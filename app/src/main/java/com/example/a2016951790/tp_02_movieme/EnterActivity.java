package com.example.a2016951790.tp_02_movieme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Button login = findViewById(R.id.ent_login);
        Button register = findViewById(R.id.ent_register);

        SharedPreferences sharedPreferences = getSharedPreferences("pref_key", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("user_id", "");

        if(result.equals("")) {

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EnterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EnterActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        } else {
            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
