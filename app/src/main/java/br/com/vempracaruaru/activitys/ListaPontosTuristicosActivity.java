package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

import br.com.vempracaruaru.adapters.AdapterListaPonto;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class ListaPontosTuristicosActivity extends AppCompatActivity implements OnItemClickListener{

    //colocou o array adapter errado aqui
    //voce coloco o arrayAdapter da galeria
    // erra o arrayAdapterLista
    private ArrayList<PontoTuristico> pontoTuristico;
    private AdapterListaPonto adapterListaPonto;
    private ListView listaponto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaponto =  new ListView(this);
        //esqueceu de implementar onItemClivck a interface
        listaponto.setOnItemClickListener(this);
        setContentView(listaponto);

        ArrayList<Integer> listaFotosPonto = new ArrayList<>();
        listaFotosPonto.add(0);
        listaFotosPonto.add(1);
        listaFotosPonto.add(2);
        listaFotosPonto.add(3);
        listaFotosPonto.add(4);
        listaFotosPonto.add(5);
        listaFotosPonto.add(6);
        listaFotosPonto.add(7);

        pontoTuristico = new ArrayList<>();
        pontoTuristico.add(new PontoTuristico(1,"Teste foto","Teste foto",listaFotosPonto));
        pontoTuristico.add(new PontoTuristico(2,"Teste foto","Teste foto",listaFotosPonto));
        pontoTuristico.add(new PontoTuristico(3,"Teste foto","Teste foto",listaFotosPonto));
        pontoTuristico.add(new PontoTuristico(4,"Teste foto","Teste foto",listaFotosPonto));
        pontoTuristico.add(new PontoTuristico(5,"Teste foto","Teste foto",listaFotosPonto));
        pontoTuristico.add(new PontoTuristico(6,"Teste foto","Teste foto",listaFotosPonto));

        //passou o array list errado
        adapterListaPonto = new AdapterListaPonto(this,pontoTuristico);
        listaponto.setAdapter(adapterListaPonto);
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
