package br.tp.tp_rest;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PokemonDAO dao = new PokemonDAO(this);

        for (int i=1; i<=dao.getNum_pokemons(); i++) {
            Callback<Pokemon> cb = new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    dao.addPokemon(pokemon);
                    ListView listPokemons = findViewById(R.id.pokemonsList);
                    listPokemons.setAdapter(new PokemonAdapter(dao.getContext(), dao.getPokemons()));
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    t.printStackTrace();
                }
            };
            dao.carregarPokemons(i, cb);

        }


        //ArrayList<Pokemon> pokemons = PokemonDAO.getInstance().getPokemons();

    }
}
/*
        // Prepara Action Bar //
   //     ActionBar actionBar = getActionBar();
  //      actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));

        // recupera os imóveis cadastrados no DAO até o momento e os carrega na lista
//        PokemonDAO dao = PokemonDAO.getInstance();
//        this.atualizarLista(dao.getPokemons());
//    }

    // MUDAR PQ EH INEFICIENTE //
 //   private void atualizarLista(ArrayList<Pokemon> pokemons) {

        // Não precisa criar outro adapter //
        //ListView imoveisList = findViewById(R.id.pokemonsList);
        //imoveisList.setAdapter(new PokemonAdapter(this, pokemons));
        //adapter.notifyDataSetChanged();
        //ListView listImoveis = findViewById(R.id.imoveisList);
        //listImoveis.setAdapter(adapter);
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla menu na tela
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // busca pelo SearchView e customiza suas ações
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                PokemonDAO dao = PokemonDAO.getInstance();
                ArrayList<Pokemon> filtrados = dao.filtrarPokemons(s);
                ListView listImoveis = findViewById(R.id.pokemonsList);
                atualizarLista(filtrados);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // executado enquanto texto é alterado pelo usuário
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                PokemonDAO dao = PokemonDAO.getInstance();
                atualizarLista(dao.getPokemons());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // recupera id do item selecionado
        int id = item.getItemId();

        // verifica qual é o botão selecionado com base no id
        switch (id) {
            case R.id.action_add:
                Intent intent = new Intent(MainActivity.this, NovoImovelActivity.class);
                startActivity(intent);
                this.atualizarLista(ImovelDAO.getInstance().getImoveis());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
*/
