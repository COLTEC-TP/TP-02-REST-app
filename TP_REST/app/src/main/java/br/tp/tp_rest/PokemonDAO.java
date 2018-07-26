package br.tp.tp_rest;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDAO {

    private static int num_pokemons = 151;
    private ArrayList<Pokemon> pokemons;

    // singleton para lidar com única instância do DAO
    private static PokemonDAO instance;

    // Construtor Privado
    private PokemonDAO() {
        pokemons = new ArrayList<>();
        carregarPokemons();
    }

    public static PokemonDAO getInstance() {

        if(instance == null)
            instance = new PokemonDAO();

        return instance;
    }

    // Carrega a lista inicial de pokemons
    private void carregarPokemons() {

        PokemonService service = new RetrofitConfig().getPersonagemService();

        for (int i = 1; i < num_pokemons; i++) {
            Call<Pokemon> enderecoCall = service.getPokemon(String.valueOf(i));

            // fazendo a requisição de forma assíncrona
            enderecoCall.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    pokemons.add(new Pokemon(pokemon.getName()));
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
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
        return instance.pokemons;
    }
}
