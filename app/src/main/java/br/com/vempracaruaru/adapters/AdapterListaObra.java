package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.util.ConfigSistema;


/**
 * Created by joao on 26/03/16.
 */
public class AdapterListaObra extends BaseAdapter{

    private Context ctx;
    private ArrayList<Obra> lista;
    public static ConfigSistema cfgs = new ConfigSistema();

    public AdapterListaObra(Context ctx, ArrayList<Obra> lista) {
        this.ctx = ctx;
        this.lista = lista;
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

        //aqui eu pego o item da minha lista
        Obra obra = lista.get(position);

        ViewHolder holder = null;
        if(convertView == null){//View nova temos que criala
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_lista_obra_layout,null);
            holder = new ViewHolder();
            holder.imgObra = (ImageView) convertView.findViewById(R.id.img_item_lista);
            holder.txtObra = (TextView) convertView.findViewById(R.id.txt_titulo_artista);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(ctx)
                .load(cfgs.URL_IMAGENS + obra.getFoto()).into(holder.imgObra);
        holder.txtObra.setText(obra.getNome()+"");

        return convertView;
    }
    static class ViewHolder{
        ImageView imgObra;
        TextView txtObra;
    }
}
