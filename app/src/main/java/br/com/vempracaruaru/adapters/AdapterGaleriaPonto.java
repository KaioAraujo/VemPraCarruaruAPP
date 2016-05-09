package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;


/**
 * Created by Administrador on 01/04/2016.
 */
public class AdapterGaleriaPonto extends BaseAdapter {

    private Context context;
    //aqui recebe um ponto e pegamos a lista de fotos dele
    //com o getListaFotos
    private PontoTuristico ponto;

    public AdapterGaleriaPonto(Context context, PontoTuristico ponto) {
        this.context = context;
        this.ponto = ponto;
    }



    @Override
    public int getCount() { return ponto.getListaFotoPonto().size();
    }

    @Override
    public Object getItem(int position) {return ponto.getListaFotoPonto().get(position);
    }

    @Override
    public long getItemId(int position) { return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(R.drawable.pontoteste);

        return imageView;
    }
}
