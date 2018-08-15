package com.gui_rei.tempopreparar.rest.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gui_rei.tempopreparar.R;

import java.util.ArrayList;

public class BuscaCidadeListAdapter extends BaseAdapter {

    Context context;
    private ArrayList<BuscaCidade> cidadesEncontradas;

    public BuscaCidadeListAdapter(Context context,ArrayList<BuscaCidade> arrayList){
        this.context = context;
        this.cidadesEncontradas=arrayList;
    }

    @Override
    public int getCount() {
        return cidadesEncontradas.size();
    }
    @Override
    public Object getItem(int i) {
        return cidadesEncontradas.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BuscaCidade buscaCidade = (BuscaCidade) this.getItem(i);

        View itemListView = LayoutInflater.from(this.context).inflate(R.layout.listview_adapter_buscacidade, viewGroup, false);

        TextView texto = itemListView.findViewById(R.id.item_list_busca);

        //"   XX, Cidade"
        texto.setText("   " + buscaCidade.getState() + ", " + buscaCidade.getName());

        return itemListView;
    }

}
