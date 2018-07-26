package br.tp.tp_rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/") // Url do serviço
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService service = retrofit.create(PokemonService.class);

    }

    public PokemonService getPersonagemService() {
        return this.retrofit.create(PokemonService.class);
    }

}