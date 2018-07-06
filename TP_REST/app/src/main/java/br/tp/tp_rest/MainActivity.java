package br.tp.tp_rest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView nome = findViewById(R.id.nome);

        PersonagemService service = new RetrofitConfig().getPersonagemService();
        Call<Personagem> enderecoCall = service.getEndereco("583");

        // fazendo a requisição de forma assíncrona
        enderecoCall.enqueue(new Callback<Personagem>() {
            @Override
            public void onResponse(Call<Personagem> call, Response<Personagem> response) {
                Personagem personagem = response.body();
                nome.setText(personagem.getName()+" sabe de nada");
                // Manipulação do endereço recebido
            }

            @Override
            public void onFailure(Call<Personagem> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


}
