package com.gui_rei.tempopreparar.rest.days;

import com.gui_rei.tempopreparar.rest.RetrofitConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DiasService {
    @GET("api/v1/forecast/locale/{cityid}/days/15?token=" + RetrofitConfig.TOKEN)
    public Call<Dias> getDias(@Path("cityid") String cityId);
}
