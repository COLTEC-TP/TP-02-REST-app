package com.example.a2016951790.tp_02_movieme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by a2016951790 on 09/08/18.
 */

public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Button search = view.findViewById(R.id.search_button);
        final EditText conteudo = view.findViewById(R.id.search_string);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String conteudo2 = conteudo.getText().toString();
                swapFragment(conteudo2);
            }
        });

        return view;
    }

    private void swapFragment(String string){
        ResultsFragment fragment2 = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString("Key", string);
        fragment2.setArguments(args);
        android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment2,"tag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
