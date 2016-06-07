package br.com.vempracaruaru.activitys;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.com.vempracaruaru.usuario.Usuario;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

public class CadastroActivity extends AppCompatActivity {

    public static ConfigSistema cfgs = new ConfigSistema();
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    private Usuario usuario = null;
    public static ArrayList<Usuario> listaUsuario = new ArrayList<>();
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPrefEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);

        final EditText edtNome = (EditText) findViewById(R.id.edt_nome);
        final EditText edtEmail = (EditText) findViewById(R.id.edt_email);
        final EditText edtSenha = (EditText) findViewById(R.id.edt_senha);
        final EditText edtConfimarSenha = (EditText) findViewById(R.id.edt_confirmar);

        Button btnCadastro = (Button) findViewById(R.id.btn_cadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(getApplicationContext(),HomeActivity.class);
                nome = edtNome.getText().toString();
                email = edtEmail.getText().toString();
                senha = edtSenha.getText().toString();
                confirmarSenha = edtConfimarSenha.getText().toString();

                if (!nome.equals("") && !email.equals("") && !senha.equals("") && !confirmarSenha.equals("")) {
                    if (senha.equals(confirmarSenha.toString())) {

                        try {
                            progressDialog = new ProgressDialog(CadastroActivity.this);
                            progressDialog.setTitle("Aguarde!");
                            progressDialog.setMessage("Alterando a senha");
                            progressDialog.show();
                            usuario = new Usuario(0, nome, email, "", senha, "", "", 0, 'S');
                            cadastro(its);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"As senhas digitadas não conferem!",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Verifique os dados digitados",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void cadastro(final Intent its) throws IOException, ClassNotFoundException {
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
                    oos.writeObject(new Solicitacao(Solicitacao.iUsuarioCadastro, "", "", "", usuario));
                    //abre canal de leitura
                    ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
                    //recebe objeto
                    Serializable obTeste = (Serializable) ois.readObject();
                    usuario = (Usuario) obTeste;
                    //imprime log
                    if (usuario != null) {
                        Log.i("LoginActivity", "RETORNO:> " + usuario.toString());
                        startActivity(its);
                        Message msg = handler.obtainMessage();
                        msg.arg1 = 1;
                        handler.sendMessage(msg);
                        sharedPrefEmail = getSharedPreferences("LOGIN", 0);
                        SharedPreferences.Editor editorEmail = sharedPrefEmail.edit();
                        editorEmail.putString("email", email);
                        editorEmail.commit();
                        progressDialog.dismiss();
                        finish();
                    } else {
                        Log.i("LoginActivity", "RETORNO:> Cadastro não efetuado!");
                        Message msg = handler.obtainMessage();
                        msg.arg1 = 2;
                        handler.sendMessage(msg);
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {
                    Log.e("LoginActivity", "Erro do TRY " + e.getMessage());
                    progressDialog.dismiss();
                }
            }
        }.start();
    }

    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1) {
                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!\nID: " + usuario.getId() + "\nNome: " + usuario.getNome() + "\nEmail: " + usuario.getEmail() + "\nPontos: " + usuario.getPontos(), Toast.LENGTH_LONG).show();
            } else if(msg.arg1 == 2) {
                Toast.makeText(getApplicationContext(), "Não foi possível efetuar o cadastro, verifique os dados e tente novamente!", Toast.LENGTH_LONG).show();
            }
        }
    };
}
