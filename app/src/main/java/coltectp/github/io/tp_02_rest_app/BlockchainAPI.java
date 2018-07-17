package coltectp.github.io.tp_02_rest_app;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BlockchainAPI {
    @GET("charts/n-transactions-per-block")
    public Call<Chart> getTransactionsPerBlock(@QueryMap Map<String, String> options);
}
