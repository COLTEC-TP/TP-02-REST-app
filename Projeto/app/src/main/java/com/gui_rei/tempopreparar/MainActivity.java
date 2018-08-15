package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    // 15 dias (ou 7, n entendi): apiadvisor.climatempo.com.br/api/v1/forecast/locale/6879/days/15?token=

    private void preencheTela(ClimaAtual clima){ //Função responsável for preencher o frontend

        //Mostrar a cidade
        TextView txtCidade = findViewById(R.id.txt_cidade);
        txtCidade.setText(clima.getName().toString());

        //Mostrar velocidade do vento
//        TextView txtTemp = findViewById(R.id.txt_vento);
//        txtTemp.setText("Clima em " + clima.getName().toString() + ": \n" +
//                "Vento: " + clima.getData().getWind_velocity().toString() + " KM/h"
//        );

        //Mostrar a temperatura atual
        TextView temperaturaAtual = findViewById(R.id.txtTemperaturaAtual);
        temperaturaAtual.setText(clima.getData().getTemperature().toString() + "°"
        );
    }

    private void atualizaTemp(){
        Prefs prefs = Prefs.getInstance();
        Integer city = prefs.getCity();

        RetrofitConfig retrofitConfig = new RetrofitConfig();
        final ClimaAtualService serviceA = retrofitConfig.getClimaAtualService();
        Call<ClimaAtual> request = serviceA.getClima(city.toString());
        Toast.makeText(MainActivity.this,"Buscando por temperatura", Toast.LENGTH_SHORT).show();
        request.enqueue(new Callback<ClimaAtual>() {
            @Override
            public void onResponse(Call<ClimaAtual> call, Response<ClimaAtual> response) {// executado quando resposta for recebida
                ClimaAtual clima = response.body();
                preencheTela(clima);
            }
            @Override
            public void onFailure(Call<ClimaAtual> call, Throwable t) {//executado quando houver erros
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"Algo deu errado",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Remover Action Bar
        getActionBar().hide();

        Button btnAtualiza = findViewById(R.id.btn_alterarCidadeMain);
        Button btnAlteraCidade = findViewById(R.id.btnCity);

        btnAtualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaTemp();
            }
        });
        btnAlteraCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CityActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizaTemp();
    }
}