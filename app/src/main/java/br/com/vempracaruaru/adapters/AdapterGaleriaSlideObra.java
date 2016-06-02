package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.util.ConfigSistema;


/**
 * Created by joao on 29/03/16.
 */
public class AdapterGaleriaSlideObra extends PagerAdapter{

    private Context ctx;
    private Obra obra;
    public static ConfigSistema cfgs = new ConfigSistema();

    public AdapterGaleriaSlideObra(Context ctx, Obra obra) {
        this.ctx = ctx;
        this.obra = obra;
    }

    @Override
    public int getCount() {
        return obra.getListaFotos().size();
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
        ImageView imagem = new ImageView(ctx);
        imagem.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(ctx).load(cfgs.URL_IMAGENS+obra.getListaFotos().get(position).getImagem()).into(imagem);
        ((ViewPager) pager).addView(imagem, 0);
        return imagem;
    }
}
