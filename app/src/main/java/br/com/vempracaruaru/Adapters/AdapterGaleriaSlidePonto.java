package br.com.vempracaruaru.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;


/**
 * Created by Administrador on 01/04/2016.
 */
public class AdapterGaleriaSlidePonto extends BaseAdapter{

    Context context;
    PontoTuristico pontoTuristico;


 public AdapterGaleriaSlidePonto(Context context, PontoTuristico pontoTuristico) {
     this.context=context;
     this.pontoTuristico = pontoTuristico;
    }


    @Override
    public int getCount() {return pontoTuristico.getListaFotoPonto().size();
    }

    @Override
    public Object getItem(int position) {return pontoTuristico.getListaFotoPonto().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null) {
            imageView  =new ImageView(context);
        }else{
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(R.drawable.pontoteste);
        return imageView;
    }
}
