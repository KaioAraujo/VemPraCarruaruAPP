package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.joao.vempracaruaruapp.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        ImageButton btnPonto = (ImageButton) findViewById(R.id.ibt_pontos_turisticos);
        ImageButton btnArtista = (ImageButton) findViewById(R.id.ibt_artistas);
        ImageButton btnObras = (ImageButton) findViewById(R.id.ibt_obras);
        ImageButton btnConta = (ImageButton) findViewById(R.id.ibt_minha_conta);

        btnPonto.setOnClickListener(this);
        btnArtista.setOnClickListener(this);
        btnObras.setOnClickListener(this);
        btnConta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibt_pontos_turisticos:
                Intent itsPonto = new Intent(this,ListaPontosTuristicosActivity.class);
                startActivity(itsPonto);
                break;
            case R.id.ibt_artistas:
                Intent itsArtista = new Intent(this,ListaArtistaActivity.class);
                startActivity(itsArtista);
                break;
            case R.id.ibt_obras:
                Intent itsObra = new Intent(this,ListaObrasActivity.class);
                startActivity(itsObra);
                break;
            case R.id.ibt_minha_conta:
                Intent itsMinhaConta = new Intent(this,MinhaContaActivity.class);
                startActivity(itsMinhaConta);
                break;
        }
    }
}
