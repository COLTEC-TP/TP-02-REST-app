package zen.tp02teste;

import android.content.res.Resources;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by a2016951669 on 02/08/18.
 */

public interface AddressService {

    @GET("maps/api/geocode/json")
    public Call<Address> getAddress(@Query("address") String address, @Query("key") String key);
}
