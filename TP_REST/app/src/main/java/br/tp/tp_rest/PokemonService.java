package br.tp.tp_rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {

    @GET("/api/v2/pokemon/{characterID}")
    public Call<Pokemon> getPokemon(@Path("characterID") String characterID);

}
