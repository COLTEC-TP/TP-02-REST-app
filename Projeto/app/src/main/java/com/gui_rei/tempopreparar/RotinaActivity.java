package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RotinaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotina2);

        ((Button) findViewById(R.id.btn_setrotina)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.getInstance().setTemRotinaAmanha(1);
            }
        });
    }
}
