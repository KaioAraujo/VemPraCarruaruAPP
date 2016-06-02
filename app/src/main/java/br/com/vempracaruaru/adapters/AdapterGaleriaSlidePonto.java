package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.util.ConfigSistema;


/**
 * Created by Administrador on 01/04/2016.
 */
public class AdapterGaleriaSlidePonto extends PagerAdapter{

    Context context;
    PontoTuristico pontoTuristico;
    public static ConfigSistema cfgs = new ConfigSistema();

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
        Picasso.with(context).load(cfgs.URL_IMAGENS+pontoTuristico.getListaFotoPonto().get(position).getImagem()).into(imagem);
        ((ViewPager) pager).addView(imagem, 0);
        return imagem;
    }
}
