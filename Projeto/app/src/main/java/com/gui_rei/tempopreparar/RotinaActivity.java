package com.gui_rei.tempopreparar;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class RotinaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotina2);

        //Alterar cor da Action Bar
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.azul)));

        CalendarView c = findViewById(R.id.calendarView);
        final String[] data = {""};

        c.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Integer d = dayOfMonth;
                Integer m = month;
                Integer a = year;

                data[0] = d.toString() + "/" + m.toString() + "/" + a.toString();
            }
        });


        ((Button) findViewById(R.id.btn_setrotina)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.getInstance().setTemRotinaAmanha(1);


                Toast.makeText(RotinaActivity.this, data[0], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
