package br.tp.tp_rest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter(ArrayList<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_pokemons, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        Pokemon pokemon = pokemons.get(position);

        viewHolder.nome.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView nome;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.Pokemon_Nome);
        }
    }
}

/*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Pokemon pokemon = (Pokemon) this.getItem(i);

        // Mecanismo de estilização da lista //
        View newView = LayoutInflater.from(this.context).inflate(R.layout.list_pokemons, viewGroup, false);

        TextView nome = newView.findViewById(R.id.lbl_nome);
        nome.setText(pokemon.getName());

        notifyDataSetChanged();

        return newView;
    }
*/