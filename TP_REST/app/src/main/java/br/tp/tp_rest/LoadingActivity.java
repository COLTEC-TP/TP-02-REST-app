package br.tp.tp_rest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        AppDB db = new AppDB(this);
        final PokemonDAO dao = new PokemonDAO(this);
        requisita(1, dao, this, db);
    }

    private void requisita(final int id, final PokemonDAO dao, final Context context, final AppDB db) {
        Callback<Pokemon> cb = new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                dao.addPokemon(pokemon);
                db.insert(pokemon);
                if (id < dao.getNum_pokemons()) {
                    requisita(id + 1, dao, context, db);
                    Toast.makeText(context, pokemon.getName(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Todos os pokemons foram carregados", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                t.printStackTrace();
            }
        };

        dao.carregarPokemons(id, cb);

    }
}
