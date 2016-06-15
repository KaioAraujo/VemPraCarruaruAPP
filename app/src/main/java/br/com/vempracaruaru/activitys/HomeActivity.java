package br.com.vempracaruaru.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.comunicacao.DownloadListarArtista;
import br.com.vempracaruaru.comunicacao.DownloadListarObra;
import br.com.vempracaruaru.comunicacao.DownloadListarPontoTuristico;
import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;
    private SharedPreferences sharedPrefEmail;
    private SharedPreferences sharedPrefUsuario;
    private String isEmail;
    private ArrayList<Artista> artistas;
    private ArrayList<Obra> obras;
    private ArrayList<PontoTuristico> pontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        ImageButton btnPonto = (ImageButton) findViewById(R.id.ibt_pontos_turisticos);
        ImageButton btnArtista = (ImageButton) findViewById(R.id.ibt_artistas);
        ImageButton btnObras = (ImageButton) findViewById(R.id.ibt_obras);
        ImageButton btnConta = (ImageButton) findViewById(R.id.ibt_minha_conta);
        ImageButton btnScan = (ImageButton) findViewById(R.id.ibt_scan);
        ImageButton btnSair = (ImageButton) findViewById(R.id.ibt_sair);

        btnPonto.setOnClickListener(this);
        btnArtista.setOnClickListener(this);
        btnObras.setOnClickListener(this);
        btnConta.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        btnSair.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPrefEmail = getSharedPreferences("LOGIN", 0);
        String isEmail = sharedPrefEmail.getString("email", null);

        if(isEmail == null) {
            finish();
        }
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
            case R.id.ibt_scan:
                scan();
                break;
            case R.id.ibt_sair:
                sharedPrefEmail = getSharedPreferences("LOGIN", 0);
                SharedPreferences.Editor editorEmail = sharedPrefEmail.edit();
                editorEmail.putString("email", null);
                editorEmail.commit();

                sharedPrefUsuario = getSharedPreferences("USUARIO", 0);
                SharedPreferences.Editor editorUsuario = sharedPrefUsuario.edit();
                editorUsuario.putInt("idUsuario", 0);
                editorUsuario.commit();

                Intent itsLogin = new Intent(this,LoginActivity.class);
                startActivity(itsLogin);
                break;

        }
    }

    private void scan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null){
            if(result.getContents() == null){
//                Log.d("Main","scan cancelado");
//                Toast.makeText(this,"cancelado: "+result.getContents(),Toast.LENGTH_LONG).show();
            }else {
                String retorno = result.getContents();
                String id = retorno.substring(retorno.indexOf('=') + 1);
                if (retorno.contains("artistasDetalhes.jsp")){

                    try {
                        DownloadListarArtista downloadArtista = new DownloadListarArtista(HomeActivity.this);
                        downloadArtista.execute(Integer.parseInt("" + id));
                        artistas = downloadArtista.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.d("SCANNER", "TAMANHO DO ARRAY " + artistas.size());
                    Log.d("SCANNER", "DADOS DO ARTISTA " + artistas.get(0).toString());
                    Intent its = new Intent(this, PerfilArtistaActivity.class);
                    Artista artista = artistas.get(0);
                    Log.d("SCANNER", "DADOS DO ARTISTA 2 " + artista);
                    its.putExtra("artista", artista);
                    startActivity(its);
                } else if (retorno.contains("obrasDetalhes.jsp")) {
                    try {
                        DownloadListarObra downloadObra = new DownloadListarObra(HomeActivity.this);
                        downloadObra.execute(0);
                        obras = downloadObra.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.d("SCANNER", "TAMANHO DO ARRAY " + obras.size());
                    Log.d("SCANNER", "DADOS DO OBRA " + obras.get(0).toString());
                    Intent its = new Intent(this, MenuObraActivity.class);
                    Obra obra = null;

                    for (Obra obra1 : obras) {
                        if (obra1.getId() == Integer.parseInt("" + id)) {
                            obra = obra1;
                            break;
                        }
                    }

                    Log.d("SCANNER", "DADOS DO OBRA 2 " + obra);
                    its.putExtra("obra", obra);
                    startActivity(its);

                } else if (retorno.contains("pontosTuristicosDetalhes.jsp")) {

                    try {
                        DownloadListarPontoTuristico downloadPonto = new DownloadListarPontoTuristico(HomeActivity.this);
                        downloadPonto.execute(Integer.parseInt("" + id));
                        pontos = downloadPonto.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.d("SCANNER", "TAMANHO DO ARRAY " + pontos.size());
                    Log.d("SCANNER", "DADOS DO PONTO " + pontos.get(0).toString());
                    Intent its = new Intent(this, MenuPontoTuristicoActivity.class);
                    PontoTuristico ponto = pontos.get(0);
                    Log.d("SCANNER", "DADOS DO PONTO 2 " + ponto);
                    its.putExtra("pontoTuristico", ponto);
                    startActivity(its);

                } else {
                    Toast.makeText(this,"QR CODE inválido!!!",Toast.LENGTH_LONG).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void cadastro(String url){

    }
}
