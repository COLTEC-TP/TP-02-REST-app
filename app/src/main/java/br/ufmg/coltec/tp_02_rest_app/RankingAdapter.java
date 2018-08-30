package br.ufmg.coltec.tp_02_rest_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rankingAdapter.RankingDAO;
import restapi.rankingModel.MusicaRank;

public class RankingAdapter extends BaseAdapter{
    private ArrayList<MusicaRank> ranking;
    private Context context;

    public RankingAdapter(Context context){
        this.context = context;
        RankingDAO DAO = RankingDAO.getInstance(context);
        ranking = DAO.getRanking();
    }


    public RankingAdapter(Context context, ArrayList<MusicaRank> rank){
        this.context = context;
        ranking = rank;
        //... seta o ListView com um Array de itens arbitrário
    }

    @Override
    public int getCount() {
        return this.ranking.size();
    };

    @Override
    public Object getItem(int i) {
        return this.ranking.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        MusicaRank item = this.ranking.get(i);
        View newView  = LayoutInflater.from(this.context).inflate(R.layout.ranking_adapter, viewGroup, false);

        TextView info1 = newView.findViewById(R.id.musica_rank);
        TextView info2 = newView.findViewById(R.id.artista_rank);
        TextView info3 = newView.findViewById(R.id.posicao_rank);

        info1.setText(item.getName());
        info2.setText(item.getArt().getName());
        info3.setText((i+1)+"º");
        return newView;
    }

}
