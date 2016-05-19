package br.com.vempracaruaru.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.joao.vempracaruaruapp.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.adapters.AdapterListaArtista;
import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.comunicacao.DownloadListarArtista;
import br.com.vempracaruaru.usuario.Usuario;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

public class ListaArtistaActivity extends AppCompatActivity implements OnItemClickListener {

    public static ConfigSistema cfgs = new ConfigSistema();
    private ArrayList<Artista> artistas;
    private AdapterListaArtista adapter;
    private ListView lista;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Aguarde!");
        progressDialog.setMessage("Carregando lista de artistas...");
        progressDialog.show();

        lista = new ListView(this);
        lista.setOnItemClickListener(this);
        setContentView(lista);

        try {
            DownloadListarArtista downloadArtista = new DownloadListarArtista(ListaArtistaActivity.this);
            downloadArtista.execute(0);
            artistas = downloadArtista.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adapter = new AdapterListaArtista(this, artistas);
        lista.setAdapter(adapter);

        progressDialog.dismiss();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Artista artista = (Artista) parent.getItemAtPosition(position);

        Intent its = new Intent(this,PerfilArtistaActivity.class);
        its.putExtra("artista", artista);
        startActivity(its);
    }

    public void listar(Integer id) throws IOException, ClassNotFoundException {
        final Integer idArtista = id;
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(cfgs.URL);
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");
                    http.addRequestProperty("content-type", "application/binary");
                    http.setDoInput(true);
                    http.setDoOutput(true);
                    http.setConnectTimeout(20000);
                    http.connect();

                    //abre canal de saída
                    ObjectOutputStream oos = new ObjectOutputStream(http.getOutputStream());
                    //envia objeto
                    oos.writeObject(new Solicitacao(Solicitacao.iArtistaListar, "", "", "", idArtista));
                    //abre canal de leitura
                    ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
                    //recebe objeto
                    Serializable obTeste = (Serializable) ois.readObject();
                    artistas = (ArrayList<Artista>) obTeste;
                    //imprime log
                    if (artistas != null) {
                        Log.i("ListaArtistaActivity", "RETORNO:> " + artistas.size());
                        Message msg = handler.obtainMessage();
                        msg.arg1 = 1;
                        handler.sendMessage(msg);
                    } else {
                        Log.i("ListaArtistaActivity", "RETORNO:> Não há artistas cadastrados!");
                        Message msg = handler.obtainMessage();
                        msg.arg1 = 2;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Log.e("LoginActivity", "Erro do TRY " + e.getMessage());
                }
            }
        }.start();
    }

    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1) {
                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso!\nTamanho do Array: " + artistas.size(), Toast.LENGTH_LONG).show();
            } else if(msg.arg1 == 2) {
                Toast.makeText(getApplicationContext(), "Não foi possível efetuar listar os artistas, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
            }
        }
    };
}
