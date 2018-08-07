package com.example.a2016951790.tp_02_movieme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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


                if(conf == 0){
                    Toast.makeText(LoginActivity.this, "User Not Found", Toast.LENGTH_LONG).show();
                } else if(conf == 1){
                    Toast.makeText(LoginActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                } else if(conf == 2){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
