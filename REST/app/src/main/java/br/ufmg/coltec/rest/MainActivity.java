package br.ufmg.coltec.rest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import br.ufmg.coltec.rest.Services.Taxa;

import br.ufmg.coltec.rest.Services.Taxas;
import br.ufmg.coltec.rest.Services.UltimasTaxas;
import br.ufmg.coltec.rest.Services.RetrofitConfig;

import br.ufmg.coltec.rest.Services.UsersService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    ArrayList<Taxa> txs;
    ItemSelecionado itemSelecionado = new ItemSelecionado();
    TaxaAdapter adapter  ;
    ArrayList<Float> sValores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "61642e7a5ee4824a017e67db26cea9f7";

        UsersService service = new RetrofitConfig().getUsersService();

        Call<UltimasTaxas> latestCall = service.getLatest(key);

        latestCall.enqueue(new Callback<UltimasTaxas>() {
            @Override
            public void onResponse(Call<UltimasTaxas> call, Response<UltimasTaxas> response) {
                try {
                    UltimasTaxas resp = response.body();
                    txs = resp.getMoedas().getTodosOsValores();
                    ListView languagesListView = findViewById(R.id.languages_list);
                    adapter = new TaxaAdapter(MainActivity.this, txs);
                    languagesListView.setAdapter(adapter);


                } catch (Exception e) {
                    Log.i("I", e.toString());
                }

            }

            @Override
            public void onFailure(Call<UltimasTaxas> call, Throwable t) {
                Log.i("I", t.toString());
            }

        });


    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.pesquisamoedas, menu);

        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }


            public boolean onQueryTextChange(String s) { //durante a digitação do usuário filtra e seta a ListView com o resultado da busca
                Taxas taxas = new Taxas();
                ArrayList<Taxa> listaFiltrada = taxas.filtrarProdutos(s, MainActivity.this.txs);
                mostraBusca(listaFiltrada);
                return false;
            }


        });
        return super.onCreateOptionsMenu(menu);
    }


    private void mostraBusca(ArrayList<Taxa> m) {
        ListView auxList = findViewById(R.id.languages_list);
        auxList.setAdapter(new TaxaAdapter(this, m));
    }
}
