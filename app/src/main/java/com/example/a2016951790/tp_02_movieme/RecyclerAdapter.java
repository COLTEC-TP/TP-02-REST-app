package com.example.a2016951790.tp_02_movieme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ListViewHolder) holder).bindView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieActivity.class).putExtra("id", filmes.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.filmes.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView mTitulo;
        private SmartImageView mCartaz;
        private TextView mRating;
        private TextView mAno;
        private TextView mGenero;

        public ListViewHolder(View itemView){

            super(itemView);
            mTitulo = itemView.findViewById(R.id.rcv_title);
            mCartaz = itemView.findViewById(R.id.rcv_photo);
            mRating = itemView.findViewById(R.id.rcv_grade);
            mAno = itemView.findViewById(R.id.rcv_release_date);
            mGenero = itemView.findViewById(R.id.rcv_gender);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            Filme filme = filmes.get(position);
            int[] generoint = filme.getGender();
            String genero = "";

            for(int i = 0; i < generoint.length && i < 3; i++) {
                if (generoint[i] == 28) {
                    genero = genero + "/" + context.getResources().getString(R.string.action);
                } else if (generoint[i] == 12) {
                    genero = genero + "/" + context.getResources().getString(R.string.adventure);
                } else if (generoint[i] == 16) {
                    genero = genero + "/" + context.getResources().getString(R.string.animation);
                } else if (generoint[i] == 35) {
                    genero = genero + "/" + context.getResources().getString(R.string.comedy);
                } else if (generoint[i] == 80) {
                    genero = genero + "/" + context.getResources().getString(R.string.crime);
                } else if (generoint[i] == 99) {
                    genero = genero + "/" + context.getResources().getString(R.string.documentary);
                } else if (generoint[i] == 18) {
                    genero = genero + "/" + context.getResources().getString(R.string.drama);
                } else if (generoint[i] == 10751) {
                    genero = genero + "/" + context.getResources().getString(R.string.family);
                } else if (generoint[i] == 14) {
                    genero = genero + "/" + context.getResources().getString(R.string.fantasy);
                } else if (generoint[i] == 36) {
                    genero = genero + "/" + context.getResources().getString(R.string.history);
                } else if (generoint[i] == 27) {
                    genero = genero + "/" + context.getResources().getString(R.string.horror);
                } else if (generoint[i] == 10402) {
                    genero = genero + "/" + context.getResources().getString(R.string.music);
                } else if (generoint[i] == 9648) {
                    genero = genero + "/" + context.getResources().getString(R.string.mystery);
                } else if (generoint[i] == 10749) {
                    genero = genero + "/" + context.getResources().getString(R.string.romance);
                } else if (generoint[i] == 878) {
                    genero = genero + "/" + context.getResources().getString(R.string.science_fiction);
                } else if (generoint[i] == 10770) {
                    genero = genero + "/" + context.getResources().getString(R.string.tv_movie);
                } else if (generoint[i] == 53) {
                    genero = genero + "/" + context.getResources().getString(R.string.thriller);
                } else if (generoint[i] == 10752) {
                    genero = genero + "/" + context.getResources().getString(R.string.war);
                } else if (generoint[i] == 37) {
                    genero = genero + "/" + context.getResources().getString(R.string.western);
                }

            }

            genero = genero.replaceFirst("/", "");

            mTitulo.setText(filme.getTitulo());
            if (filme.getRating().length() == 1){
                mRating.setText(filme.getRating() + ".0");
            } else {
                mRating.setText(filme.getRating());
            }
            mGenero.setText(genero);
            mAno.setText(context.getResources().getString(R.string.year) + ": " + filme.getAno().substring(0,4));
            mCartaz.setImageUrl("https://image.tmdb.org/t/p/original" + filme.getFoto());

        }

        @Override
        public void onClick(View view) {

        }
    }
}
