package com.example.a2016951790.tp_02_movieme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by a2016951790 on 09/08/18.
 */

public class NextFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next, container, false);
        final DbController crud = new DbController(container.getContext());

        ((MainActivity) getActivity()).setActionBarTitle(getContext().getResources().getString(R.string.nav_next));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("pref_key", Context.MODE_PRIVATE);
        final String result = sharedPreferences.getString("user_id", "");

        ArrayList teste = crud.pegarProximoPorID(result);
        ArrayList<Filme> filmes = crud.pegarFilmePorID(teste);

        Toast.makeText(container.getContext(), String.valueOf(teste.size()) , Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_layour_recycler);
        RecyclerAdapter listAdapter = new RecyclerAdapter(container.getContext(), filmes);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
