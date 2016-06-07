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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.joao.vempracaruaruapp.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import br.com.vempracaruaru.usuario.Usuario;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static ConfigSistema cfgs = new ConfigSistema();
    public static Usuario usuarioRetorno = null;
    private static Boolean retornoLogin = false;
    private String email;
    private String senha;
    private String nome;
    private String id;
    private EditText emailEdt;
    private EditText senhaEdt;
    private SharedPreferences sharedPref;
    private ProgressDialog progressDialog;
    //
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        boolean isLogged = sharedPref.getBoolean("logado", false);

        if(isLogged) {
            Intent its = new Intent(this,HomeActivity.class);
            startActivity(its);
        }

        emailEdt = (EditText) findViewById(R.id.edt_campo_email);
        senhaEdt =(EditText) findViewById(R.id.edt_campo_senha);
        Button btnEntrar = (Button) findViewById(R.id.btn_entrar);
        TextView cadastre = (TextView) findViewById(R.id.txv_cadastrese);

        btnEntrar.setOnClickListener(this);
        cadastre.setOnClickListener(this);

        //aqui estão os metodos para fazer o login com o face
        //id do button
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //colocamos setamos as permições
        loginButton.setReadPermissions("public_profile","email");
        //aqui sabemos se o login foi feito com sucesso ou não
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i("LoginFace","onSuccess");
                getInfoFace();
            }

            @Override
            public void onCancel() {
                // App code
                Log.i("LoginFace","onCancel");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("LoginFace","onError");

            }
        });
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
                        progressDialog = new ProgressDialog(this);
                        progressDialog.setTitle("Aguarde!");
                        progressDialog.setMessage("Efetuando login...");
                        progressDialog.show();
                        login(its);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Digite os campos de email e senha!", Toast.LENGTH_LONG).show();
                }
                break;
            //cadastro
            case R.id.txv_cadastrese:
                Intent itsCadastro = new Intent(this,CadastroActivity.class);
                startActivity(itsCadastro);
                break;
        }
    }

    public void login(final Intent its) throws IOException, ClassNotFoundException {
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
                    oos.writeObject(new Solicitacao(Solicitacao.iLoginPadrao, email, senha, "", null));
                    //abre canal de leitura
                    ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
                    //recebe objeto
                    Serializable obTeste = (Serializable) ois.readObject();
                    usuarioRetorno = (Usuario) obTeste;
                    //imprime log
                    if (usuarioRetorno != null) {
                        Log.i("LoginActivity", "RETORNO:> " + usuarioRetorno.toString());
                        retornoLogin = true;
                        startActivity(its);
//                        Message msg = handler.obtainMessage();
//                        msg.arg1 = 1;
//                        handler.sendMessage(msg);

                        sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("logado", true);
                        editor.commit();

                        finish();

                    } else {
                        progressDialog.dismiss();
                        Log.i("LoginActivity", "RETORNO:> Login inválido");
//                        Message msg = handler.obtainMessage();
//                        msg.arg1 = 2;
//                        handler.sendMessage(msg);
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
                Toast.makeText(getApplicationContext(), "ID: " + usuarioRetorno.getId() + "\nNome: " + usuarioRetorno.getNome() + "\nEmail: " + usuarioRetorno.getEmail() + "\nPontos: " + usuarioRetorno.getPontos(), Toast.LENGTH_LONG).show();
            } else if(msg.arg1 == 2) {
                Toast.makeText(getApplicationContext(), "Login inválido", Toast.LENGTH_LONG).show();
            }
        }
    };

    //metodo que recupera as informações do face
    private void getInfoFace(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            id = response.getJSONObject().getString("id");
                            email = response.getJSONObject().getString("email");
                            nome = response.getJSONObject().getString("name");
                            Log.i("LoginFace","getInfoFace - Completed " + id + " - " + nome + " - " + email);
                            sharedPref = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean("logado", true);
                            editor.commit();
                            Intent its = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(its);
                            finish();
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }

                });
        Bundle bundle = new Bundle();
        bundle.putString("fields","id,name,email");
        request.setParameters(bundle);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
