package br.ufmg.coltec.tp_02_rest_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import restapi.artmusModel.MusDocs;
import searchAdapter.BuscaDAO;

public class TelaBuscaAdapter extends BaseAdapter{
    private ArrayList<MusDocs> busca;
    private Context context;

    public TelaBuscaAdapter(Context context){
        this.context = context;
        BuscaDAO DAO = BuscaDAO.getInstance(context);
        busca = DAO.getBusca();
    }


    public TelaBuscaAdapter(Context context, ArrayList<MusDocs> bus){
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
        MusDocs item = this.busca.get(i);
        View newView  = LayoutInflater.from(this.context).inflate(R.layout.tela_busca_adapter, viewGroup, false);

        TextView titulo = newView.findViewById(R.id.titulo_busca);
        TextView artista = newView.findViewById(R.id.artista_busca);

        titulo.setText(item.getTitle());
        artista.setText(item.getBand());


        return newView;
    }

}
