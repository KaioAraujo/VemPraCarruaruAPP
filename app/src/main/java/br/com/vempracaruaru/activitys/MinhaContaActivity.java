package br.com.vempracaruaru.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.comunicacao.DownloadListarUsuario;
import br.com.vempracaruaru.usuario.Usuario;

public class MinhaContaActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minha_conta_layout);

            Usuario usuario = null;
//        sharedPref = getPreferences(Context.MODE_PRIVATE);
//        String nome = sharedPref.getString("email","email");


        try {
            DownloadListarUsuario download = new DownloadListarUsuario(MinhaContaActivity.this);
            download.execute("kaio_web@hotmail.com");
            usuario = download.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
        }

        TextView txtNome = (TextView) findViewById(R.id.txv_info_nome);
        txtNome.setText(""+usuario.getNome());

        Button btnAlterar = (Button) findViewById(R.id.btn_alterar_senha);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(getApplicationContext(),AlterarActivity.class);
                startActivity(its);
            }
        });
    }
}
