package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.comunicacao.DownloadListarUsuario;
import br.com.vempracaruaru.usuario.Usuario;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

public class AlterarActivity extends AppCompatActivity {

    public static ConfigSistema cfgs = new ConfigSistema();
    private Usuario usuario = null;
    private String senha;
    private String senhaConfirmacao;
    private SharedPreferences sharedPrefEmail;
    private String isEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_layout);

        sharedPrefEmail = getSharedPreferences("LOGIN", 0);
        isEmail = sharedPrefEmail.getString("email", "");

        try {
            DownloadListarUsuario download = new DownloadListarUsuario(AlterarActivity.this);
            download.execute(isEmail);
            usuario = download.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
        }

        final EditText edtSenha = (EditText) findViewById(R.id.edt_nova_senha);
        final EditText edtConfimarSenha = (EditText) findViewById(R.id.edt_confirmar_senha);

        TextView txtNome = (TextView) findViewById(R.id.txv_info_nome);
        TextView txtPontuacao = (TextView) findViewById(R.id.txv_info_pontuacao);
        txtNome.setText(""+usuario.getNome());
        txtPontuacao.setText("Pontuação: "+usuario.getPontos());


        Button btnAlterar = (Button) findViewById(R.id.btn_alterar);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(getApplicationContext(),MinhaContaActivity.class);
                senha = edtSenha.getText().toString();
                senhaConfirmacao = edtConfimarSenha.getText().toString();

                if (senha.equals(senhaConfirmacao.toString())) {
                    usuario.setSenha(senha);
                    try {
                        alterar(its);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"As senhas digitadas não conferem!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void alterar(final Intent its) throws IOException, ClassNotFoundException {
        final Intent it = its;
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(cfgs.URL);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    http.addRequestProperty("content-type", "application/binary");
                    http.setDoInput(true);
                    http.setDoOutput(true);
                    http.setConnectTimeout(20000);
                    http.connect();

                    //abre canal de saída
                    ObjectOutputStream oos = new ObjectOutputStream(http.getOutputStream());
                    //envia objeto
                    oos.writeObject(new Solicitacao(Solicitacao.iSenhaAlterar, "", "", "", usuario));
                    //abre canal de leitura
                    ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
                    //recebe objeto
                    Serializable obTeste = (Serializable) ois.readObject();
                    usuario = (Usuario) obTeste;
                    //imprime log
                    if (usuario != null) {
                        Log.i("LoginActivity", "RETORNO SENHA ALTERADA:> " + usuario.toString());
                        startActivity(its);
                        Message msg = handler.obtainMessage();
                        msg.arg1 = 1;
                        handler.sendMessage(msg);
                        finish();
                    } else {
                        Log.i("LoginActivity", "RETORNO SENHA ALTERADA:> Senha não alterada!");
                        Message msg = handler.obtainMessage();
                        msg.arg1 = 2;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Log.e("LoginActivity", "Erro do TRY " + e.getMessage());
                }
            }
        }.start();
    }

    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1) {
                Toast.makeText(getApplicationContext(), "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();
            } else if(msg.arg1 == 2) {
                Toast.makeText(getApplicationContext(), "Não foi possível alterar a senha, verifique os dados e tente novamente!", Toast.LENGTH_LONG).show();
            }
        }
    };
}
