package br.com.vempracaruaru.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.foto.Foto;
import br.com.vempracaruaru.obra.Obra;

/**
 * Created by joao on 25/03/16.
 */
public class AdapterGaleriaObra extends BaseAdapter {

    Context ctx;
    ArrayList<Foto> lista;

    public AdapterGaleriaObra(ArrayList<Foto> lista, Context ctx) {
        this.lista = lista;
        this.ctx = ctx;
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
        Foto foto = lista.get(position);

        ViewHolder holder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.itens_galeria_obra,null);
            holder = new ViewHolder();

            holder.imgItem = (ImageView) convertView.findViewById(R.id.imv_item_galeria);
            //set imagem
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    static class ViewHolder{
        ImageView imgItem;
    }
}
