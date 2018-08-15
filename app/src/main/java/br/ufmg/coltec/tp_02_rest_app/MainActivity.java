package br.ufmg.coltec.tp_02_rest_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import restapi.RetrofitConfig;
import restapi.VagalumeService;
import restapi.artmusAttr.ArtMus;
import restapi.artmusAttr.ArtMusResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
/*

        final VagalumeService service = new RetrofitConfig().getVagalumeService(); //setando o retrofit

        String query = "Skank Vamos Fugir"; //o que o usuário digitou
        String limit = "5";
        //calling
        Call<ArtMus> musicaDadosCall = service.searchArtmus(query, limit);

        musicaDadosCall.enqueue(new Callback<ArtMus>() {
            @Override
            public void onResponse(Call<ArtMus> call, Response<ArtMus> response) {
                try {
                    ArtMus  res = response.body();
                    ArtMusResponse resp = res.getResponse();
                    Log.i("I", "onResponse: " + response.raw());

                    Log.i("I", "onResponse: " + resp.getDocs().get(2).getBand());


                }catch (Exception e){
                    Log.i("I", "onResponse: " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<ArtMus> call, Throwable t) {
                Log.i("I", "onFailure: "+ t.toString());

            }
        });

*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // inicia a activity Cadastro_Produto quando o usuário clica no botão correspondente
                Intent intent = new Intent(MainActivity.this, BuscaArtMus.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}

