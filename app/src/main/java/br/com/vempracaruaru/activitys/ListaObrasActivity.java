package br.com.vempracaruaru.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.adapters.AdapterListaObra;
import br.com.vempracaruaru.comunicacao.DownloadListarArtista;
import br.com.vempracaruaru.comunicacao.DownloadListarObra;
import br.com.vempracaruaru.obra.Obra;


public class ListaObrasActivity extends AppCompatActivity implements OnItemClickListener{

    private ArrayList<Obra> obras;
    private AdapterListaObra adapter;
    private ListView lista;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Aguarde!");
        progressDialog.setMessage("Carregando lista de obras...");
        progressDialog.show();

        lista = new ListView(this);
        lista.setOnItemClickListener(this);
        setContentView(lista);

        try {
            DownloadListarObra downloadObra = new DownloadListarObra(ListaObrasActivity.this);
            downloadObra.execute(0);
            obras = downloadObra.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        adapter = new AdapterListaObra(this,obras);
        lista.setAdapter(adapter);

        progressDialog.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Obra obra = (Obra) parent.getItemAtPosition(position);
        Intent its = new Intent(this,MenuObraActivity.class);
        its.putExtra("obra",obra);
        startActivity(its);
    }
}
