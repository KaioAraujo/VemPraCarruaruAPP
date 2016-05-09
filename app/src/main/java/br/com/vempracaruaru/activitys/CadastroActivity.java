package br.com.vempracaruaru.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.usuario.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha;
    public static ArrayList<Usuario> listaUsuario = new ArrayList<>();

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

                if(edtSenha.getText().toString().equals(edtConfimarSenha.getText().toString())) {
                    nome = edtNome.getText().toString();
                    email = edtEmail.getText().toString();
                    senha = edtSenha.getText().toString();
                    confirmarSenha = edtConfimarSenha.getText().toString();
                    listaUsuario.add(new Usuario(0,nome,email,senha));
                }else{
                    Toast.makeText(getApplicationContext(),"Senhas diferentes...",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
