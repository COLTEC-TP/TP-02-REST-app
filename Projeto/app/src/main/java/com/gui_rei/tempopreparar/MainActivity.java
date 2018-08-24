package com.gui_rei.tempopreparar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
        //Toast.makeText(MainActivity.this,"Codigo do icone: " + codigoIcone, Toast.LENGTH_SHORT).show();

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
        int linhasTabela = 7; //Quantas linhas tem a tabela
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
        //Toast.makeText(MainActivity.this,"Amanha é: " + clima.getData().get(1).getDate_br(), Toast.LENGTH_SHORT).show(); //So pra verificar se ta certo
    }
    private void preencherTela()//Funcao que pega os dados do banco e chama as preenchedoras
    {
        int city = Prefs.getInstance().getCity();

        ClimaAtual climaA = Dados.getInstance().getClimaAtual(city);
        if(climaA == null) //Se não tem a informação no banco
        {
            Log.d("main", "A: Banco vazio!!!!!!!!!!");
            Toast.makeText(MainActivity.this,"Aguarde atualizar",Toast.LENGTH_SHORT).show();
        }
        else preencheTela(climaA);

        Dias climaD = Dados.getInstance().getClimaDias(city);
        if(climaD == null) //Se não tem a informação no banco
        {
            Log.d("main", "D: Banco vazio!!!!!!!!!!");
            Toast.makeText(MainActivity.this,"Aguarde atualizar",Toast.LENGTH_SHORT).show();
        }
        else preencheTela(climaD);
    }

    private void atualizaTemp(){
        Prefs prefs = Prefs.getInstance();
        Integer city = prefs.getCity();

        RetrofitConfig retrofitConfig = new RetrofitConfig();

        final ClimaAtualService serviceA = retrofitConfig.getClimaAtualService();
        Call<ClimaAtual> requestA = serviceA.getClima(city.toString());
        Toast.makeText(MainActivity.this,"Atualizando clima", Toast.LENGTH_SHORT).show();
        requestA.enqueue(new Callback<ClimaAtual>() {
            @Override
            public void onResponse(Call<ClimaAtual> call, Response<ClimaAtual> response) {// executado quando resposta for recebida
                ClimaAtual clima = response.body();
                if(clima==null) Log.i("call", "onResponse: resposta nula");
                else {
                    Dados.getInstance().setClimaAtual(clima);
                    preencheTela(clima);
                }
            }
            @Override
            public void onFailure(Call<ClimaAtual> call, Throwable t) {//executado quando houver erros
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"Erro de rede",Toast.LENGTH_SHORT).show();
            }
        });

        final DiasService serviceB = retrofitConfig.getDDiasService();
        Call<Dias> requestB = serviceB.getDias(city.toString());
        //Toast.makeText(MainActivity.this,"Buscando por temperatura de amanha", Toast.LENGTH_SHORT).show();
        Log.i("call", "atualizaTemp: Buscando temp amanha");
        requestB.enqueue(new Callback<Dias>() {
            @Override
            public void onResponse(Call<Dias> call, Response<Dias> response) {// executado quando resposta for recebida
                Dias clima = response.body();
                if(clima==null) Log.i("call", "onResponse: resposta nula");
                else {
                    Dados.getInstance().setClimaDias(clima);
                    preencheTela(clima);
                    avisar();
                }
            }
            @Override
            public void onFailure(Call<Dias> call, Throwable t) {//executado quando houver erros
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"Erro de rede",Toast.LENGTH_SHORT).show();
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

        Dados.setContext(MainActivity.this); //Permite ao dados manipula arquivos
        Prefs.getInstance().setContextAndLoad(MainActivity.this);

        //Alterar cor da Action Bar
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.azul)));

        preencherTela();

    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizaTemp();

        avisar();
    }
    private void avisar(){
        Dias dias = Dados.getInstance().getClimaDias(Prefs.getInstance().getCity());
        if(Prefs.tempMostrarAviso==1 && dias!=null && Prefs.getInstance().getTemRotinaAmanha() == 1) { //vai mudar muito
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Você vai sair amanhã");
            String clima = Dados.getInstance().getClimaDias(Prefs.getInstance().getCity()).getData().get(1).getText_icon().getIcon().getDay();
            if (clima.equals("1") || clima.equals("1") || clima.equals("9") || clima.equals("2"))
                builder.setMessage("Mas não se preocupe, não vai chover");
            else
                builder.setMessage("É melhor levar uma blusa e um guarda chuva");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Prefs.tempMostrarAviso = 0; //Já mostrou, n precisa mostrar mais
                }
            });
            Prefs.tempMostrarAviso = 0; //eh melhor mesmo sem apertar ok //Já mostrou, n precisa mostrar mais
            builder.show();
        }
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
            case R.id.row3:
                x=3;
                break;
            case R.id.row4:
                x=4;
                break;
            case R.id.row5:
                x=5;
                break;
            case R.id.row6:
                x=6;
                break;
        }

        Intent intent = new Intent(MainActivity.this,ClimaDetalhesActivity.class);
        Bundle args = new Bundle();
        args.putCharSequence("dia", x.toString());
        intent.putExtras(args);
        startActivity(intent);
    }
}