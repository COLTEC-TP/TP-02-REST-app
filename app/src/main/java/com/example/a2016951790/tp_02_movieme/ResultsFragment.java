package com.example.a2016951790.tp_02_movieme;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_results, container, false);
        FilmeService service = new RetrofitConfig().getFilmeService();
        String myDataFromActivity = getArguments().getString("Key");
        Call<List<Filme>> filmeCall = service.getFilmeByName(myDataFromActivity);

        ((MainActivity) getActivity()).setActionBarTitle(getContext().getResources().getString(R.string.nav_results));


        filmeCall.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                List<Filme> filmes = response.body();
                RecyclerView recyclerView = view.findViewById(R.id.recycler_view_layour_recycler);
                RecyclerAdapter listAdapter = new RecyclerAdapter(container.getContext(), filmes);
                recyclerView.setAdapter(listAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                Toast toast = Toast.makeText(container.getContext(), container.getResources().getString(R.string.connection_error),Toast.LENGTH_SHORT);
                toast.show();
            }

        });



        return view;
    }

}
