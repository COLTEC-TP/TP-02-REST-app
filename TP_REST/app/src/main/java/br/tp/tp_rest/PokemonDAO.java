package br.tp.tp_rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDAO {

    private static int num_pokemons = 10;
    private ArrayList<Pokemon> pokemons;
    private Context context;

    // singleton para lidar com única instância do DAO
    private static PokemonDAO instance;


    public static PokemonDAO getInstance(Context context) {

        if(instance == null)
            instance = new PokemonDAO(context);

        return instance;
    }

    // Construtor Privado
    private PokemonDAO(Context context) {
        this.pokemons = new ArrayList<>();
        this.context = context;
    }


    // Carrega a lista inicial de pokemons
    public void carregarPokemons(final int pokemon_id, Callback<Pokemon> cb) {

        PokemonService service = new RetrofitConfig().getPersonagemService();
        Call<Pokemon> enderecoCall = service.getPokemon(String.valueOf(pokemon_id));

        // fazendo a requisição de forma assíncrona
        enderecoCall.enqueue(cb);
    }

    // Faz a filtragem dos pokemons por nome //

    public ArrayList<Pokemon> filtrarPokemons(String nome) {
        ArrayList<Pokemon> pokemonsFiltrados = new ArrayList<>();
        for (int i=0; i<num_pokemons; i++){
            if(pokemons.get(i).getName().toUpperCase().contains(nome.toUpperCase())){
                pokemonsFiltrados.add(pokemons.get(i));
            }
        }
        return pokemonsFiltrados;
    }


    // Recupera lista completa dos imóveis //
    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    // Adiciona Pokemon //
    public void addPokemon(Pokemon p){
        this.pokemons.add(p);
    }

    public int getNum_pokemons(){
        return num_pokemons;
    }

    public Context getContext() {
        return context;
    }
}
