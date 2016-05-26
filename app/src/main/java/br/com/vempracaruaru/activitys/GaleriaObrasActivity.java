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

import br.com.vempracaruaru.adapters.AdapterGaleriaObra;
import br.com.vempracaruaru.comunicacao.DownloadListarFotoObra;
import br.com.vempracaruaru.foto.Foto;
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
        obra.setListaFotos(recuperarFotos(obra.getId()));
        adapter = new AdapterGaleriaObra(obra,this);
        galeria.setAdapter(adapter);

        galeria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent its = new Intent(getApplicationContext(), ExpandirImagensObraActivity.class);
                its.putExtra("obra",obra);
                startActivity(its);
            }
        });
    }

    private ArrayList<Foto> recuperarFotos(int id) {
        ArrayList<Foto> fotos = null;
        try {
            Log.i("teste 2","galeria id: "+id);
            DownloadListarFotoObra download  = new DownloadListarFotoObra(this);
            Log.i("teste 2","fazer download");
            download.execute(id);
            fotos = download.get();
            Log.i("teste 2",""+fotos.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("teste 2","galeria tamanho: "+fotos.size());
        return fotos;
    }
}
