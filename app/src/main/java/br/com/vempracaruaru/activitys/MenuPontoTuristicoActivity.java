package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;
import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.util.ConfigSistema;


public class MenuPontoTuristicoActivity extends AppCompatActivity implements OnClickListener{

    //sugestão flavio como aqui tem varios botões implementa
    //a interface onclicklineste fica mais facil de programar
    //e mais legivel
    public static ConfigSistema cfgs = new ConfigSistema();
    private PontoTuristico pontoTuristico;
    private Button btnAdd;
    Boolean teste = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_ponto_turistico_layout);

        //cuidado com os nomes das variaveis flavio pra não confundir
        //coloca o nome dela tipo textoDescricao/ txtTitulo
        Intent itponto = getIntent();

        pontoTuristico = (PontoTuristico) itponto.getSerializableExtra("pontoTuristico");

        ImageView imgPonto = (ImageView) findViewById(R.id.imv_imagem_ponto);
        Picasso.with(getApplicationContext())
                .load(cfgs.URL_IMAGENS + pontoTuristico.getFoto()).into(imgPonto);

        TextView textoTitulo = (TextView) findViewById(R.id.txv_ponto_titulo);
        textoTitulo.setText(pontoTuristico.getNome());

        TextView textoDescricao = (TextView) findViewById(R.id.txv_menu_descricao_ponto);
        String descricao = "Endereço: " + pontoTuristico.getEndereco() +
                            "\nTelefone: " + pontoTuristico.getTelefone() +
                            "\nEmail: " + pontoTuristico.getEmail() +
                            "\nHorario: " + pontoTuristico.getHorarioFuncionamento() +
                            "\nTempo de visitção: " + pontoTuristico.getTempoVisitacao();

        textoDescricao.setText(descricao);


        //esqueceu da descricao do ponto historico dele
        TextView textoHistorico = (TextView) findViewById(R.id.txv_historico_ponto);
        textoHistorico.setText(pontoTuristico.getHistoricoDescricao());

        //aqui os buttons ficam assim
        //aqui vc pega a referencia
        Button btnFotos = (Button) findViewById(R.id.btn_ver_fotos_ponto);
        Button btnObras = (Button) findViewById(R.id.btn_ver_obras_ponto);
        Button btnGps = (Button) findViewById(R.id.btn_ver_gps_ponto);
        btnAdd = (Button) findViewById(R.id.btn_adicionar);
        //aqui vc passa o onclik que vc implementou ou seja ele mesmno
        btnFotos.setOnClickListener(this);
        btnObras.setOnClickListener(this);
        btnGps.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //aqui no metodo de onclik vc faz um switch pra pegar
        //os clicks pode ser um if

        switch (v.getId()){
            case R.id.btn_ver_fotos_ponto:
                //aqui eu chamo tela e passo o ponto para ela
                Intent itFotos = new Intent(this,GaleriaPontoTuristicoActivity.class);
                itFotos.putExtra("ponto",pontoTuristico);
                startActivity(itFotos);
                break;
            case R.id.btn_ver_obras_ponto:
                Intent itObras = new Intent(this,ListaObrasActivity.class);
                startActivity(itObras);
                break;
            case R.id.btn_ver_gps_ponto:
                Intent itGps = new Intent(this,LocalizacaoGpsActivity.class);
                itGps.putExtra("ponto", pontoTuristico);
                Log.i("Mapa",pontoTuristico.getLatitude()+" - "+pontoTuristico.getLongitude());
                itGps.putExtra("titulo",pontoTuristico.getNome());
                startActivity(itGps);
                break;
            case R.id.btn_adicionar:
                if(teste == true){
                    teste = false;
                    btnAdd.setText("Remover");
                }else{
                    teste = true;
                    btnAdd.setText("Adicionar");
                }
                break;
        }
    }
}
