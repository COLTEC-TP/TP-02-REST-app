package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.gui_rei.tempopreparar.rest.days.Dias;
import com.gui_rei.tempopreparar.rest.days.data.Data;

public class ClimaDetalhesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_detalhes);

        Bundle activityBundle = this.getIntent().getExtras();
        String diaS = activityBundle.getString("dia");
        int dia = Integer.parseInt( diaS );
        Log.i("info", "onCreate: dia: " + dia);

        Dias dias = Dados.getInstance().getClimaDias(Prefs.getInstance().getCity());
        Data dados = dias.getData().get(dia);
        /////////////////////////////////////////////

        String cidade = dias.getName() + ", " + dias.getState();

        TextView texto = findViewById(R.id.cidade);
        texto.setText(cidade);

        //Setar a data
        TextView data = findViewById(R.id.data);
        data.setText("Data: " + dados.getDate_br());

        //Setar a temperatura
        TextView temp = findViewById(R.id.temp);
        temp.setText("Temperatura: " + dados.getTemperature().getMin() + " / " + dados.getTemperature().getMax());

        //Setar a umidade
        TextView umidade = findViewById(R.id.umidade);
        umidade.setText("Umidade: " + dados.getHumidity().getMin() + " / " + dados.getHumidity().getMax());

        //Setar a sensacao termica
        TextView sensacao = findViewById(R.id.sensacao);
        sensacao.setText("Sensação Térmica: " + dados.getThermal_sensation().getMin() + " / " + dados.getThermal_sensation().getMax());

        //Setar UV
        TextView uv = findViewById(R.id.uv);
        uv.setText("UV: " + dados.getUv().getMax());

        //Setar chance de chuva
        TextView probabilidadeChuva = findViewById(R.id.propabilidadeChuva);
        probabilidadeChuva.setText("Probabilidade de chuva: " + dados.getRain().getProbability() + "%");

        //Setar preciptacao de chuva
        TextView precipitacaoChuva = findViewById(R.id.precipitacaoChuva);
        precipitacaoChuva.setText("Precipitacao de chuva: " + dados.getRain().getPrecipitation());

        //Setar velocidade do vento
        TextView velocidadeVento = findViewById(R.id.vento);
        velocidadeVento.setText("Velocidade do vento: " + dados.getWind().getVelocity_avg());
    }
}
