package com.gui_rei.tempopreparar.rest.city;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.gui_rei.tempopreparar.rest.RetrofitConfig;

//api/v1/locale/city?name=juiz%20de%20fora&state=MG&token=1300a7ef84802f53402e16f9505ae2be
public interface BuscaCidadeService {
    @GET("api/v1/locale/city?token=" + RetrofitConfig.TOKEN)
    public Call<BuscaCidade> getCidadeByName(@Query("name") String cityName);

    @GET("api/v1/locale/city?token=" + RetrofitConfig.TOKEN)
    public Call<BuscaCidade> getCidadeByState(@Query("state") String stateName);

}