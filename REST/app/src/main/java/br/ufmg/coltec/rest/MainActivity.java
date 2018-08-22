package br.ufmg.coltec.rest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufmg.coltec.rest.Services.Taxa;
import br.ufmg.coltec.rest.Services.TaxaHistorica;
import br.ufmg.coltec.rest.Services.UltimasTaxas;
import br.ufmg.coltec.rest.Services.RetrofitConfig;
import br.ufmg.coltec.rest.Services.Taxas;
import br.ufmg.coltec.rest.Services.UltimasTaxas;
import br.ufmg.coltec.rest.Services.UsersService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    ArrayList<Taxa> txs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "61642e7a5ee4824a017e67db26cea9f7";

        UsersService service = new RetrofitConfig().getUsersService();

        /*

        Call<TaxaHistorica> historyCall = service.historyRate("2018-02-20", key, "BRL");

        historyCall.enqueue(new Callback<TaxaHistorica>() {
            @Override
            public void onResponse(Call<TaxaHistorica> call, Response<TaxaHistorica> response) {
                try {
                    Log.i("I",response.raw().toString());
                    TaxaHistorica resp = response.body();

                    float br = resp.getMoedas().getBRL();
                    String brST = Float.toString(br);
                    Toast toast = Toast.makeText(getApplicationContext(), brST, Toast.LENGTH_LONG);
                    toast.show();

                }catch (Exception e){
                    Log.i("I",e.toString());
                }

            }

            @Override
            public void onFailure(Call<TaxaHistorica> call, Throwable t) {
                Log.i("I",t.toString());
            }
        });

        */

        Call<UltimasTaxas> latestCall = service.getLatest(key);

        // = new ArrayList<>();

        latestCall.enqueue(new Callback<UltimasTaxas>() {
            @Override
            public void onResponse(Call<UltimasTaxas> call, Response<UltimasTaxas> response) {
                try {
                    UltimasTaxas resp = response.body();
                    txs = resp.getMoedas().getTodosOsValores();
                    ListView languagesListView = findViewById(R.id.languages_list);
                    languagesListView.setAdapter(new TaxaAdapter(MainActivity.this, txs));
                }catch (Exception e){
                    Log.i("I",e.toString());
                }

            }

            @Override
            public void onFailure(Call<UltimasTaxas> call, Throwable t) {
                Log.i("I",t.toString());
            }


        });

    }

}
