package coltectp.github.io.tp_02_rest_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionPerBlock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_per_block);

        BlockchainAPI service = new RetrofitConfig().getInfoBlockchain();
        Map<String, String> data = new HashMap<>();
        data.put("timespan", "30days");
        data.put("rollingAverage", "8hours");
        data.put("format", "json");
        Call<Chart> transactionPerBlockCall = service.getTransactionsPerBlock(data);

        // fazendo a requisição de forma assíncrona
        transactionPerBlockCall.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {
                Chart chart = response.body();

                // Manipulação do endereço recebido

                List<Entry> entries = new ArrayList<Entry>();

                List<Point> points = chart.getPoints();

                for (Point data : convertingTimeSmallNum(points, points.get(0).getX())) {
                    entries.add(new Entry(data.getX(), data.getY()));
                }
                setChart(chart, entries, points.get(0).getX());
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {
                // Tratamento de eventual erro de requisição
            }
        });
    }

    private List<Point> convertingTimeSmallNum(List<Point> points, float referencedTimestamps) {
        List<Point> result = new ArrayList<>();
        for (Point data : points) {
            result.add(new Point(data.getX() - referencedTimestamps, data.getY()));
        }

        return result;
    }

    private void setChart(Chart transactionPerBlock, List<Entry> entries, float referencedTime) {
        LineChart chart = (LineChart) findViewById(R.id.chart);
        LineDataSet dataSet = new LineDataSet(entries, transactionPerBlock.getName()); // add entries to dataset
        LineData lineData = new LineData(dataSet);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new HourAxisValueFormatter((long)referencedTime));

        DateMarkerView myMarkerView = new DateMarkerView(
                                                        getApplicationContext(),
                                                        R.layout.custom_marker_view,
                                                        (long) referencedTime);
        chart.setMarker(myMarkerView);

        chart.setData(lineData);
        chart.invalidate(); // refresh
    }
}
