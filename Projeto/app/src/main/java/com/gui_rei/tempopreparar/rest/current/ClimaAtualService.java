package com.gui_rei.tempopreparar.rest.current;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.gui_rei.tempopreparar.rest.RetrofitConfig;

public interface ClimaAtualService {
    @GET("api/v1/weather/locale/{cityid}/current?token=" + RetrofitConfig.TOKEN)
    public Call<ClimaAtual> getClima(@Path("cityid") String cityId);

}
