package com.example.mtgo007.petfinder;

/**
 * Created by a2016952827 on 07/08/18.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    // Objeto que fará o acesso a API do serviço
    private final Retrofit retrofit;

    public RetrofitConfig() {

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.petfinder.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PetService service = retrofit.create(PetService.class);
    }

    public PetService getPetService() {
        return this.retrofit.create(PetService.class);
    }
}