package com.gui_rei.tempopreparar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private static final String token = "1300a7ef84802f53402e16f9505ae2be";
        //Ex:
        // atual: http://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=
        // 15 dias (ou 6, n entendi): apiadvisor.climatempo.com.br/api/v1/forecast/locale/6879/days/15?token=

    private static final String bh_id = "6879";
    private static final String sp_id = "3477";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
