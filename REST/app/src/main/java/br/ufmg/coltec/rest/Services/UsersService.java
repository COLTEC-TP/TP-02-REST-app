package br.ufmg.coltec.rest.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by a2016951600 on 28/06/18.
 */

public interface UsersService {
    @GET("latest")
    Call<UltimasTaxas> getLatest(@Query("access_key") String access_key);

}
