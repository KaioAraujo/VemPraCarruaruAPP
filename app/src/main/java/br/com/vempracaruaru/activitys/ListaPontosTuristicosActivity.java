package br.com.vempracaruaru.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.widget.AdapterView.OnItemClickListener;

import br.com.vempracaruaru.adapters.AdapterListaPonto;
import br.com.vempracaruaru.comunicacao.DownloadListarObra;
import br.com.vempracaruaru.comunicacao.DownloadListarPontoTuristico;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class ListaPontosTuristicosActivity extends AppCompatActivity implements OnItemClickListener{

    //colocou o array adapter errado aqui
    //voce coloco o arrayAdapter da galeria
    // erra o arrayAdapterLista
    private ArrayList<PontoTuristico> pontoTuristico;
    private AdapterListaPonto adapterListaPonto;
    private ListView listaponto;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Aguarde!");
        progressDialog.setMessage("Carregando lista de pontos turísticos...");
        progressDialog.show();

        listaponto =  new ListView(this);
        //esqueceu de implementar onItemClivck a interface
        listaponto.setOnItemClickListener(this);
        setContentView(listaponto);

        try {
            DownloadListarPontoTuristico downloadPontoTuristico = new DownloadListarPontoTuristico(ListaPontosTuristicosActivity.this);
            downloadPontoTuristico.execute(0);
            pontoTuristico = downloadPontoTuristico.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //passou o array list errado
        adapterListaPonto = new AdapterListaPonto(this,pontoTuristico);
        listaponto.setAdapter(adapterListaPonto);

        progressDialog.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PontoTuristico pontoTuristico = (PontoTuristico) parent.getItemAtPosition(position);
        Intent its = new Intent(this,MenuPontoTuristicoActivity.class);
        //aqui vc passa como se fosse um id sem espaço
        its.putExtra("pontoTuristico",pontoTuristico);
        startActivity(its);
    }
}
