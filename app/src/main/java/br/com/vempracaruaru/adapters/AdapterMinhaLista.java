package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.comunicacao.DownloadListarListaAVisitar;
import br.com.vempracaruaru.comunicacao.DownloadListarListaVisitado;
import br.com.vempracaruaru.lista.Lista;

/**
 * Created by joao on 07/06/16.
 */
public class AdapterMinhaLista extends BaseAdapter {

    private Context ctx;
    private int id;
    private ArrayList<Lista> pontosJaVisitados;
    private ArrayList<Lista> pontosNaovisitados;
    private ArrayList<Lista> lista = new ArrayList<>();


    public AdapterMinhaLista(Context ctx, int id) {
        this.ctx = ctx;
        this.id = id;
       recuperarPontosJaVisitados(id);
       recuperarPontosNaovisitados(id);
    }

    private void recuperarPontosNaovisitados(int id) {
        pontosNaovisitados = null;

        try {
            DownloadListarListaAVisitar download = new DownloadListarListaAVisitar(ctx);
            download.execute(id);
            pontosNaovisitados = download.get();
            Log.i("AdapterMinhaLista","não visitado");
            Log.i("AdapterMinhaLista","teste: " +(lista == null));
            if(pontosNaovisitados !=null){
                for (Lista item: pontosNaovisitados) {
                    item.setVisitado('N');
                    lista.add(item);
                    Log.i("AdapterMinhaLista","teste: " +item.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void recuperarPontosJaVisitados(int id) {
        pontosJaVisitados = null;

        try {
            DownloadListarListaVisitado download = new DownloadListarListaVisitado(ctx);
            download.execute(id);
            lista = download.get();
            Log.i("AdapterMinhaLista","ja visitado");
            Log.i("AdapterMinhaLista","teste: " +(lista == null));
            if(pontosJaVisitados !=null) {
                for (Lista item : pontosJaVisitados) {
                    item.setVisitado('S');
                    lista.add(item);
                    Log.i("AdapterMinhaLista","teste: " +item.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Lista item = lista.get(position);

        ViewHolder holder = null;
        if(convertView == null){//View nova temos que criala
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_layout,null);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkItem);
            holder.titulo = (TextView) convertView.findViewById(R.id.textoItem);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titulo.setText(item.getNomePontoTuristico());
        if('S' == item.getVisitado()){
            holder.checkBox.setChecked(true);
        }

        return convertView;
    }
    static class ViewHolder{
        CheckBox checkBox;
        TextView titulo;
    }
}
