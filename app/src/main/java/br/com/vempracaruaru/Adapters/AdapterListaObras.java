package br.com.vempracaruaru.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.obra.Obra;

/**
 * Created by joao on 25/03/16.
 */
public class AdapterListaObras extends BaseAdapter {

    private Context ctx;
    private ArrayList<Obra> listaObras;

    public AdapterListaObras(ArrayList<Obra> listaObras, Context ctx) {
        this.listaObras = listaObras;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return listaObras.size();
    }

    @Override
    public Object getItem(int position) {
        return listaObras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Obra obra = listaObras.get(position);

        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.lista_obras_layout, null);
            holder = new ViewHolder();

            holder.imgObra = (ImageView) convertView.findViewById(R.id.imv_imagem_obra);
            holder.txvNomeObra = (TextView) convertView.findViewById(R.id.txv_obra_lista_titulo);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //retornar uma imagem
        holder.imgObra.setImageResource(obra.getFoto().charAt(0));
        holder.txvNomeObra.setText(obra.getNome());

        return convertView;
    }

    static class ViewHolder{
        ImageView imgObra;
        TextView txvNomeObra;
    }
}
