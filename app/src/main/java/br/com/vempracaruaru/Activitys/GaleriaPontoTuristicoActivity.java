package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.Adapters.AdapterGaleriaPonto;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class GaleriaPontoTuristicoActivity extends AppCompatActivity {

    AdapterGaleriaPonto adapterponto;
    PontoTuristico ponto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galeria_foto_ponto_turitico_layout);

        Intent intent = getIntent();

        //vem um ponto não uma lista
        ponto = (PontoTuristico) intent.getSerializableExtra("ponto");

        TextView texto = (TextView) findViewById(R.id.txv_galeria_ponto);
        GridView gridView = (GridView) findViewById(R.id.gdv_galeria_ponto);

        texto.setText(ponto.getNome());

        adapterponto =  new AdapterGaleriaPonto(getApplicationContext(),ponto);
        gridView.setAdapter(adapterponto);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ExpandirImagensPontoActivity.class);
                intent.putExtra("ponto", ponto);
                startActivity(intent);
            }
        });
    }
}
