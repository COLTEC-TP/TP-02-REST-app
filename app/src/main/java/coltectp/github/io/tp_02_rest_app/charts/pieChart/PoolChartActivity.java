package coltectp.github.io.tp_02_rest_app.charts.pieChart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoolChartActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_chart_acitivity);

        BlockchainAPI service = new RetrofitConfig().getInfoBlockchain();

        Map<String, String> data = new HashMap<>();
        data.put("format", "json");
        Call<PoolChart> pieChartPoolsCall = service.getPieChartPools(data);

        pieChartPoolsCall.enqueue(new Callback<PoolChart>() {
            @Override
            public void onResponse(Call<PoolChart> call, Response<PoolChart> response) {
                PoolChart poolChart = response.body();
                Map<String, Integer> dataset = new HashMap<String, Integer>();

                dataset.put("BTC.com", poolChart.getBTCCom());
                dataset.put("F2Pool", poolChart.getF2Pool());
                dataset.put("AntPool", poolChart.getAntPool());
                dataset.put("Bixin", poolChart.getBixin());
                dataset.put("ViaBTC", poolChart.getViaBTC());
                dataset.put("SlushPool", poolChart.getSlushPool());
                dataset.put("Unknown", poolChart.getUnknown());
                dataset.put("BTC.TOP", poolChart.getBTCTOP());
                dataset.put("DPOOL", poolChart.getDPOOL());
                dataset.put("Bitcoin.com", poolChart.getBitcoinCom());
                dataset.put("BW.COM", poolChart.getBWCOM());
                dataset.put("BTCC Pool", poolChart.getBTCCPool());
                dataset.put("BitClub Network", poolChart.getBitClubNetwork());
                dataset.put("BitFury", poolChart.getBitFury());
                dataset.put("Solo CKPool", poolChart.getCKPool());
                dataset.put("KanoPool", poolChart.getKanoPool());
                dataset.put("BitcoinRussia", poolChart.getBitcoinRussia());
                dataset.put("58COIN", poolChart.get58COIN());

                setChart(dataset);
            }

            @Override
            public void onFailure(Call<PoolChart> call, Throwable t) {
                // Tratamento de eventual erro de requisição
            }
        });
    }

    private void setChart(Map<String, Integer> dataset) {
        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterTextSize(10);
        pieChart.setDrawHoleEnabled(false);

        addDataSet(dataset);
    }

    private void addDataSet(Map<String, Integer> dataset) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        Set<String> chaves = dataset.keySet();
        for (String chave : chaves) {
            if(chave != null) {
                Integer value = dataset.get(chave);
                Log.d(PoolChartActivity.class.getSimpleName(), String.valueOf(chave + " " + value));
                if(value != null && value > 15) {
                    yEntrys.add(new PieEntry((float)value, chave));
                    xEntrys.add(chave);
                }
            }
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(0);

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
