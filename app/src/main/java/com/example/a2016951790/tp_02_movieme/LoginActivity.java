package com.example.a2016951790.tp_02_movieme;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        final EditText mail = findViewById(R.id.lgn_mail);
        final EditText pass = findViewById(R.id.lgn_pass);
        Button submit = findViewById(R.id.lgn_button);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbController crud = new DbController(getBaseContext());
                String mails = mail.getText().toString();
                String passs = pass.getText().toString();
                Integer conf = crud.pegarUsuarioPorEmail(mails, passs);
                SharedPreferences sharedPreferences = getSharedPreferences("pref_key", Context.MODE_PRIVATE);



                if(conf == -1){
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.user_incorrect), Toast.LENGTH_LONG).show();
                } else if(conf == -2){
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.pass_incorrect), Toast.LENGTH_LONG).show();
                } else if(conf >= 0){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_id", conf.toString());
                    editor.apply();
                    finish();
                }



            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent(LoginActivity.this, EnterActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
