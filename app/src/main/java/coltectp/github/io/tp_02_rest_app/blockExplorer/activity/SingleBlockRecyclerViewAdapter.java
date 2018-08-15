package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.blockExplorer.BlockExplorerHelper;
import coltectp.github.io.tp_02_rest_app.blockExplorer.Transaction;

public class SingleBlockRecyclerViewAdapter extends RecyclerView.Adapter<SingleBlockRecyclerViewAdapter.ViewHolder> {
    private static SimpleBlockRecyclerViewAdapter.ClickListener clickListener;
    private final List<Transaction> mValues;

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
    public void onBindViewHolder(final SingleBlockRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mInputView.setText(String.valueOf(BlockExplorerHelper.sumOfInputValue(mValues.get(position).getInputs())));
        holder.mHashView.setText(mValues.get(position).getHash());
        holder.mOutputView.setText(String.valueOf(BlockExplorerHelper.sumOfValueOut(mValues.get(position).getOutputs())));
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
        public final CardView mCardView;
        public Transaction mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mOutputView = (TextView) view.findViewById(R.id.output_tv_editable);
            mHashView = (TextView) view.findViewById(R.id.hash_tv_editable);
            mInputView = (TextView) view.findViewById(R.id.input_tv_editable);
            mCardView = (CardView) view.findViewById(R.id.card_view);

            WindowManager windowmanager = (WindowManager)view.getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dimension = new DisplayMetrics();
            if (windowmanager != null) {
                windowmanager.getDefaultDisplay().getMetrics(dimension);
            }
            final int height = dimension.heightPixels;

            mCardView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    mCardView.getViewTreeObserver().removeOnPreDrawListener(this);
                    int minHeight = mCardView.getHeight();
                    ViewGroup.LayoutParams layoutParams = mCardView.getLayoutParams();
                    layoutParams.height = minHeight;
                    mCardView.setLayoutParams(layoutParams);

                    return true;
                }
            });

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
