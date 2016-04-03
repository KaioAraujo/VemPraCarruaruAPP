package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.Adapters.AdapterListaArtista;
import br.com.vempracaruaru.artista.Artista;

public class ListaArtistaActivity extends AppCompatActivity implements OnItemClickListener {

    private ArrayList<Artista> artistas;
    private AdapterListaArtista adapter;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_lista_artista_layout);

        lista = new ListView(this);
        lista.setOnItemClickListener(this);
        setContentView(lista);

        artistas = new ArrayList<>();
        artistas.add(new Artista(1, "Azulão", "Músico"));
        artistas.add(new Artista(2, "Mestre Vitalino", "Artesão"));

        adapter = new AdapterListaArtista(this, artistas);
        lista.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Artista artista = (Artista) parent.getItemAtPosition(position);

        Intent its = new Intent(this,PerfilArtistaActivity.class);
        its.putExtra("artista", artista);
        startActivity(its);
    }
}
