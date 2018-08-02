package zen.tp02teste;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AddressService getEnderecoService() {
        return this.retrofit.create(AddressService.class);
    }
}
