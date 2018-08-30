package br.tp.tp_rest;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.text.TextUtils.isEmpty;

public class PokemonAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Pokemon> pokemons;
    private ItemClickListener clickListener;

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
        ArrayList<PokeType> pokeTypes = pokemon.getPokeTypes();
        viewHolder.tipo_1.setText(String.valueOf(pokeTypes.get(0).getNamePokeType()));
        if (pokeTypes.size() > 1) { // Se possui mais de um tipo
            viewHolder.tipo_2.setText(String.valueOf(pokeTypes.get(1).getNamePokeType()));
        }else{
            viewHolder.tipo_2.setText("");
        }
        viewHolder.imagem.setImageBitmap(pokemon.getImagem());
        viewHolder.id.setText(String.valueOf(pokemon.getId()));

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView id;
        final TextView nome;
        final ImageView imagem;
        final TextView tipo_1;
        final TextView tipo_2;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.Lista_Pokemon_Nome);

            imagem = (ImageView) itemView.findViewById(R.id.Lista_Pokemon_Img);
            tipo_1 = (TextView) itemView.findViewById(R.id.Lista_Tipo1);
            tipo_2 = (TextView) itemView.findViewById(R.id.Lista_Tipo2);
            id = (TextView) itemView.findViewById(R.id.id_pokemon);
            itemView.setOnClickListener(this); //liga ao listener
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, Integer.parseInt(id.getText().toString()));//getAdapterPosition());
        }
    }

    public void atualiza(ArrayList<Pokemon> pokemons){
        this.pokemons = pokemons;
    }
}
