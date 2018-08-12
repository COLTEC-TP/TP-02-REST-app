package com.gui_rei.tempopreparar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gui_rei.tempopreparar.rest.RetrofitConfig;
import com.gui_rei.tempopreparar.rest.city.BuscaCidade;
import com.gui_rei.tempopreparar.rest.city.BuscaCidadeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
    }

    public void procura(View view) {
        //Prefs prefs = Prefs.getInstance();

        EditText txtCidade = findViewById(R.id.txtCityS);
        String cidade = txtCidade.getText().toString();

        RetrofitConfig retrofitConfig = new RetrofitConfig();
        final BuscaCidadeService serviceA = retrofitConfig.getBuscaCidadeService();
        Call<BuscaCidade> request = serviceA.getCidadeByName(cidade);
        Toast.makeText(CityActivity.this,"Buscando por essa cidade", Toast.LENGTH_SHORT).show();
        request.enqueue(new Callback<BuscaCidade>() {
            @Override
            public void onResponse(Call<BuscaCidade> call, Response<BuscaCidade> response) {// executado quando resposta for recebida
                BuscaCidade cidade = response.body();
                if(cidade.getName()!=null){
                    Prefs prefs = Prefs.getInstance();
                    prefs.setCity(cidade.getId());
                    Toast.makeText(CityActivity.this,"Cidade " + cidade.getName() + ", " + cidade.getState() + "selecionada",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(CityActivity.this,"Cidade não encontrada",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BuscaCidade> call, Throwable t) {//executado quando houver erros
                t.printStackTrace();
                Toast.makeText(CityActivity.this,"Algo deu errado",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
