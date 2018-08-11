package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gui_rei.tempopreparar.rest.current.ClimaAtual;
import com.gui_rei.tempopreparar.rest.current.ClimaAtualService;
import com.gui_rei.tempopreparar.rest.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private static final String BH_ID = "6879";
    private static final String SP_ID = "3477";
    //Ex:
    // atual: http://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=
    // 15 dias (ou 6, n entendi): apiadvisor.climatempo.com.br/api/v1/forecast/locale/6879/days/15?token=

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RetrofitConfig retrofitConfig = new RetrofitConfig();
        final ClimaAtualService serviceA = retrofitConfig.getClimaAtualService();

        Button btnAtualiza = findViewById(R.id.btn);
        btnAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TextView labelTemp = findViewById(R.id.txt);

                Call<ClimaAtual> request = serviceA.getClima(BH_ID);
                Toast.makeText(MainActivity.this,"Buscando por temperatura", Toast.LENGTH_SHORT).show();
                request.enqueue(new Callback<ClimaAtual>() {
                    @Override
                    public void onResponse(Call<ClimaAtual> call, Response<ClimaAtual> response) {// executado quando resposta for recebida
                        ClimaAtual clima = response.body();
                        labelTemp.setText(
                                "Clima em " + clima.getName().toString() + ": \n" +
                                        "Temperatura: " + clima.getData().getTemperature().toString() + " \n" +
                                        "Vento: " + clima.getData().getWind_velocity().toString() + " KM/h"
                        );
                    }
                    @Override
                    public void onFailure(Call<ClimaAtual> call, Throwable t) {//executado quando houver erros
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this,"Algo deu errado",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
