package com.example.a2016951790.tp_02_movieme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        final SmartImageView mFoto = findViewById(R.id.mv_image);
        final TextView mTitle = findViewById(R.id.mv_title);
        final TextView mSubtitle = findViewById(R.id.mv_subtitle);
        final TextView mDescription = findViewById(R.id.mv_description);
        final Button mButton = findViewById(R.id.mv_fav);
        final DbController crud = new DbController(this);

        FilmeService service = new RetrofitConfigUnity().getFilmeService();
        final Integer id = getIntent().getIntExtra("id", 0);
        Call<Filme> filmeCall = service.getFilmeDetails(id);

        SharedPreferences sharedPreferences = getSharedPreferences("pref_key", Context.MODE_PRIVATE);
        final String result = sharedPreferences.getString("user_id", "");



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.salvarFilme(id, Integer.parseInt(result), MovieActivity.this);
            }
        });
        

        filmeCall.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                Filme filme = response.body();
                //Log.i("msg", filme.getTitulo());
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
}
