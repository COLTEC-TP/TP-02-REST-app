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

import org.w3c.dom.Text;

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
        ArrayList<Stat> stats = pokemon.getStats();

        viewHolder.ataque.setText("ATQ " + String.valueOf(stats.get(0).getBase_stat()));
        viewHolder.defesa.setText("DEF " + String.valueOf(stats.get(1).getBase_stat()));
        viewHolder.agilidade.setText("SPD " + String.valueOf(stats.get(2).getBase_stat()));
        viewHolder.hp.setText("HP " + String.valueOf(stats.get(3).getBase_stat()));
        viewHolder.super_ataque.setText("SAT " + String.valueOf(stats.get(4).getBase_stat()));
        viewHolder.super_defesa.setText("SDE " + String.valueOf(stats.get(5).getBase_stat()));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView nome;
        final TextView ataque;
        final TextView defesa;
        final TextView agilidade;
        final TextView hp;
        final TextView super_ataque;
        final TextView super_defesa;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.Pokemon_Nome);
            ataque = (TextView) itemView.findViewById(R.id.Ataque_Num);
            defesa = (TextView) itemView.findViewById(R.id.Defesa_Num);
            agilidade = (TextView) itemView.findViewById(R.id.Agilidade_Num);
            hp = (TextView) itemView.findViewById(R.id.HP_Num);
            super_ataque = (TextView) itemView.findViewById(R.id.Ataque_Especial_Num);
            super_defesa = (TextView) itemView.findViewById(R.id.Defesa_Especial_Num);

        }
    }

    public void atualiza(ArrayList<Pokemon> pokemons){
        this.pokemons = pokemons;
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