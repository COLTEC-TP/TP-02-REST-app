package coltectp.github.io.tp_02_rest_app.charts.block;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import coltectp.github.io.tp_02_rest_app.charts.Chart;
import coltectp.github.io.tp_02_rest_app.charts.DateMarkerView;
import coltectp.github.io.tp_02_rest_app.charts.HourAxisValueFormatter;
import coltectp.github.io.tp_02_rest_app.charts.Point;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionPerBlockFragment extends Fragment {
    public static final String ARG_PAGE = "TRANSACTION_PER_BLOCK";

    private int mPage;
    private ProgressBar mProgressBar;
    private LineChart mChart;

    public static TransactionPerBlockFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TransactionPerBlockFragment fragment = new TransactionPerBlockFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void showProgress(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mChart.setVisibility(show ? View.GONE    : View.VISIBLE);
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

        mProgressBar = view.findViewById(R.id.progressBar);
        mChart = view.findViewById(R.id.chart);

        makeCall(view);

        return view;
    }

    private void makeCall(final View view) {
        BlockchainAPI service = new RetrofitConfig(view.getContext()).getInfoBlockchain(view.getContext());
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
                setChart(view, chart, entries, points.get(0).getX());
                showProgress(false);
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

    private void setChart(View view, Chart transactionPerBlock, List<Entry> entries, float referencedTime) {
        LineDataSet dataSet = new LineDataSet(entries, transactionPerBlock.getName()); // add entries to dataset
        LineData lineData = new LineData(dataSet);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new HourAxisValueFormatter((long)referencedTime));

        DateMarkerView myMarkerView = new DateMarkerView(
                                                        getActivity(),
                                                        R.layout.custom_marker_view,
                                                        (long) referencedTime);
        mChart.setMarker(myMarkerView);

        mChart.setData(lineData);
        mChart.invalidate(); // refresh
    }
}
