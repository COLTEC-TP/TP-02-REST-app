package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Output;

public class SingleTransactionOutputAdapter extends BaseAdapter {

    private Context context;
    private List<Output> outputs;

    public SingleTransactionOutputAdapter(Context context, List<Output> outputs) {
        this.context = context;

        this.outputs = outputs;
    }

    @Override
    public int getCount() {
        return outputs.size();
    }

    @Override
    public Object getItem(int i) {
        return outputs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Output output = (Output) this.getItem(i);

        // recupera a view do adapter que será customizada
        View newView = LayoutInflater.from(this.context).inflate(R.layout.transaction_dialog_output_item,
                viewGroup, false);

        TextView lblAddress = newView.findViewById(R.id.address);
        TextView lblValue = newView.findViewById(R.id.value);

        lblAddress.setText(output.getAddress());
        lblValue.setText(String.valueOf(new BigDecimal(output.getValue()).divide(BigDecimal.valueOf(100000000))) + " BTC");

        return newView;
    }
}