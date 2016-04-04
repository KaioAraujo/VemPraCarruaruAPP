package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.usuario.Usuario;
import br.com.vempracaruaru.util.Solicitacao;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String email;
    private String senha;
    private EditText emailEdt;
    private EditText senhaEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        emailEdt = (EditText) findViewById(R.id.edt_campo_email);
        senhaEdt =(EditText) findViewById(R.id.edt_campo_senha);
        Button btnEntrar = (Button) findViewById(R.id.btn_entrar);
        ImageButton btnFacebook = (ImageButton) findViewById(R.id.ibt_facebook);
        TextView cadastre = (TextView) findViewById(R.id.txv_cadastrese);

        btnEntrar.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        cadastre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent its = new Intent(this,HomeActivity.class);
        switch (v.getId()){
            //captura de cliques
            //entrar
            case R.id.btn_entrar:
                email = emailEdt.getText().toString();
                senha = senhaEdt.getText().toString();
                if (!email.equals("") && !senha.equals("")) {
                    try {
                        login();
                        Log.i("LoginActivity", "RETORNO:> Comunicação OK");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    //imprime log
                    startActivity(its);
                } else {
                    Toast.makeText(getApplicationContext(), "Digite os campos de email e senha!", Toast.LENGTH_LONG).show();
                }
                break;
            //facebook
            case R.id.ibt_facebook:
                startActivity(its);
                break;
            //cadastro
            case R.id.txv_cadastrese:
                Intent itsCadastro = new Intent(this,CadastroActivity.class);
                startActivity(itsCadastro);
                break;
        }
    }

    public boolean login() throws IOException, ClassNotFoundException {
        Boolean retorno = false;

        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.1.104:8080/VemPraCaruaru/Android");
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
                    oos.writeObject(new Solicitacao(Solicitacao.iLoginPadrao, email, senha, "", null));
                    //abre canal de leitura
                    ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
                    //recebe objeto
                    Serializable obTeste = (Serializable) ois.readObject();
                    Usuario usuarioRetorno = (Usuario) obTeste;
                    //imprime log
                    Log.i("LoginActivity", "RETORNO:> " + usuarioRetorno.toString());
                } catch (Exception e) {
                    Log.e("LoginActivity", "Erro do TRY " + e.getMessage());
                }
            }
        }.start();
        return retorno;
    }
}
