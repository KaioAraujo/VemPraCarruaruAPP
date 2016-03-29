package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.vempracaruaru.Adapters.AdapterListaObras;
import br.com.vempracaruaru.fachada.Fachada;
import br.com.vempracaruaru.obra.Obra;

public class ListaObrasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<Obra> obras;
    private AdapterListaObras adapter;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista = new ListView(this);
        lista.setOnItemClickListener(this);
        setContentView(lista);

        ArrayList<Integer> listaFotos = new ArrayList<>();
        listaFotos.add(0);
        listaFotos.add(1);
        listaFotos.add(2);
        listaFotos.add(3);
        listaFotos.add(4);
        listaFotos.add(5);
        listaFotos.add(6);
        listaFotos.add(7);
        listaFotos.add(8);
        listaFotos.add(9);

        obras = new ArrayList<>();
        obras.add(new Obra(1,"Nome Artista 01","Teste1 Imagem","Ponto teste 01","isso aqui foi um teste1",listaFotos));
        obras.add(new Obra(2,"Nome Artista 02","Teste2 Imagem","Ponto teste 02","isso aqui foi um teste2",listaFotos));
        obras.add(new Obra(3,"Nome Artista 03","Teste3 Imagem","Ponto teste 03","isso aqui foi um teste3",listaFotos));
        obras.add(new Obra(4,"Nome Artista 04","Teste4 Imagem","Ponto teste 04","isso aqui foi um teste4",listaFotos));
        obras.add(new Obra(5,"Nome Artista 05","Teste5 Imagem","Ponto teste 05","isso aqui foi um teste5",listaFotos));


        adapter = new AdapterListaObras(this,obras);
        lista.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Obra obra = (Obra) parent.getItemAtPosition(position);
        Intent its = new Intent(this,PerfilObraActivity.class);
        its.putExtra("obra",obra);
        startActivity(its);
    }
}
