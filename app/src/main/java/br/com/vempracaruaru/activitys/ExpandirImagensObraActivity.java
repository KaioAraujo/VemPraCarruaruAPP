package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.adapters.AdapterGaleriaSlideObra;
import br.com.vempracaruaru.obra.Obra;


public class ExpandirImagensObraActivity extends AppCompatActivity{

    AdapterGaleriaSlideObra adapter;
    ViewPager galeria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandir_imagem_obra_layout);

        Intent intent = getIntent();

        Obra obra = (Obra) intent.getSerializableExtra("obra");

        galeria = (ViewPager) findViewById(R.id.galeriaObra);
        adapter = new AdapterGaleriaSlideObra(this,obra);
        galeria.setAdapter(adapter);

    }
}
