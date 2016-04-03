package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

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
            case R.id.btn_entrar:
                startActivity(its);
                break;
            case R.id.ibt_facebook:
                startActivity(its);
                break;
            case R.id.txv_cadastrese:
                Intent itsCadastro = new Intent(this,CadastroActivity.class);
                startActivity(itsCadastro);
                break;
        }
    }
}
