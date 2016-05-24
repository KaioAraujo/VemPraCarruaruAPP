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

import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.util.ConfigSistema;


/**
 * Created by David on 01/04/2016.
 */
public class AdapterListaArtista extends BaseAdapter {

    private Context ctx;
    private ArrayList<Artista> lista;
    public static ConfigSistema cfgs = new ConfigSistema();

    public AdapterListaArtista(Context ctx, ArrayList<Artista> lista) {
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

        Artista artista = lista.get(position);

        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_lista_artista_layout,null);
            holder = new ViewHolder();
            holder.imgArtista = (ImageView) convertView.findViewById(R.id.img_lista_artista);
            holder.txtArtista = (TextView) convertView.findViewById(R.id.txt_titulo_lista_artista);
            holder.txtSubtitulo = (TextView) convertView.findViewById(R.id.txt_sub_titulo_artista);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //esqueceu de passar os dados para cada item da lista aqui falta as imgens vamos fazer
        //na proxima sprint
        Picasso.with(ctx)
                .load(cfgs.URL_IMAGENS + artista.getFoto()).into(holder.imgArtista);
        holder.txtArtista.setText(artista.getNome());
        holder.txtSubtitulo.setText(artista.getTipo());

	//vc tinha deixado null kkkk :)
	//return null;
        return convertView;
    }

    static class ViewHolder{
        ImageView imgArtista;
        TextView txtArtista;
        // falto o subTitulo
        TextView txtSubtitulo;
    }
}
