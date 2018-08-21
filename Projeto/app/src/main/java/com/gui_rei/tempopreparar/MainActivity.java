package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private static final String BH_ID = "6879";
    //Ex:
    // atual: http://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=
    // 15 dias (ou 7, n entendi): apiadvisor.climatempo.com.br/api/v1/forecast/locale/6879/days/15?token=

    private int defIcon(String cod){
        switch (cod) {
            case "1":
                return R.drawable.sol;
            case "9":
                return R.drawable.sol;
            case "1n":
                return R.drawable.noite;
            case "2n":
                return R.drawable.noite;
            case "2rn":
                return R.drawable.noite;
            case "9n":
                return R.drawable.noite;
            case "2":
                return R.drawable.solnuvem;
            case "2r":
                return R.drawable.solnuvem;
            case "3TM":
                return R.drawable.nuvem;
            default:
                return R.drawable.guardachuva;
        }
        //return android.R.drawable.ic_menu_help;
    }
    private void preencheTela(ClimaAtual clima) //Função responsável for preencher o frontend
    {
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

        ImageView climaIcon = findViewById(R.id.imageView);
        climaIcon.setImageResource(defIcon(codigoIcone));

        //Mostrar a temperatura atual
        TextView temperaturaAtual = findViewById(R.id.txt_TemperaturaAtual);
        temperaturaAtual.setText(clima.getData().getTemperature().toString() + "°");
    }
    private int getIdDeUmItemNaTabela(String prefix, int linha, String item) //Função muuuuuito louca pra pegar o id
    {
        int idReturn = 0;
        try {
            String id = prefix + "_" + Integer.toString( linha ) + "_" + item; //"dias_0_msg"
            Log.i("Verificando", "getIdDeUmItemNaTabela: " + id);
            idReturn = (int) R.id.class.getDeclaredField(id).get(int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(idReturn == 0) Log.wtf ("Eita pora", "getIdDeUmItemNaTabela: N devia chegar aq");
        return idReturn; //Se for 0 complica la na frente
    }
    private void preencheTela(Dias clima) //Função responsável for colocar dados de outros dias
    {
        int linhasTabela = 3; //Quantas linhas tem a tabela
        for(int x = 0;x<linhasTabela;x++){
            ImageView icon = findViewById( getIdDeUmItemNaTabela("dias",x,"icon") );
            TextView tempMin = findViewById( getIdDeUmItemNaTabela("dias",x,"tempMin") );
            TextView tempMax = findViewById( getIdDeUmItemNaTabela("dias",x,"tempMax") );

            icon.setImageResource(defIcon(clima.getData().get(x).getText_icon().getIcon().getDay())); //getDay retorna a media dos icones de todas as horas teoricamente
            tempMin.setText(clima.getData().get(x).getTemperature().getMin() + "° / ");
            tempMax.setText(clima.getData().get(x).getTemperature().getMax() + "°");
        }

        //((TextView) findViewById(R.id.dias_1_temp)).setText("max: " + clima.getData().get(1).getTemperature().getMax() + "°");
        //((TextView) findViewById(R.id.txt_tempAmanha)).setText("Temp max amanhã: " + clima.getData().get(1).getTemperature().getMax() + "°"); // get(1) seria amanha
        Toast.makeText(MainActivity.this,"Amanha é: " + clima.getData().get(1).getDate_br(), Toast.LENGTH_SHORT).show(); //So pra verificar se ta certo
    }
    private void preencherTela()//Funcao que pega os dados do banco e chama as preenchedoras
    {
        int city = Prefs.getInstance().getCity();
        ClimaAtual climaA = Dados.getInstance().getClimaAtual(city);
        Dias climaD = Dados.getInstance().getClimaDias(city);
        if(climaA == null || climaD == null) //Se não tem a informação no banco ///!!! Redundante neh, de acordo com atualizaTemp se um eh null o outro tmb eh!!
        {
            Log.d("main", "Banco vazio!!!!!!!!!!");
            Toast.makeText(MainActivity.this,"Aguarde atualizar",Toast.LENGTH_SHORT).show();
        }
        else {
            preencheTela(climaA);
            preencheTela(climaD);
        }
    }

    private void atualizaTemp(){
        Prefs prefs = Prefs.getInstance();
        Integer city = prefs.getCity();
        final int[] pronto = {0,0}; //ponteiro de int

        RetrofitConfig retrofitConfig = new RetrofitConfig();

        final ClimaAtualService serviceA = retrofitConfig.getClimaAtualService();
        Call<ClimaAtual> requestA = serviceA.getClima(city.toString());
        Toast.makeText(MainActivity.this,"Buscando por temperatura", Toast.LENGTH_SHORT).show();
        requestA.enqueue(new Callback<ClimaAtual>() {
            @Override
            public void onResponse(Call<ClimaAtual> call, Response<ClimaAtual> response) {// executado quando resposta for recebida
                ClimaAtual clima = response.body();
                //preencheTela(clima);
                Dados.getInstance().setClimaAtual(clima);

                //Preencher: //precisa disso ou pode so preencher duas vez?
                pronto[0] = 1; //avisar q ta pronto
                if(pronto[0] == 1 && pronto[1] == 1) preencherTela(); //Se o outro call ja recebeu pode preencher
                else Log.i("Call", "ClimaAtual chegou primeiro");
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
                //preencheTela(clima);
                Dados.getInstance().setClimaDias(clima);

                //Preencher: //precisa disso ou pode so preencher duas vez?
                pronto[1] = 1; //avisar q ta pronto
                if(pronto[0] == 1 && pronto[1] == 1) preencherTela(); //Se o outro call ja recebeu pode preencher
                else Log.i("Call", "Dias chegou primeiro");
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
            startActivity(new Intent(MainActivity.this,CityActivity.class));
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

        preencherTela();

    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizaTemp();
    }

    public void rowClick(View view){
        Integer x = -1;
        switch (view.getId()){
            case R.id.row0:
                x=0;
                break;
            case R.id.row1:
                x=1;
                break;
            case R.id.row2:
                x=2;
                break;
        }

        Intent intent = new Intent(MainActivity.this,ClimaDetalhesActivity.class);
        Bundle args = new Bundle();
        args.putCharSequence("dia", x.toString());
        intent.putExtras(args);
        startActivity(intent);
    }
}