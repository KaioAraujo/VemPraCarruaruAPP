package br.com.vempracaruaru.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.Adapters.AdapterGaleriaObra;
import br.com.vempracaruaru.obra.Obra;

public class FotosObrasActivity extends AppCompatActivity {

    private Obra obra;
    private AdapterGaleriaObra adapterGaleria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fotos_obras_layout);

    }
}
