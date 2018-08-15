package br.ufmg.coltec.tp_02_rest_app;

import android.app.ActionBar;
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
import restapi.artmusAttr.ArtMus;
import restapi.artmusAttr.ArtMusDocs;
import restapi.artmusAttr.ArtMusResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscaArtMus extends AppCompatActivity {
    BuscaArtMusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_artmus);



        ListView ProdutosListView = findViewById(R.id.lista_busca);
        adapter = new BuscaArtMusAdapter(this);
        ProdutosListView.setAdapter(adapter);

    }


/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_search:
                // inicia a activity Cadastro_Produto quando o usuário clica no botão correspondente
                //Intent intent = new Intent(MainActivity.this, Cadastro_Produto.class);
                //startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
*/

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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { //durante a digitação do usuário filtra e seta a ListView com o resultado da busca

                final VagalumeService service = new RetrofitConfig().getVagalumeService(); //setando o retrofit

                String query = s; //o que o usuário digitou
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

                            //  Log.i("I", "onResponse: " + resp.getDocs().get(2).getBand());
                            mostraBusca(resp.getDocs());


                        }catch (Exception e){
                            Log.i("I", "onResponse: " + e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArtMus> call, Throwable t) {
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
        buscaListView.setAdapter(new BuscaArtMusAdapter(this, lista));


        buscaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Object listItem = auxList.getItemAtPosition(position);

                Toast toast = Toast.makeText(getApplicationContext(), lista_aux.get(position).getTitle(), Toast.LENGTH_SHORT);
                toast.show();


            }
        });
    }
}
