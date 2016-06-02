package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.adapters.AdapterGaleriaPonto;
import br.com.vempracaruaru.comunicacao.DownloadListarFotoObra;
import br.com.vempracaruaru.comunicacao.DownloadListarFotoPonto;
import br.com.vempracaruaru.foto.Foto;
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
        ponto.setListaFotoPonto(recuperarFotos(ponto.getId()));

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
    private ArrayList<Foto> recuperarFotos(int id) {
        ArrayList<Foto> fotos = null;
        try {
            Log.i("teste","id: "+id);
            DownloadListarFotoPonto download  = new DownloadListarFotoPonto(this);
            download.execute(id);
            fotos = download.get();
            Log.i("teste","tamanho: " +fotos.size());
            Log.i("teste","tamanho: " +fotos.get(0).getImagem());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return fotos;
    }

}
