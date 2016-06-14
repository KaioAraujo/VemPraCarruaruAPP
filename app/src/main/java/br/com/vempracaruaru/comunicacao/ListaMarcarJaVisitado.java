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

import br.com.vempracaruaru.lista.Lista;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

/**
 * Created by Kaio César on 14/06/2016.
 */
public class ListaMarcarJaVisitado extends AsyncTask<Integer, String, Lista> {
    private Context context;
    private ProgressDialog progressDialog;
    public static ConfigSistema cfgs = new ConfigSistema();

    public ListaMarcarJaVisitado(Context contexto){
        this.context = contexto;
    }

    @Override
    protected void onPreExecute() {

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("Aguarde!");
//        progressDialog.setMessage("Marcando Ponto Turístico como visitado...");
//        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Lista doInBackground(Integer... params) {

        Integer idPonto = params[0];
        Integer idUsuario = params[1];
        Lista lista = new Lista(idUsuario,idPonto,"",'S');
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
            oos.writeObject(new Solicitacao(Solicitacao.iListaMarcarJaVisitado, "", "", "", lista));
            //abre canal de leitura
            ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
            //recebe objeto
            Serializable obTeste = (Serializable) ois.readObject();
            lista = (Lista) obTeste;
            //imprime log
            if (lista != null) {
                Log.i("ListaMarcarJaVisitado", "RETORNO:> " + lista.toString());
            } else {
                Log.i("ListaMarcarJaVisitado", "RETORNO:> Não foi possível marcar a lista!");
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
        return lista;
    }

    @Override
    protected void onPostExecute(Lista lista) {
//        progressDialog.setMessage("Finalizado!");
//        progressDialog.dismiss();
        super.onPostExecute(lista);
    }
}
