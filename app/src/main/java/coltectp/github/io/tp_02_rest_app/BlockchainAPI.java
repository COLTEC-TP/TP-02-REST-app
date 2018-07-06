package coltectp.github.io.tp_02_rest_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BlockchainAPI {
    @GET("charts/n-transactions-per-block?timespan={tempo}weeks&rollingAverage=8hours&format=json")
    public Call<Chart> getTransactionsPerBlock(@Path("tempo") String tempo);
}
