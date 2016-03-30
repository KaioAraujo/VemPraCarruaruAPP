package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.Adapters.AdapterListaObras;
import br.com.vempracaruaru.Adapters.AdapterListaPontos;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class ListaPontoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<PontoTuristico> listaPonto;
    private AdapterListaPontos adapter;
    private ListView lsponto;


    public ListaPontoActivity(ArrayList<PontoTuristico> listaPonto) {
        this.listaPonto = listaPonto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_ponto_layout_fragment);
        lsponto = new ListView(this);
        lsponto.setOnItemClickListener(this);
        setContentView(lsponto);


        adapter = new AdapterListaPontos(this,listaPonto);;
        lsponto.setAdapter(adapter);

         }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PontoTuristico pontoTuristico = (PontoTuristico) parent.getItemAtPosition(position);
        Intent its = new Intent(this,MenuPontoActivity.class);
        its.putExtra("ponto",pontoTuristico);
        startActivity(its);
    }
}
