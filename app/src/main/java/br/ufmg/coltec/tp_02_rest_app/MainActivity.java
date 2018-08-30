package br.ufmg.coltec.tp_02_rest_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import restapi.RetrofitConfig;
import restapi.VagalumeService;
import restapi.rankingModel.Mus;
import restapi.rankingModel.MusicaRank;
import restapi.rankingModel.RankingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        ListView RankingListView = findViewById(R.id.lista_ranking);
        RankingAdapter adapter;
        adapter = new RankingAdapter(this);
        RankingListView.setAdapter(adapter);

        final VagalumeService service = new RetrofitConfig().getVagalumeService(); //setando o retrofit

        String type = "mus", period = "day", scope = "internacional", limit = "5", key = "757b78a7fb6faecd6b3ba6ad97daac38";
        Call<RankingResponse> musicaDadosCall = service.obterRanking(type, period, scope, limit, key);

        musicaDadosCall.enqueue(new Callback<RankingResponse>() {
            @Override
            public void onResponse(Call<RankingResponse> call, Response<RankingResponse> response) {
                try {
                    Mus ranking = response.body().getMus();
                    mostraRanking(ranking.getDay().getMusicaRank()); //carrega na listview
                } catch (Exception e) {
                    Log.i("I", "onResponse: " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<RankingResponse> call, Throwable t) {
                Log.i("I", "onFailure: " + t.toString());

            }

        });

    }

    private void mostraRanking(ArrayList<MusicaRank> lista) {
        final ArrayList<MusicaRank> lista_aux = lista;
        final ListView rankingListView = findViewById(R.id.lista_ranking);
        rankingListView.setAdapter(new RankingAdapter(this, lista));

        rankingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Object listItem = auxList.getItemAtPosition(position);

                    Intent intent = new Intent(MainActivity.this, ExibeLetra.class);
                    intent.putExtra("mus", lista_aux.get(position).getName());
                    intent.putExtra("art", lista_aux.get(position).getArt().getName());
                    startActivity(intent);

                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_search:
                // inicia a activity TelaBusca quando o usuário clica no botão correspondente
                Intent intent = new Intent(MainActivity.this, TelaBusca.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}

