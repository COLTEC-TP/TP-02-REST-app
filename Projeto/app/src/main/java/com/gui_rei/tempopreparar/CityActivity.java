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
        Call<BuscaCidade[]> request = serviceA.getCidadeByName(cidade);
        Toast.makeText(CityActivity.this,"Buscando por essa cidade", Toast.LENGTH_SHORT).show();
        request.enqueue(new Callback<BuscaCidade[]>() {
            @Override
            public void onResponse(Call<BuscaCidade[]> call, Response<BuscaCidade[]> response) {// executado quando resposta for recebida
                BuscaCidade[] resultados = response.body();
                BuscaCidade cidade = resultados[0];
                Prefs prefs = Prefs.getInstance();
                prefs.setCity(cidade.getId());

                if(resultados.length>1){ //"Em 20 anos dessa industria vital isso nunca me ocorreu"
                    Toast.makeText(CityActivity.this,"Encontramos mais de uma cidade com esse nome e selecionamos a primeira",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(CityActivity.this,"Cidade " + cidade.getName() + ", " + cidade.getState() + " selecionada",Toast.LENGTH_SHORT).show();

                finish(); //fechar essa tela e voltar para a anterior
            }
            @Override
            public void onFailure(Call<BuscaCidade[]> call, Throwable t) {//executado quando houver erros
                t.printStackTrace();
                Toast.makeText(CityActivity.this,"Algo deu errado, esta cidade pode não existir",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
