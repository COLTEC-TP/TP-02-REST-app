package com.example.a2016951790.tp_02_movieme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    final DbController crud = new DbController(this);
    Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final SmartImageView mFoto = findViewById(R.id.mv_image);
        final TextView mTitle = findViewById(R.id.mv_title);
        final TextView mSubtitle = findViewById(R.id.mv_subtitle);
        final TextView mDescription = findViewById(R.id.mv_description);

        FilmeService service = new RetrofitConfigUnity().getFilmeService();
        final Integer id = getIntent().getIntExtra("id", 0);
        Call<Filme> filmeCall = service.getFilmeDetails(id);


        filmeCall.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                filme = response.body();
                mTitle.setText(filme.getTitulo());
                mSubtitle.setText(filme.getSubtitle());
                mDescription.setText(filme.getDescription());
                mFoto.setImageUrl("https://image.tmdb.org/t/p/original" + filme.getCartaz());
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                Log.d(MovieActivity.class.getSimpleName(),"mensagem", t);
                Toast toast = Toast.makeText(MovieActivity.this, getResources().getString(R.string.connection_error),Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla menu na tela
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences("pref_key", Context.MODE_PRIVATE);
        final String result = sharedPreferences.getString("user_id", "");
        // recupera id do item selecionado
        int id = item.getItemId();

        // verifica qual é o botão selecionado com base no id
        switch (id) {
            case R.id.menu_favorite:
                Toast.makeText(this, getResources().getString(R.string.nav_favorite), Toast.LENGTH_SHORT).show();
                crud.salvarFilme(0, filme.getId(), Integer.parseInt(result), filme.getTitulo(), filme.getSubtitle(), filme.getRating(), filme.getAno(),filme.getFoto(), MovieActivity.this);
                return true;
            case R.id.menu_next:
                Toast.makeText(this, "Clicou no refresh", Toast.LENGTH_SHORT).show();
                crud.salvarFilme(1, filme.getId(), Integer.parseInt(result), filme.getTitulo(), filme.getSubtitle(), filme.getRating(), filme.getAno(),filme.getFoto(), MovieActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
