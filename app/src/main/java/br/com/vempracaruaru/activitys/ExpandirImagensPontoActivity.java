package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.adapters.AdapterGaleriaSlideObra;
import br.com.vempracaruaru.adapters.AdapterGaleriaSlidePonto;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;


public class ExpandirImagensPontoActivity extends AppCompatActivity {

    AdapterGaleriaSlidePonto adapter;
    ViewPager galeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandir_imagem_ponto_layout);

        Intent intent = getIntent();

        PontoTuristico pontoTuristico = (PontoTuristico) intent.getSerializableExtra("ponto");

        galeria = (ViewPager) findViewById(R.id.galeriaObra);
        adapter = new AdapterGaleriaSlidePonto(this,pontoTuristico);
        galeria.setAdapter(adapter);

    }
}
