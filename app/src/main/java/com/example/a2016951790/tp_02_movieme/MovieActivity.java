package com.example.a2016951790.tp_02_movieme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        FilmeService service = new RetrofitConfigUnity().getFilmeService();
        Call<Filme> filmeCall = service.getFilmeDetails();

        filmeCall.enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                Filme filme = response.body();
                mTitle.setText(filme.getTitulo());
                mSubtitle.setText(filme.getSubtitle());
                mDescription.setText(filme.getDescription());
                mFoto.setImageUrl("https://image.tmdb.org/t/p/original" + filme.getCartaz());
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
            }
        });
    }
}
