package br.com.vempracaruaru.comunicacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

/**
 * Created by Kaio César on 17/05/2016.
 */
public class DownloadListarArtista extends AsyncTask<Integer, String, ArrayList<Artista>> {
    private Context context;
    private ProgressDialog progressDialog;
    public static ConfigSistema cfgs = new ConfigSistema();

    public DownloadListarArtista (Context contexto){
        this.context = contexto;
    }

    @Override
    protected void onPreExecute() {
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("Aguarde!");
//        progressDialog.setMessage("Carregando lista de artistas...");
//        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Artista> doInBackground(Integer... params) {
        Integer idArtista = params[0];
        ArrayList<Artista> artistas = new ArrayList<Artista>();
        try {

            URL url = new URL(cfgs.URL);
            HttpURLConnection http = null;
            http = (HttpURLConnection) url.openConnection();
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
            } else {
                Log.i("ListaArtistaActivity", "RETORNO:> Não há artistas cadastrados!");
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return artistas;
    }

    @Override
    protected void onPostExecute(ArrayList<Artista> artistas) {
        //progressDialog.setMessage("Finalizado!");
        //progressDialog.dismiss();
        super.onPostExecute(artistas);
    }
}
