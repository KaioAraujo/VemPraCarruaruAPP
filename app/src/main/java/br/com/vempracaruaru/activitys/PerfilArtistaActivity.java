package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;
import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.util.ConfigSistema;


public class PerfilArtistaActivity extends AppCompatActivity {

    public static ConfigSistema cfgs = new ConfigSistema();
    private Artista artista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_artista_layout);

        final Intent it = getIntent();
        artista = (Artista)  it.getSerializableExtra("artista");

        ImageView imgArista = (ImageView) findViewById(R.id.img_artista);
        Picasso.with(getApplicationContext())
                .load(cfgs.URL_IMAGENS + artista.getFoto()).into(imgArista);

        TextView titulo = (TextView) findViewById(R.id.txt_sub_titulo_artista);
        TextView subTitulo = (TextView) findViewById(R.id.txt_sub_titulo_artista);
        TextView textoPrincipal = (TextView) findViewById(R.id.txt_texto_principal_artista);

        titulo.setText(artista.getNome());
        subTitulo.setText(artista.getTipo());
        textoPrincipal.setText(artista.getHistorico());

    }
}
