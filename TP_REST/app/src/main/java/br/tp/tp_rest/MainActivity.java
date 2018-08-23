package br.tp.tp_rest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity extends Activity implements ItemClickListener {

    PokemonDAO dao = PokemonDAO.getInstance(this);
    PokemonAdapter adapter = null;
    RecyclerView.LayoutManager layoutManager = null;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao.inicializar(this);

        if (dao.getPokemons().size() != dao.getNum_pokemons()) {
            dao.deleteAllPokemon(this);
            Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
            startActivity(intent);
        } else {
            pokemons = dao.getPokemons();

            // Cria adapter //
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pokemonsList);
            adapter = new PokemonAdapter(pokemons, MainActivity.this);
            recyclerView.setAdapter(adapter);
            layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            adapter.setClickListener(this); //liga ao listener
        }



        // Prepara Action Bar //
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<Pokemon> pokemonsFiltrados = dao.filtrarPokemons(s);
                adapter.atualiza(pokemonsFiltrados);
                adapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // executado enquanto texto é alterado pelo usuário
                ArrayList<Pokemon> pokemonsFiltrados = dao.filtrarPokemons(s);
                adapter.atualiza(pokemonsFiltrados);
                adapter.notifyDataSetChanged();
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.atualiza(pokemons);
                adapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(0);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
        Bundle args = new Bundle();
        args.putInt("position", position);
        intent.putExtras(args);
        startActivity(intent);
    }
}
