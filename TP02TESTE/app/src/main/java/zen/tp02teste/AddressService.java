package zen.tp02teste;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by a2016951669 on 02/08/18.
 */

public interface AddressService {

    @GET("maps/api/geocode/json?key=AIzaSyC0Ovirs8akz3HLbseYkPIr--ZTh8q0q80")
    public Call<Address> getAddress(@Query("address") String address);
}
