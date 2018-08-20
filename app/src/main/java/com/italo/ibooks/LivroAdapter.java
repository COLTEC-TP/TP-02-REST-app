package com.italo.ibooks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;


public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.MyViewHolder> {

    private Context mContext;
    private List<Livro> livroList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, autor; //titulo autor
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.title);
            autor = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

    }


    public LivroAdapter(Context mContext, List<Livro> livroList) {
        this.mContext = mContext;
        this.livroList = livroList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_livro, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = parent.indexOfChild(view);
                Livro returnedLivro = livroList.get(itemPosition);
                String infoUrl = returnedLivro.getInfoUrl();

                Intent intent = new Intent(mContext,livroListView.class);
                intent.putExtra("infoUrl", infoUrl);

                mContext.startActivity(intent);
            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Livro returnedLivro = livroList.get(position);
        holder.titulo.setText(returnedLivro.getTitulo());
        holder.autor.setText(returnedLivro.getAutor());

        // Carregando o livro usando Glide
        Glide.with(mContext).load(returnedLivro.getImageUrl()).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return livroList.size();
    }
}