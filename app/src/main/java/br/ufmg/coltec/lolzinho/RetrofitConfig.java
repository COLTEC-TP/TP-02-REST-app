package br.ufmg.coltec.lolzinho;

/**
 * Created by a2016951782 on 02/08/18.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Data.class, new DataDeserializer());
        final Gson gson = gsonBuilder.create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://br1.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public SummonerService getSummonerService() {
        return this.retrofit.create(SummonerService.class);
    }

}
