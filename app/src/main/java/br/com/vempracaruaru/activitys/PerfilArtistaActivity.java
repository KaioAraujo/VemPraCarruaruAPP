package br.com.vempracaruaru.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;
import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.util.ConfigSistema;


public class PerfilArtistaActivity extends AppCompatActivity implements OnClickListener  {

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

        ImageButton btnEmail = (ImageButton) findViewById(R.id.btn_email);
        ImageButton btnFone = (ImageButton) findViewById(R.id.btn_fone);
        ImageButton btnTwitter = (ImageButton) findViewById(R.id.btn_twitter);
        ImageButton btnInstagram = (ImageButton) findViewById(R.id.btn_instagram);
        ImageButton btnFace = (ImageButton) findViewById(R.id.btn_facebook);

        btnEmail.setOnClickListener(this);
        btnFone.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        btnInstagram.setOnClickListener(this);
        btnFace.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_email:
                break;
            case R.id.btn_fone:
                break;
            case R.id.btn_twitter:
                break;
            case R.id.btn_instagram:
                break;
            case R.id.btn_facebook:
                break;
        }
    }
}
