package com.gui_rei.tempopreparar.rest;

import com.gui_rei.tempopreparar.rest.city.BuscaCidadeService;
import com.gui_rei.tempopreparar.rest.current.ClimaAtualService;
import com.gui_rei.tempopreparar.rest.days.DiasService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static final String TOKEN = "1300a7ef84802f53402e16f9505ae2be";
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://apiadvisor.climatempo.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ClimaAtualService getClimaAtualService() {
        return this.retrofit.create(ClimaAtualService.class);
    }

    public BuscaCidadeService getBuscaCidadeService() {
        return this.retrofit.create(BuscaCidadeService.class);
    }

    public DiasService getDDiasService(){
        return this.retrofit.create(DiasService.class);
    }

}
