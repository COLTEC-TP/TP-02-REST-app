package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.blockExplorer.BlockExplorerHelper;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Transaction;

public class SingleBlockRecyclerViewAdapter extends RecyclerView.Adapter<SingleBlockRecyclerViewAdapter.ViewHolder> {
    private static SimpleBlockRecyclerViewAdapter.ClickListener clickListener;
    private final List<Transaction> mValues;
    private int mExpandedPosition = -1;

    public SingleBlockRecyclerViewAdapter(List<Transaction> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public SingleBlockRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_block_item, parent, false);
        return new SingleBlockRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SingleBlockRecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mInputView.setText(String.valueOf(BlockExplorerHelper.sumOfInputValue(mValues.get(position).getInputs())) + "BTC");
        holder.mHashView.setText(mValues.get(position).getHash());
        holder.mOutputView.setText(String.valueOf(BlockExplorerHelper.sumOfValueOut(mValues.get(position).getOutputs())) + "BTC");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mOutputView;
        public final TextView mHashView;
        public final TextView mInputView;
        public Transaction mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mOutputView = (TextView) view.findViewById(R.id.output_tv_editable);
            mHashView = (TextView) view.findViewById(R.id.hash_tv_editable);
            mInputView = (TextView) view.findViewById(R.id.input_tv_editable);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(SimpleBlockRecyclerViewAdapter.ClickListener clickListener) {
        SingleBlockRecyclerViewAdapter.clickListener = clickListener;
    }
}
