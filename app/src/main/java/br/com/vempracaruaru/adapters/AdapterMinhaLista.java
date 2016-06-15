package br.com.vempracaruaru.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.joao.vempracaruaruapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.com.vempracaruaru.comunicacao.DownloadListarListaAVisitar;
import br.com.vempracaruaru.comunicacao.DownloadListarListaVisitado;
import br.com.vempracaruaru.lista.Lista;

/**
 * Created by joao on 07/06/16.
 */
public class AdapterMinhaLista extends BaseExpandableListAdapter {

    private Context ctx;
    private Map<String,List<Lista>> listas;
    private ArrayList<String> keys;


    public AdapterMinhaLista(Context ctx, Map<String,List<Lista>> lista) {
        this.ctx = ctx;
        this.listas = lista;
        this.keys = new ArrayList<String>(listas.keySet());
    }

    @Override
    public Object getGroup(int groupPosition) {
        return keys.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return listas.size();
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(
                    parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1,null);
        }

        TextView txt = (TextView) convertView.findViewById(android.R.id.text1);
        txt.setTextColor(Color.WHITE);
        txt.setBackgroundColor(Color.RED);
        txt.setTextSize(24);
        if(keys.get(groupPosition).equals("N")){
            txt.setText("Pontos não visitados ("+listas.get("N").size()+")");
        }else{
            txt.setText("Pontos já visitados ("+listas.get("S").size()+")");

        }
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listas.get(keys.get(groupPosition)).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(android.R.layout.simple_expandable_list_item_1,null);
        }
        TextView txt = (TextView) convertView.findViewById(android.R.id.text1);
        txt.setText(listas.get(keys.get(groupPosition)).get(childPosition).getNomePontoTuristico());
        txt.setTextSize(20);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listas.get(keys.get(groupPosition)).size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
