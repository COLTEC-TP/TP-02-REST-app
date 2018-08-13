package com.example.a2016951790.tp_02_movieme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2016951790 on 09/08/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {


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
        return DataTeste.titulo.length;
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
            mTitulo.setText(DataTeste.titulo[position]);
            mRating.setText(DataTeste.rating[position]);
            mDiretor.setText(DataTeste.diretor[position]);
            mAno.setText(DataTeste.ano[position]);
            mCartaz.setImageResource(DataTeste.movie[position]);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
