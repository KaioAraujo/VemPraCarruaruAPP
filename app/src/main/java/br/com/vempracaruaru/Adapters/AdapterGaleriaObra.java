package br.com.vempracaruaru.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.foto.Foto;
import br.com.vempracaruaru.obra.Obra;

/**
 * Created by joao on 25/03/16.
 */
public class AdapterGaleriaObra extends BaseAdapter {

    private Context ctx;
    private Obra obra;

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
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(R.drawable.teste);

        return imageView;
    }
}
