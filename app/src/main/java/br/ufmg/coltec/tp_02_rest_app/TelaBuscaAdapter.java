package br.ufmg.coltec.tp_02_rest_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import restapi.artmusBusca.ArtMusDocs;
import searchAdapter.BuscaDAO;

public class TelaBuscaAdapter extends BaseAdapter{
    private ArrayList<ArtMusDocs> busca;
    private Context context;

    public TelaBuscaAdapter(Context context){
        this.context = context;
        BuscaDAO DAO = BuscaDAO.getInstance(context);
        busca = DAO.getBusca();
    }


    public TelaBuscaAdapter(Context context, ArrayList<ArtMusDocs> bus){
        this.context = context;
        busca = bus;
        //... seta o ListView com um Array de itens arbitrário
    }

    @Override
    public int getCount() {
        return this.busca.size();
    };

    @Override
    public Object getItem(int i) {
        return this.busca.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ArtMusDocs item = this.busca.get(i);
        View newView  = LayoutInflater.from(this.context).inflate(R.layout.tela_busca_adapter, viewGroup, false);

        TextView info1 = newView.findViewById(R.id.informacao_1);
        TextView info2 = newView.findViewById(R.id.informacao_2);

        if(item.getTitle()!=null){
            info1.setText(item.getTitle());
            info2.setText(item.getBand());
        }else{
            info1.setText(item.getBand());
            info2.setText("Ver discografia");
        }

        return newView;
    }

}
