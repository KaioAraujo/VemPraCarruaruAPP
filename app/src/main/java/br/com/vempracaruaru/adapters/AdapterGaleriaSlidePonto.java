package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;


/**
 * Created by Administrador on 01/04/2016.
 */
public class AdapterGaleriaSlidePonto extends PagerAdapter{

    Context context;
    PontoTuristico pontoTuristico;


 public AdapterGaleriaSlidePonto(Context context, PontoTuristico pontoTuristico) {
     this.context=context;
     this.pontoTuristico = pontoTuristico;
    }

    @Override
    public int getCount() {
        return pontoTuristico.getListaFotoPonto().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup pager, int position) {
        ImageView imagem = new ImageView(context);
        imagem.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imagem.setImageResource(pontoTuristico.getListaFotoPonto().get(position));
        ((ViewPager) pager).addView(imagem, 0);
        return imagem;
    }
}
