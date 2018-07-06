package br.tp.tp_rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PersonagemService {

    @GET("api/characters/{characterID}")
    public Call<Personagem> getEndereco(@Path("characterID") String characterID);

}
