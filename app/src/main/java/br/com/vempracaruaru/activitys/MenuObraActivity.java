package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;
import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.util.ConfigSistema;

public class MenuObraActivity extends AppCompatActivity {

    public static ConfigSistema cfgs = new ConfigSistema();
    private Obra obra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_obra_layout);

        final Intent it = getIntent();

        obra = (Obra)  it.getSerializableExtra("obra");

        ImageView img = (ImageView) findViewById(R.id.img_obra_menu);
        //aqui eu passo a imagem para o imagem view
        Picasso.with(getApplicationContext()).load(cfgs.URL_IMAGENS + obra.getFoto()).into(img);

        TextView titulo = (TextView) findViewById(R.id.txt_titulo_obra_menu);
        //aqui eu passo o titulo da obra
        titulo.setText(obra.getNome());

        TextView subTitulo = (TextView) findViewById(R.id.txt_sub_titulo);
        //aqui eu passo o sub-titulo da obra
        String textoPerfil;
        Log.i("MenuObraActivity",obra.getNome());
        if(obra.getNomeArtista() != null){
            textoPerfil = "Localização: " + obra.getNomePontoTuristico() +
                    "\nArtista: "+obra.getNomeArtista();
        }else{
            textoPerfil = "Localização: " + obra.getNomePontoTuristico() + "\n" +
                    "Artista: Artista indefinido";
        }
        subTitulo.setText(textoPerfil);

        TextView texto = (TextView) findViewById(R.id.txt_texto_menu);
        //aqui eu passo o texto da obra
        texto.setText(obra.getDescricao());

        Button btnVerFotos = (Button) findViewById(R.id.btn_fotos_menu);
        btnVerFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(getApplicationContext(),GaleriaObrasActivity.class);
                its.putExtra("obra",obra);
                startActivity(its);
            }
        });
    }
}

