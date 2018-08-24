package com.example.a2016951790.tp_02_movieme;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a2016951790 on 14/08/18.
 */

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(List.class, new FilmeDeserializer());
        final Gson gson = gsonBuilder.create();

        // configura o retrofit para um determinado serviço
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        FilmeService service = retrofit.create(FilmeService.class);
    }

    public FilmeService getFilmeService() {
        return this.retrofit.create(FilmeService.class);
    }
}
