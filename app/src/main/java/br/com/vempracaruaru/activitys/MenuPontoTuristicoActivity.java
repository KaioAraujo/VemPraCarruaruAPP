package br.com.vempracaruaru.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.comunicacao.DownloadListarListaAVisitar;
import br.com.vempracaruaru.comunicacao.ListaCadastrar;
import br.com.vempracaruaru.comunicacao.ListaDeletar;
import br.com.vempracaruaru.lista.Lista;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.util.ConfigSistema;


public class MenuPontoTuristicoActivity extends AppCompatActivity implements OnClickListener{

    //sugestão flavio como aqui tem varios botões implementa
    //a interface onclicklineste fica mais facil de programar
    //e mais legivel
    public static ConfigSistema cfgs = new ConfigSistema();
    private PontoTuristico pontoTuristico;
    private Button btnAdd;
    private SharedPreferences sharedPrefUsuario;
    private int isId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_ponto_turistico_layout);

        //cuidado com os nomes das variaveis flavio pra não confundir
        //coloca o nome dela tipo textoDescricao/ txtTitulo
        Intent itponto = getIntent();

        pontoTuristico = (PontoTuristico) itponto.getSerializableExtra("pontoTuristico");

        sharedPrefUsuario = getSharedPreferences("USUARIO", 0);
        isId = sharedPrefUsuario.getInt("idUsuario", 0);
        ArrayList<Lista> pontos = null;

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


        try {
            DownloadListarListaAVisitar lista = new DownloadListarListaAVisitar(this);
            lista.execute(isId);
            pontos = lista.get();
            if(pontos != null){
                for (Lista ponto:pontos) {
                    if(ponto.getIdPontoTuristico() == pontoTuristico.getId()){
                        btnAdd.setText("Remover");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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
                itObras.putExtra("ponto",pontoTuristico);
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
                if(btnAdd.getText().equals("Adicionar")) {
                    try {
                        Lista lista = null;
                        ListaCadastrar add = new ListaCadastrar(this);
                        add.execute(pontoTuristico.getId(), isId);
                        lista = add.get();
                        if (lista == null) {
                            Toast.makeText(this, "Infelizmente não foi possivel adicionar a sua lista", Toast.LENGTH_LONG).show();
                        }
                        btnAdd.setText("Remover");
                        Toast.makeText(this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Lista lista = null;
                        ListaDeletar deletar = new ListaDeletar(this);
                        deletar.execute(pontoTuristico.getId(),isId);
                        lista = deletar.get();
                        if (lista == null) {
                            Toast.makeText(this, "Infelizmente não foi possivel Remover da sua lista", Toast.LENGTH_LONG).show();
                        }
                        btnAdd.setText("Adicionar");
                        Toast.makeText(this, "Removido com sucesso", Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
