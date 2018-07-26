package br.tp.tp_rest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int i) {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

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
}
