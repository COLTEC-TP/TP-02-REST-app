package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlock;
//import coltectp.github.io.tp_02_rest_app.blockExplorer.activity.SimpleBlockFragment.OnListFragmentInteractionListener;

public class SimpleBlockRecyclerViewAdapter extends RecyclerView.Adapter<SimpleBlockRecyclerViewAdapter.ViewHolder> {
    private static ClickListener clickListener;
    private final List<SimpleBlock> mValues;
    private SimpleDateFormat mDataFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ROOT);


    public SimpleBlockRecyclerViewAdapter(List<SimpleBlock> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_block_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mHeightView.setText(String.valueOf(mValues.get(position).getHeight()));
        holder.mHashView.setText(mValues.get(position).getHash());
        holder.mTimeView.setText(mDataFormat.format(mValues.get(position).getTime()*1000));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mHeightView;
        public final TextView mHashView;
        public final TextView mTimeView;
        public SimpleBlock mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHeightView = (TextView) view.findViewById(R.id.height_tv);
            mHashView = (TextView) view.findViewById(R.id.hash_tv);
            mTimeView = (TextView) view.findViewById(R.id.time_tv);
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

    public void setOnItemClickListener(ClickListener clickListener) {
        SimpleBlockRecyclerViewAdapter.clickListener = clickListener;
    }
}
