package br.ufmg.coltec.lolzinho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView champList = findViewById(R.id.champList);
        final ArrayList<Champion> campeoes = new ArrayList<>();

        SummonerService service = new RetrofitConfig().getSummonerService();
        Call<Data> championCall = service.getChampionList();

        championCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data newData = response.body();
                champList.setAdapter(new ChampionAdapter(MainActivity.this, newData.champions));
                champList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this, ChampionPage.class);
                        Bundle args = new Bundle();
                        Champion currentFilme = (Champion) champList.getItemAtPosition(champList.getPositionForView(view));

                        args.putString("nome", currentFilme.nome);
                        args.putString("titulo", currentFilme.titulo);
                        args.putInt("id", currentFilme.id);
                        intent.putExtras(args);
                        startActivity(intent);
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

}