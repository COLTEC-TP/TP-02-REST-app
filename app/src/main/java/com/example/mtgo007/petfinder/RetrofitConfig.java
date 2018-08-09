package com.example.mtgo007.petfinder;

/**
 * Created by a2016952827 on 07/08/18.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    // Objeto que fará o acesso a API do serviço
    private final Retrofit retrofit;

    public RetrofitConfig() {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(List.class, new PetDeserializer());
        final Gson gson = gsonBuilder.create();

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.petfinder.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PetService service = retrofit.create(PetService.class);
    }

    public PetService getPetService() {
        return this.retrofit.create(PetService.class);
    }
}