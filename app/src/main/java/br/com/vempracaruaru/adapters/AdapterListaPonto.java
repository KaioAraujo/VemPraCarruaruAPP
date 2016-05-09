package br.com.vempracaruaru.adapters;

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
 * Created by Administrador on 01/04/2016.
 */
public class AdapterListaPonto extends BaseAdapter{

    private Context ctx;
    private ArrayList<PontoTuristico> pontolist;


    public AdapterListaPonto(Context context, ArrayList<PontoTuristico> ponto) {
        this.ctx = context;
        this.pontolist = ponto;
    }

    @Override
    public int getCount() { return pontolist.size();
    }

    @Override
    public Object getItem(int position) {
        return pontolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PontoTuristico pontoTuristico = pontolist.get(position);
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_lista_ponto_turistico_layout,null);
            holder = new ViewHolder();
            holder.imgponto = (ImageView) convertView.findViewById(R.id.imv_imagem_ponto);
            holder.txtponto = (TextView) convertView.findViewById(R.id.txv_ponto_lista_titulo);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgponto.setImageResource(R.drawable.pontoteste);
        holder.txtponto.setText(pontoTuristico.getNome());

        return convertView;
    }

    static class ViewHolder{
        ImageView imgponto;
        TextView txtponto;
    }
}
