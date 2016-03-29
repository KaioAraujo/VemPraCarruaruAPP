package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.Adapters.AdapterGaleriaObra;
import br.com.vempracaruaru.obra.Obra;

public class GaleriaObrasActivity extends AppCompatActivity {

    AdapterGaleriaObra adapter;
    Obra obra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galeria_obras_layout);

        Intent its = getIntent();
        obra = (Obra) its.getSerializableExtra("obra");

        TextView texto = (TextView) findViewById(R.id.txt_titulo_galeria);
        GridView galeria = (GridView) findViewById(R.id.gdv_galeria_obras);

        texto.setText(obra.getNome()+"");
        adapter = new AdapterGaleriaObra(obra,this);
        galeria.setAdapter(adapter);

    }
}
