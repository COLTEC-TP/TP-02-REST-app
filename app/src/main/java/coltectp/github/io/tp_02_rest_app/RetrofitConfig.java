package coltectp.github.io.tp_02_rest_app;

import android.content.Context;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    private final int cacheSize = 10 * 1024 * 1024; // 10 MB


    public RetrofitConfig(Context context) {

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");

        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://blockchain.info/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BlockchainAPI service = retrofit.create(BlockchainAPI.class);
    }

    public BlockchainAPI getInfoBlockchain() {
        return this.retrofit.create(BlockchainAPI.class);
    }
}
