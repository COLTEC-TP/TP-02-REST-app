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

        TextView texto = findViewById(R.id.detalhesInfo);
        texto.setText(cidade + "\n" + dados.getDate_br() + "\n" + "max: " + dados.getTemperature().getMax());
    }
}
