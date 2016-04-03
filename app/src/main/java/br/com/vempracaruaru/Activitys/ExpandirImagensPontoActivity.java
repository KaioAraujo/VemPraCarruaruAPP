package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.Adapters.AdapterGaleriaSlidePonto;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;


public class ExpandirImagensPontoActivity extends AppCompatActivity {

    AdapterGaleriaSlidePonto adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandir_imagem_ponto_layout);

        Intent intent = getIntent();

        PontoTuristico pontoTuristico = (PontoTuristico) intent.getSerializableExtra("ponto");

        adapter = new AdapterGaleriaSlidePonto(this,pontoTuristico);
        Gallery gallery = (Gallery) findViewById(R.id.galeria_slide_ponto);
        gallery.setAdapter(adapter);
    }
}
