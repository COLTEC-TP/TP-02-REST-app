package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gui_rei.tempopreparar.joao.User;
import com.gui_rei.tempopreparar.joao.RetrofitConfig;
import com.gui_rei.tempopreparar.joao.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity {

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

    public void buscarUsuario(View view) {
        final TextView lblUsuario = findViewById(R.id.lbl_usuario);
        String login = ((EditText) findViewById(R.id.txt_login)).getText().toString();

        //EditText txtLogin = findViewById(R.id.txt_login);
        //String login = txtLogin.getText().toString();

        RetrofitConfig retrofitConfig = new RetrofitConfig();
        UserService service = retrofitConfig.getUserService();

        Call<User> request = service.getUser(login);
        Toast.makeText(MainActivity.this,"Buscando por " + login, Toast.LENGTH_SHORT).show();

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // executado quando resposta for recebida
                User user = response.body();

                lblUsuario.setText(user.getName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //executado quando houver erros
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"N deu",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
