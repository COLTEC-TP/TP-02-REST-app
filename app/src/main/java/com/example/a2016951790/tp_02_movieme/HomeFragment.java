package com.example.a2016951790.tp_02_movieme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by a2016951790 on 09/08/18.
 */

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        FilmeService service = new RetrofitConfig().getFilmeService();
        Call<List<Filme>> filmeCall = service.getFilme();

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


