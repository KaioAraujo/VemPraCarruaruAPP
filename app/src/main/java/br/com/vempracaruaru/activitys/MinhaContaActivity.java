package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.adapters.AdapterMinhaLista;
import br.com.vempracaruaru.comunicacao.DownloadListarUsuario;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.usuario.Usuario;

public class MinhaContaActivity extends AppCompatActivity {

    private SharedPreferences sharedPrefEmail;
    private String isEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minha_conta_layout);

        Usuario usuario = null;
        sharedPrefEmail = getSharedPreferences("LOGIN", 0);
        isEmail = sharedPrefEmail.getString("email", "");
        Log.i("LoginActivity", "RETORNO EMAIL NA MINHA CONTA:> " + isEmail);


        try {
            DownloadListarUsuario download = new DownloadListarUsuario(MinhaContaActivity.this);
            download.execute(isEmail);
            usuario = download.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
        }

        TextView txtNome = (TextView) findViewById(R.id.txv_info_nome);
        TextView txtPontuacao = (TextView) findViewById(R.id.txv_info_pontuacao);
        txtNome.setText("" + usuario.getNome());
        txtPontuacao.setText("Pontuação: " + usuario.getPontos());


        Button btnAlterar = (Button) findViewById(R.id.btn_alterar_senha);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(getApplicationContext(), AlterarActivity.class);
                startActivity(its);
            }
        });


        ExpandableListView listaCheck = (ExpandableListView) findViewById(R.id.lista);
        listaCheck.setAdapter(new AdapterMinhaLista(getApplicationContext(), usuario.getId()));


//        listaCheck.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
////                PontoTuristico ponto = (PontoTuristico)
//                return false;
//            }
//        });
    }
}
