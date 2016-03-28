package br.com.vempracaruaru.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class ListaPontoActivity extends AppCompatActivity {

    private ArrayList<PontoTuristico> listaPonto;
 //   private AdapterListaPontos adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_ponto_layout);

    }
}
