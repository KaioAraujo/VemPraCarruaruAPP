package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.joao.vempracaruaruapp.R;

import br.com.vempracaruaru.obra.Obra;


/**
 * Created by joao on 29/03/16.
 */
public class AdapterGaleriaSlideObra extends PagerAdapter{

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
        imagem.setImageResource(obra.getListaFotos().get(position));
        ((ViewPager) pager).addView(imagem, 0);
        return imagem;
    }
}
