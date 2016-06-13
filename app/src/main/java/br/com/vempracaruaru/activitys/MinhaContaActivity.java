package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.adapters.AdapterMinhaLista;
import br.com.vempracaruaru.comunicacao.DownloadListarListaAVisitar;
import br.com.vempracaruaru.comunicacao.DownloadListarListaVisitado;
import br.com.vempracaruaru.comunicacao.DownloadListarUsuario;
import br.com.vempracaruaru.lista.Lista;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.usuario.Usuario;

public class MinhaContaActivity extends AppCompatActivity{

    private SharedPreferences sharedPrefEmail;
    private String isEmail;
    private Map<String,List<Lista>> listas = new HashMap<>();
    private ArrayList<Lista> pontosJaVisitados = null;

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

        recuperarPontosJaVisitados(usuario.getId());
        recuperarPontosNaovisitados(usuario.getId());
        final ExpandableListView listaCheck = (ExpandableListView) findViewById(R.id.lista);
        listaCheck.setAdapter(new AdapterMinhaLista(getApplicationContext(), listas));
        listaCheck.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                if(groupPosition == 0){
                    Toast.makeText(getApplicationContext(),
                            listas.get("N").get(childPosition).getNomePontoTuristico(),
                                Toast.LENGTH_SHORT)
                                .show();
                }else{
                Toast.makeText(getApplicationContext(),
                    listas.get("S").get(childPosition).getNomePontoTuristico(),
                        Toast.LENGTH_SHORT)
                        .show();
                }

                return false;
            }
        });
    }

    private void recuperarPontosNaovisitados(int id) {
        ArrayList<Lista> pontosNaovisitados = null;

        try {
            DownloadListarListaAVisitar download = new DownloadListarListaAVisitar(this);
            download.execute(id);
            pontosNaovisitados = download.get();
            if(pontosNaovisitados !=null){
                listas.put("N",pontosNaovisitados);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void recuperarPontosJaVisitados(int id) {
        ArrayList<Lista>pontosJaVisitados = null;

        try {
            DownloadListarListaVisitado download = new DownloadListarListaVisitado(this);
            download.execute(id);
            pontosJaVisitados = download.get();
            if(pontosJaVisitados !=null) {
                listas.put("S",pontosJaVisitados);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
