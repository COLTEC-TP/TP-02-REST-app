package com.gui_rei.tempopreparar;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.gui_rei.tempopreparar.rest.days.Dias;
import com.gui_rei.tempopreparar.rest.days.data.Data;

public class ClimaDetalhesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_detalhes);

        //Alterar cor da Action Bar
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.azul)));

        Bundle activityBundle = this.getIntent().getExtras();
        String diaS = activityBundle.getString("dia");
        int dia = Integer.parseInt( diaS );
        Log.i("info", "onCreate: dia: " + dia);

        Dias dias = Dados.getInstance().getClimaDias(Prefs.getInstance().getCity());
        if(dias==null){
            Toast.makeText(ClimaDetalhesActivity.this,"Não temos informações de clima",Toast.LENGTH_SHORT).show();
        }
        else {
            Data dados = dias.getData().get(dia);
            /////////////////////////////////////////////

            String cidade = dias.getName() + ", " + dias.getState();
            ((TextView) findViewById(R.id.cidade)).setText(cidade);

            ((TextView) findViewById(R.id.data)).setText(dados.getDate_br());//Setar a data
            ((TextView) findViewById(R.id.temp)).setText("Temperatura: " + dados.getTemperature().getMin() + " / " + dados.getTemperature().getMax());//Setar a temperatura
            ((TextView) findViewById(R.id.umidade)).setText("Umidade: " + dados.getHumidity().getMin() + " / " + dados.getHumidity().getMax());//Setar a umidade
            ((TextView) findViewById(R.id.sensacao)).setText("Sensação Térmica: " + dados.getThermal_sensation().getMin() + " / " + dados.getThermal_sensation().getMax());//Setar a sensacao termica
            ((TextView) findViewById(R.id.uv)).setText("UV: " + dados.getUv().getMax());//Setar UV
            ((TextView) findViewById(R.id.propabilidadeChuva)).setText("Probabilidade de chuva: " + dados.getRain().getProbability() + "%");//Setar chance de chuva
            ((TextView) findViewById(R.id.precipitacaoChuva)).setText("Precipitacao de chuva: " + dados.getRain().getPrecipitation());//Setar preciptacao de chuva
            ((TextView) findViewById(R.id.vento)).setText("Velocidade do vento: " + dados.getWind().getVelocity_avg());//Setar velocidade do vento
        }
    }
}
