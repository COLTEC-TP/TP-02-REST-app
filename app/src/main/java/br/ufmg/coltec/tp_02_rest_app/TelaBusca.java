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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import restapi.RetrofitConfig;
import restapi.VagalumeService;
import restapi.artmusBusca.ArtMusResponse;
import restapi.artmusBusca.ArtMusDocs;
import restapi.artmusBusca.ArtMus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaBusca extends AppCompatActivity {
    TelaBuscaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_busca);

        ListView ProdutosListView = findViewById(R.id.lista_busca);
        adapter = new TelaBuscaAdapter(this);
        ProdutosListView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        MenuItem button_search = menu.findItem(R.id.button_search);
        button_search.setVisible(false);


        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.onActionViewExpanded();

        final VagalumeService service = new RetrofitConfig().getVagalumeService(); //setando o retrofit
        final String limit = "5";
        final String apikey = "757b78a7fb6faecd6b3ba6ad97daac38";

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) { //durante a digitação do usuário filtra e seta a ListView com o resultado da busca

                Call<ArtMusResponse> musicaDadosCall = service.buscaArtmus(query, limit, apikey);
                musicaDadosCall.enqueue(new Callback<ArtMusResponse>() {
                    @Override
                    public void onResponse(Call<ArtMusResponse> call, Response<ArtMusResponse> response) {
                        try {
                            ArtMus resp = response.body().getResponse();
                            mostraBusca(resp.getDocs());
                        }catch (Exception e){
                            Log.i("I", "onResponse: " + e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArtMusResponse> call, Throwable t) {
                        Log.i("I", "onFailure: "+ t.toString());

                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void mostraBusca(ArrayList<ArtMusDocs> lista) {
        final ArrayList<ArtMusDocs> lista_aux = lista;

        final ListView buscaListView = findViewById(R.id.lista_busca);
        buscaListView.setAdapter(new TelaBuscaAdapter(this, lista));


        buscaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Object listItem = auxList.getItemAtPosition(position);

                if(lista_aux.get(position).getTitle()!=null){//se for uma música
                    Toast toast = Toast.makeText(getApplicationContext(), lista_aux.get(position).getTitle(), Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(TelaBusca.this, ExibeLetra.class);

                    intent.putExtra("mus", lista_aux.get(position).getTitle());
                    intent.putExtra("art", lista_aux.get(position).getBand());

                    startActivity(intent);

                    
                 }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Esse é um artista.", Toast.LENGTH_SHORT);
                    toast.show();

                }



            }
        });
    }
}
