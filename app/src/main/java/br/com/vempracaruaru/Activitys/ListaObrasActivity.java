package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.vempracaruaru.Adapters.AdapterListaObras;
import br.com.vempracaruaru.fachada.Fachada;
import br.com.vempracaruaru.obra.Obra;

public class ListaObrasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ArrayList<Obra> listaObra;
    private AdapterListaObras adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        listView.setOnItemClickListener(this);
        setContentView(listView);

        try {
            listaObra = Fachada.getInstance().obraListarTodos("");
            adapter = new AdapterListaObras(listaObra,this);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Obra obra = (Obra) parent.getItemAtPosition(position);
        Intent it = new Intent(this,PerfilObraActivity.class);
        it.putExtra("obra",obra);
        startActivity(it);
    }
}
