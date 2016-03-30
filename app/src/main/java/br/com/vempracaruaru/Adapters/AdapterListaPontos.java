package br.com.vempracaruaru.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;

import br.com.vempracaruaru.pontoturistico.PontoTuristico;

/**
 * Created by Administrador on 30/03/2016.
 */
public class AdapterListaPontos extends BaseAdapter{

    private Context ctx;
    private ArrayList<PontoTuristico> listaponto;


    public AdapterListaPontos(Context ct, ArrayList<PontoTuristico> listaponto){
        this.ctx = ctx;
        this.listaponto = listaponto;

    }

    @Override
    public int getCount() {
        return listaponto.size();
    }

    @Override
    public Object getItem(int position) {
        return listaponto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PontoTuristico pontoTuristico = listaponto.get(position);

        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.lista_ponto_layout,null);
            holder = new ViewHolder();
            holder.imgPonto = (ImageView) convertView.findViewById(R.id.imv_imagem_ponto);
            holder.txtPonto = (TextView) convertView.findViewById(R.id.txv_ponto_lista_titulo);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imgPonto.setImageResource(R.drawable.pontoteste);
        holder.txtPonto.setText(pontoTuristico.getNome()+"");

        return convertView;
    }

    static class ViewHolder{
        ImageView imgPonto;
        TextView txtPonto;
    }
}
