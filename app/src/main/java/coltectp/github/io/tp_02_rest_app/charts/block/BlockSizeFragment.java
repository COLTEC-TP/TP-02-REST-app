package coltectp.github.io.tp_02_rest_app.charts.block;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.charts.Chart;
import coltectp.github.io.tp_02_rest_app.charts.DateMarkerView;
import coltectp.github.io.tp_02_rest_app.charts.HourAxisValueFormatter;
import coltectp.github.io.tp_02_rest_app.charts.Point;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockSizeFragment extends Fragment {
    public static final String ARG_PAGE = "AVERAGE_BLOCK_SIZE";

    private int mPage;

    public static BlockSizeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BlockSizeFragment fragment = new BlockSizeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        BlockchainAPI service = new RetrofitConfig(view.getContext()).getInfoBlockchain(view.getContext());
        Map<String, String> data = new HashMap<>();
        data.put("timespan", "30days");
        data.put("rollingAverage", "8hours");
        data.put("format", "json");
        Call<Chart> averageBlockSizeCall = service.getAverageBlockSize(data);

        // fazendo a requisição de forma assíncrona
        averageBlockSizeCall.enqueue(new Callback<Chart>() {
            @Override
            public void onResponse(Call<Chart> call, Response<Chart> response) {
                Chart chart = response.body();

                // Manipulação do endereço recebido

                List<Entry> entries = new ArrayList<Entry>();

                List<Point> points = chart.getPoints();

                for (Point data : convertingTimeSmallNum(points, points.get(0).getX())) {
                    entries.add(new Entry(data.getX(), data.getY()));
                }
                setChart(view, chart, entries, points.get(0).getX());
            }

            @Override
            public void onFailure(Call<Chart> call, Throwable t) {
                // Tratamento de eventual erro de requisição
            }
        });

        return view;
    }

    private List<Point> convertingTimeSmallNum(List<Point> points, float referencedTimestamps) {
        List<Point> result = new ArrayList<>();
        for (Point data : points) {
            result.add(new Point(data.getX() - referencedTimestamps, data.getY()));
        }

        return result;
    }

    private void setChart(View view, Chart transactionPerBlock, List<Entry> entries, float referencedTime) {
        LineChart chart = (LineChart) view;
        LineDataSet dataSet = new LineDataSet(entries, transactionPerBlock.getName()); // add entries to dataset
        LineData lineData = new LineData(dataSet);

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new HourAxisValueFormatter((long)referencedTime));

        DateMarkerView myMarkerView = new DateMarkerView(
                getActivity(),
                R.layout.custom_marker_view,
                (long) referencedTime);
        chart.setMarker(myMarkerView);

        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

}
