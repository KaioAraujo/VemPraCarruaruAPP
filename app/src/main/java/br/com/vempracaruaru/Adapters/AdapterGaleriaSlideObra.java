package br.com.vempracaruaru.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.obra.Obra;


/**
 * Created by joao on 29/03/16.
 */
public class AdapterGaleriaSlideObra extends BaseAdapter{

    private Context ctx;
    private Obra obra;

    public AdapterGaleriaSlideObra(Context ctx, Obra obra) {
        this.ctx = ctx;
        this.obra = obra;
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
        ImageView img;
        if(convertView == null) {
          img  =new ImageView(ctx);
        }else{
            img = (ImageView) convertView;
        }
        img.setImageResource(R.drawable.teste);
        return img;
    }
}
