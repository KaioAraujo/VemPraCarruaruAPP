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

import br.com.vempracaruaru.obra.Obra;

/**
 * Created by joao on 25/03/16.
 */
public class AdapterListaObras extends BaseAdapter {

    private Context ctx;
    private ArrayList<Obra> lista;

    public AdapterListaObras(Context ctx, ArrayList<Obra> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //aqui eu pego o item da minha lista
        Obra obra = lista.get(position);

        ViewHolder holder = null;
        if(convertView == null){//View novo temos que criala
            convertView = LayoutInflater.from(ctx).inflate(R.layout.itens_lista_obras_layout,null);
            holder = new ViewHolder();
            holder.imgObra = (ImageView) convertView.findViewById(R.id.imv_imagem_obra);
            holder.txtObra = (TextView) convertView.findViewById(R.id.txv_obra_lista_titulo);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

//        File image = new File("/storage/emulated/0/01.jpeg");
//
//        if(image.exists() == true) {
//            Bitmap bitmapImage = BitmapFactory.decodeFile(image.getAbsolutePath());
//            holder.imgObra.setImageBitmap(bitmapImage);
//        }else {
//            holder.imgObra.setImageResource(R.drawable.teste);
//        }

        holder.imgObra.setImageResource(R.drawable.teste);
        holder.txtObra.setText(obra.getNome()+"");

        return convertView;
    }
    static class ViewHolder{
        ImageView imgObra;
        TextView txtObra;
    }
}
