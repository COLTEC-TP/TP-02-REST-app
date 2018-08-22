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
import coltectp.github.io.tp_02_rest_app.blockExplorer.Input;

public class SingleTransactionInputAdapter extends BaseAdapter {
    private Context context;
    private List<Input> inputs;

    public SingleTransactionInputAdapter(Context context, List<Input> inputs) {
        this.context = context;

        this.inputs =  inputs;
    }

    @Override
    public int getCount() {
        return inputs.size();
    }

    @Override
    public Object getItem(int i) {
        return inputs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Input input = (Input) this.getItem(i);

        // recupera a view do adapter que será customizada
        View newView = LayoutInflater.from(this.context).inflate(R.layout.transaction_dialog_input_item,
                viewGroup, false);

        TextView lblAddress = newView.findViewById(R.id.address);
        TextView lblValue = newView.findViewById(R.id.value);
        if (input.getPreviousOutput() != null) {
            lblAddress.setText(input.getPreviousOutput().getAddress());
            lblValue.setText(String.valueOf(new BigDecimal(input.getPreviousOutput().getValue()).divide(BigDecimal.valueOf(100000000))) + " BTC");
        }

        return newView;
    }
}
