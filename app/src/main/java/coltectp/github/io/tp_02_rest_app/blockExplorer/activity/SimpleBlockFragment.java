package coltectp.github.io.tp_02_rest_app.blockExplorer.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Map;

import coltectp.github.io.tp_02_rest_app.BlockchainAPI;
import coltectp.github.io.tp_02_rest_app.R;
import coltectp.github.io.tp_02_rest_app.RetrofitConfig;
import coltectp.github.io.tp_02_rest_app.blockExplorer.SimpleBlockList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class SimpleBlockFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "SIMPLE_BLOCK_FRAGMENT";
//    private OnListFragmentInteractionListener mListener;
    private SimpleBlockList mSimpleBlocks;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SimpleBlockFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SimpleBlockFragment newInstance(int columnCount) {
        SimpleBlockFragment fragment = new SimpleBlockFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layoutView = inflater.inflate(R.layout.fragment_item_list, container, false);

        ImageButton mButtonSearch = layoutView.findViewById(R.id.search_text_api_btn);

        View.OnClickListener search = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchEditText = layoutView.findViewById(R.id.search_text_api_et);

                BlockchainAPI service = new RetrofitConfig().getInfoBlockchain();

                Map<String, String> data = new HashMap<>();
                data.put("format", "json");

                Log.d(SimpleBlockList.class.getSimpleName(), searchEditText.getText().toString());

                Call<SimpleBlockList> blocksBySpecificPoolCall =
                        service.getBlocksBySpecificPool(searchEditText.getText().toString());

                // fazendo a requisição de forma assíncrona
                blocksBySpecificPoolCall.enqueue(new Callback<SimpleBlockList>() {
                    @Override
                    public void onResponse(@NonNull Call<SimpleBlockList> call, @NonNull Response<SimpleBlockList> response) {
                        Log.d(SimpleBlockList.class.getSimpleName(), "Making msg");
                        mSimpleBlocks = response.body();

                        // Set the adapter
                        if (layoutView instanceof RecyclerView) {
                            Context context = layoutView.getContext();
                            RecyclerView recyclerView = (RecyclerView) layoutView.findViewById(R.id.list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            SimpleBlockRecyclerViewAdapter adapter = new SimpleBlockRecyclerViewAdapter(mSimpleBlocks.getBlocks());
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            //Loader
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SimpleBlockList> call, @NonNull Throwable t) {
                        Log.d(SimpleBlockList.class.getSimpleName(), "Failed", t);
                    }
                });
            }
        };

        mButtonSearch.setOnClickListener(search);

        return layoutView;
    }

//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(SimpleBlock item);
//    }
}
