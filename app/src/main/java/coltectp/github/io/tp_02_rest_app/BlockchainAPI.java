package coltectp.github.io.tp_02_rest_app;

import java.util.Map;

import coltectp.github.io.tp_02_rest_app.blockExplorer.Block;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlockList;
import coltectp.github.io.tp_02_rest_app.charts.Chart;
import coltectp.github.io.tp_02_rest_app.charts.pieChart.PoolChart;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface BlockchainAPI {

    /*
    * Group of transactions
    * */
    @GET("charts/n-transactions-per-block")
    public Call<Chart> getTransactionsPerBlock(@QueryMap Map<String, String> options);

    @GET("charts/median-confirmation-time")
    public Call<Chart> getMedianConfirmationTime(@QueryMap Map<String, String> options);

    @GET("charts/avg-block-size")
    public Call<Chart> getAverageBlockSize(@QueryMap Map<String, String> options);

    /*
    * Group of stats of coin
    * */
    @GET("charts/market-price")
    public Call<Chart> getMarketPrice(@QueryMap Map<String, String> options);

    @GET("charts/market-cap")
    public Call<Chart> getMarketCap(@QueryMap Map<String, String> options);

    @GET("charts/trade-volume")
    public Call<Chart> getTradeVolume(@QueryMap Map<String, String> options);

    /**
     * Group of Mining
     */
    @GET("charts/hash-rate")
    public Call<Chart> getHashRate(@QueryMap Map<String, String> options);

    @GET("charts/miners-revenue")
    public Call<Chart> getMinersRevenue(@QueryMap Map<String, String> options);

    @GET("charts/transaction-fees")
    public Call<Chart> getTransactionFees(@QueryMap Map<String, String> options);

    @GET("charts/cost-per-transaction")
    public Call<Chart> getCostPerTransaction(@QueryMap Map<String, String> options);

    @GET("pools")
    public Call<PoolChart> getPieChartPools(@QueryMap Map<String, String> options);

    /**
     * Group of searching in address specifically
     */
    @GET("blocks/{block}?format=json")
    public Call<SimpleBlockList> getBlocksBySpecificPool(
                                                         @Path("block") String block);

    @GET("rawblock/{blockHash}?format=json")
    public Call<Block> getSingleBlock(
                                      @Path("blockHash") String blockHash);

}
