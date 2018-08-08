package com.gui_rei.tempopreparar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private static final String token = "1300a7ef84802f53402e16f9505ae2be"; //Ex: apiadvisor.climatempo.com.br/api/v1/forecast/locale/3477/days/15?token=

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
