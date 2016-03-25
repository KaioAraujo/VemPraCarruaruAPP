package br.com.vempracaruaru.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.obra.Obra;

public class PerfilObraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_obra_layout);

        Intent it = getIntent();

        Obra obra = (Obra)  it.getSerializableExtra("obra");

        ImageView img = (ImageView) findViewById(R.id.imv_imagem_obra);
        //aqui eu passo a imagem para o imagem view
        img.setImageResource(obra.getFoto().charAt(0));

        TextView titulo = (TextView) findViewById(R.id.txv_titulo_obra);
        //aqui eu passo o titulo da obra
        titulo.setText(obra.getNome());

        TextView subTitulo = (TextView) findViewById(R.id.txv_sub_titulo_obra);
        //aqui eu passo o sub-titulo da obra
        subTitulo.setText("Localização: " + obra.getNomePontoTuristico() + "/n" +
                            "Artista: " + obra.getNomeArtista());

        TextView texto = (TextView) findViewById(R.id.txv_texto);
        //aqui eu passo o texto da obra
        texto.setText(obra.getDescricao());

        Button btnVerFotos = (Button) findViewById(R.id.btn_ver_fotos);
        btnVerFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui vamos chamar a proxima tela...
            }
        });
    }
}
