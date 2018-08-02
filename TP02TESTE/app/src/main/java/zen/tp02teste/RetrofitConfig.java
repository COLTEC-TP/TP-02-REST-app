package zen.tp02teste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;
    private final Gson gson;

    public RetrofitConfig() {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Address.class, new AddressDeserializer());
        gsonBuilder.registerTypeAdapter(Results.class, new ResultsDeserializer());
        gsonBuilder.registerTypeAdapter(Geometry.class, new GeometryDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        this.gson = gsonBuilder.create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .build();
    }

    public AddressService getEnderecoService() {
        return this.retrofit.create(AddressService.class);
    }
}
