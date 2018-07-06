package br.tp.tp_rest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://anapioficeandfire.com/") // Url do serviço
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PersonagemService service = retrofit.create(PersonagemService.class);

    }

    public PersonagemService getPersonagemService() {
        return this.retrofit.create(PersonagemService.class);
    }

}