package br.ufmg.coltec.rest.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a2016951600 on 28/06/18.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UsersService getUsersService() {
        return this.retrofit.create(UsersService.class);
    }

}
