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

import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.util.ConfigSistema;
import br.com.vempracaruaru.util.Solicitacao;

/**
 * Created by Kaio César on 19/05/2016.
 */
public class DownloadListarPontoTuristico extends AsyncTask<Integer, String, ArrayList<PontoTuristico>> {
    private Context context;
    private ProgressDialog progressDialog;
    public static ConfigSistema cfgs = new ConfigSistema();

    public DownloadListarPontoTuristico(Context contexto){
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
    protected ArrayList<PontoTuristico> doInBackground(Integer... params) {
        Integer idPontoTuristico = params[0];
        ArrayList<PontoTuristico> pontoTuristicos = new ArrayList<PontoTuristico>();
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
            oos.writeObject(new Solicitacao(Solicitacao.iPontoTuristicoListar, "", "", "", idPontoTuristico));
            //abre canal de leitura
            ObjectInputStream ois = new ObjectInputStream(http.getInputStream());
            //recebe objeto
            Serializable obTeste = (Serializable) ois.readObject();
            pontoTuristicos = (ArrayList<PontoTuristico>) obTeste;
            //imprime log
            if (pontoTuristicos != null) {
                Log.i("ListaObraActivity", "RETORNO:> " + pontoTuristicos.size());
            } else {
                Log.i("ListaObraActivity", "RETORNO:> Não há obras cadastradas!");
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
        return pontoTuristicos;
    }

    @Override
    protected void onPostExecute(ArrayList<PontoTuristico> pontoTuristicos) {
        //progressDialog.setMessage("Finalizado!");
        //progressDialog.dismiss();
        super.onPostExecute(pontoTuristicos);
    }
}
