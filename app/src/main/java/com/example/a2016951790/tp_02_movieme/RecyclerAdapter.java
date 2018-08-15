package com.example.a2016951790.tp_02_movieme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by a2016951790 on 09/08/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private List<Filme> filmes;
    private Context context;

    public RecyclerAdapter(Context c, List<Filme> p){
        this.context = c;
        this.filmes = p;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_holder, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return this.filmes.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mTitulo;
        private ImageView mCartaz;
        private TextView mRating;
        private TextView mAno;
        private TextView mDiretor;

        public ListViewHolder(View itemView){

            super(itemView);
            mTitulo = itemView.findViewById(R.id.rcv_title);
            mCartaz = itemView.findViewById(R.id.rcv_photo);
            mRating = itemView.findViewById(R.id.rcv_grade);
            mAno = itemView.findViewById(R.id.rcv_release_date);
            mDiretor = itemView.findViewById(R.id.rcv_director);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            Filme filme = filmes.get(position);
            Log.i(filme.getAno(), filme.getTitulo());
            mTitulo.setText(filme.getTitulo());
            mRating.setText(filme.getRating());
            mDiretor.setText(filme.getDiretor());
            mAno.setText(filme.getAno());
            mCartaz.setImageResource(DataTeste.movie[position]);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
