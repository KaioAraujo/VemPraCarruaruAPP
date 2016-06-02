package br.com.vempracaruaru.adapters;

import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;
import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.util.ConfigSistema;


/**
 * Created by joao on 26/03/16.
 */
public class AdapterGaleriaObra extends BaseAdapter {

    private Context ctx;
    private Obra obra;
    public static ConfigSistema cfgs = new ConfigSistema();


    public AdapterGaleriaObra(Obra obra, Context ctx) {
        this.obra = obra;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
       return obra.getListaFotos().size();
    }

    @Override
    public Object getItem(int position) {
        return obra.getListaFotos().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(ctx);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);

        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(ctx).load(cfgs.URL_IMAGENS+obra.getListaFotos().get(position).getImagem()).into(imageView);

        return imageView;
    }
}
