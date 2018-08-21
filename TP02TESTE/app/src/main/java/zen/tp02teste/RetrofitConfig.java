package zen.tp02teste;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a2016951669 on 02/08/18.
 */

public class RetrofitConfig {

    public static final String TAG = "RetrofitConfig";
    public static final String BASE_URL = "https://maps.googleapis.com/";

    private Context context;
    private Gson gson;
    private Cache cache;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public RetrofitConfig(Context context, File cacheDir) {
        this.context = context;

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Address.class, new AddressDeserializer());
        gsonBuilder.registerTypeAdapter(Results.class, new ResultsDeserializer());
        gsonBuilder.registerTypeAdapter(Geometry.class, new GeometryDeserializer());
        gsonBuilder.registerTypeAdapter(Location.class, new LocationDeserializer());
        this.gson = gsonBuilder.create();

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        this.setCache(new Cache(cacheDir, cacheSize));
        try {
            this.getCache().initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Interceptor networkInterceptor = chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(1, TimeUnit.MINUTES)
                    .build();

            response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build();

            return response;
        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.setOkHttpClient(new OkHttpClient.Builder()
                .cache(getCache())
                .addNetworkInterceptor(networkInterceptor)
                .addInterceptor(loggingInterceptor)
                .build());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(this.gson))
                .client(this.getOkHttpClient())
                .build();
    }

    public void clean() {
        if (this.getOkHttpClient() != null) {
            this.getOkHttpClient().dispatcher().cancelAll();
        }

        this.retrofit = null;

        if (this.getCache() != null) {
            try {
                this.getCache().evictAll();
            } catch (IOException e) {
                Log.e(TAG, "Error cleaning http cache");
            }
        }

        this.setCache(null);
    }

    public AddressService getAddressCachedService() {
        return this.retrofit.create(AddressService.class);
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
