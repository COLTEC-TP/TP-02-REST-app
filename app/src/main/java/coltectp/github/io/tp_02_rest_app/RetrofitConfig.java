package coltectp.github.io.tp_02_rest_app;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private static final String CACHE_CONTROL = "Cache-Control";

    public RetrofitConfig(Context context) {
        BlockchainAPI service = getRetrofit(context).create(BlockchainAPI.class);
    }

    private static Retrofit getRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl("https://blockchain.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient(context))
                .build();
    }

    private static OkHttpClient getHttpClient(Context context) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .addInterceptor(getCacheInterceptor(context))
                .cache(getCache(context));

        return httpBuilder.build();
    }

    private static Interceptor getCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (!Util.isNetworkAvailable(context)) {
                    Request request = chain.request();

                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(1, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();

                    return chain.proceed(request);
                } else {
                    Response response = chain.proceed(chain.request());

                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxAge(1, TimeUnit.HOURS)
                            .build();

                    return response.newBuilder()
                            .header(CACHE_CONTROL, cacheControl.toString())
                            .build();
                }
            }
        };
    }

    private static Cache getCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    public BlockchainAPI getInfoBlockchain(Context context) {
        return getRetrofit(context).create(BlockchainAPI.class);
    }
}
