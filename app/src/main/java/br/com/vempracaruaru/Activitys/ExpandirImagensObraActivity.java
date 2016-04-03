package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.Adapters.AdapterGaleriaSlideObra;
import br.com.vempracaruaru.obra.Obra;


public class ExpandirImagensObraActivity extends AppCompatActivity {

    AdapterGaleriaSlideObra adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandir_imagem_obra_layout);

        Intent its = getIntent();
        Obra obra = (Obra) its.getSerializableExtra("obra");

        adapter = new AdapterGaleriaSlideObra(this,obra);
        Gallery gallery = (Gallery) findViewById(R.id.galeria_slide_obra);
        gallery.setAdapter(adapter);
    }
}
