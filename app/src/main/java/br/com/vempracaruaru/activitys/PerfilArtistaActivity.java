package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.artista.Artista;


public class PerfilArtistaActivity extends AppCompatActivity {

    Artista artista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_artista_layout);

        final Intent it = getIntent();
        artista = (Artista)  it.getSerializableExtra("artista");

        ImageView imgArista = (ImageView) findViewById(R.id.img_artista);
        imgArista.setImageResource(R.drawable.azulao);

        TextView titulo = (TextView) findViewById(R.id.txt_sub_titulo_artista);
        TextView subTitulo = (TextView) findViewById(R.id.txt_sub_titulo_artista);
        TextView textoPrincipal = (TextView) findViewById(R.id.txt_texto_principal_artista);

        titulo.setText(artista.getNome());
        subTitulo.setText(artista.getTipo());
        textoPrincipal.setText(artista.getHistorico());

    }
}
