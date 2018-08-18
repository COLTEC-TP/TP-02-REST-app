package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gui_rei.tempopreparar.rest.current.ClimaAtual;
import com.gui_rei.tempopreparar.rest.current.ClimaAtualService;
import com.gui_rei.tempopreparar.rest.RetrofitConfig;
import com.gui_rei.tempopreparar.rest.days.Dias;
import com.gui_rei.tempopreparar.rest.days.DiasService;

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

        //Verificar o clima para saber qual icone colocar
        String codigoIcone = clima.getData().getIcon().toString();
        Toast.makeText(MainActivity.this,"Codigo do icone: " + codigoIcone, Toast.LENGTH_SHORT).show();

        /**
         * MAPEAMENTO DOS ICONES
         * 1, 9 = sol
         * 1n, 2n, 2rn, 9n = noite
         * 2, 2r = sol/nuvem
         * 3TM = nuvem
         * restante: chuva
         */



        if(codigoIcone.equals("1") || codigoIcone.equals("9")) {
            ImageView blueBtn  = (ImageView)findViewById(R.id.imageView);
            blueBtn.setImageResource(R.drawable.sol);
        }
        else if (codigoIcone.equals("1n") || codigoIcone.equals("2n") || codigoIcone.equals("2rn") || codigoIcone.equals("9n")){
            ImageView blueBtn  = (ImageView)findViewById(R.id.imageView);
            blueBtn.setImageResource(R.drawable.noite);
        }
        else if (codigoIcone.equals("2") || codigoIcone.equals("2r")){
            ImageView blueBtn  = (ImageView)findViewById(R.id.imageView);
            blueBtn.setImageResource(R.drawable.solnuvem);
        }
        else if (codigoIcone.equals("3TM")){
            ImageView blueBtn  = (ImageView)findViewById(R.id.imageView);
            blueBtn.setImageResource(R.drawable.nuvem);
        }
        else {
            ImageView blueBtn  = (ImageView)findViewById(R.id.imageView);
            blueBtn.setImageResource(R.drawable.guardachuva);
        }

        //Mostrar a temperatura atual
        TextView temperaturaAtual = findViewById(R.id.txtTemperaturaAtual);
        temperaturaAtual.setText(clima.getData().getTemperature().toString() + "°");
    }
    private void preencheTela(Dias clima){ //Função responsável for colocar dados de outros dias
        ((TextView) findViewById(R.id.txt_tempAmanha)).setText("Temp max amanhã: " + clima.getData().get(1).getTemperature().getMax() + "°"); // get(1) seria amanha
        Toast.makeText(MainActivity.this,"Amanha é: " + clima.getData().get(1).getDate_br(), Toast.LENGTH_SHORT).show();
    }

    private void atualizaTemp(){
        Prefs prefs = Prefs.getInstance();
        Integer city = prefs.getCity();


        RetrofitConfig retrofitConfig = new RetrofitConfig();

        final ClimaAtualService serviceA = retrofitConfig.getClimaAtualService();
        Call<ClimaAtual> requestA = serviceA.getClima(city.toString());
        Toast.makeText(MainActivity.this,"Buscando por temperatura", Toast.LENGTH_SHORT).show();
        requestA.enqueue(new Callback<ClimaAtual>() {
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

        final DiasService serviceB = retrofitConfig.getDDiasService();
        Call<Dias> requestB = serviceB.getDias(city.toString());
        Toast.makeText(MainActivity.this,"Buscando por temperatura da amanha", Toast.LENGTH_SHORT).show();
        requestB.enqueue(new Callback<Dias>() {
            @Override
            public void onResponse(Call<Dias> call, Response<Dias> response) {// executado quando resposta for recebida
                Dias clima = response.body();
                preencheTela(clima);
            }
            @Override
            public void onFailure(Call<Dias> call, Throwable t) {//executado quando houver erros
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"Algo deu errado",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btn_atualizar) {
            atualizaTemp();
        }
        else if (id == R.id.btn_rotina) {
            startActivity(new Intent(MainActivity.this,RotinaActivity.class));
        }
        else if (id == R.id.btn_conf) {
            startActivity(new Intent(MainActivity.this,ConfiguracoesActivity.class));
        }
        else if (id == R.id.btn_sobre) {
            startActivity(new Intent(MainActivity.this,SobreActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAlteraCidade = findViewById(R.id.btnCity);

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