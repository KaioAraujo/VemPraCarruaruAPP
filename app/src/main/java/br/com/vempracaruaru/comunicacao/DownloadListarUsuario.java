package br.com.vempracaruaru.comunicacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import br.com.vempracaruaru.usuario.Usuario;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

/**
 * Created by Kaio César on 06/06/2016.
 */
public class DownloadListarUsuario extends AsyncTask<String, String, Usuario> {
    private Context context;
    private ProgressDialog progressDialog;
    public static ConfigSistema cfgs = new ConfigSistema();

    public DownloadListarUsuario(Context contexto){
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
    protected Usuario doInBackground(String... params) {
        String email = params[0];
        Usuario usuario = null;
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
            oos.writeObject(new Solicitacao(Solicitacao.iUsuarioListar, "", "", "", email));
            //abre canal de leitura
            ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
            //recebe objeto
            Serializable obTeste = (Serializable) ois.readObject();
            usuario = (Usuario) obTeste;
            //imprime log
            if (usuario != null) {
                Log.i("ListaArtistaActivity", "RETORNO:> " + usuario.getNome());
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
        return usuario;
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        //progressDialog.setMessage("Finalizado!");
        //progressDialog.dismiss();
        super.onPostExecute(usuario);
    }
}
