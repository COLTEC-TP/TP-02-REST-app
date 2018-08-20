package com.example.a2016951790.tp_02_movieme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText mailr = findViewById(R.id.reg_mail);
        final EditText passr = findViewById(R.id.reg_pass);
        final EditText userr = findViewById(R.id.reg_user);
        final EditText namer = findViewById(R.id.reg_name);
        Button sign = findViewById(R.id.reg_button);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbController crud = new DbController(getBaseContext());
                String mailString = mailr.getText().toString();
                String passString = passr.getText().toString();
                String userString = userr.getText().toString();
                String nameString = namer.getText().toString();
                long resultado;

                resultado = crud.insertUser(mailString, passString, nameString, userString);

                if(resultado == -1) {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.failed), Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.user) + ":" + userString, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(RegisterActivity.this, EnterActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
