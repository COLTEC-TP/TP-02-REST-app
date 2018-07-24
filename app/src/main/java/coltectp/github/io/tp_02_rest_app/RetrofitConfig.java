package coltectp.github.io.tp_02_rest_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://blockchain.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BlockchainAPI service = retrofit.create(BlockchainAPI.class);
    }

    public BlockchainAPI getInfoBlockchain() {
        return this.retrofit.create(BlockchainAPI.class);
    }
}
