package br.ufmg.coltec.tp_02_rest_app;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import restapi.RetrofitConfig;
import restapi.VagalumeService;
import restapi.musicadadosModel.Musica;
import restapi.musicadadosModel.MusicaDados;
import restapi.musicadadosModel.Traducao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExibeLetra extends AppCompatActivity {
    private String letra;
    private String traducao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_letra);

        final ToggleButton verTraducao = (ToggleButton) findViewById(R.id.traducao);
        Bundle extras = getIntent().getExtras();
        final String mus_name = extras.getString("mus");
        final String art_name = extras.getString("art");
        String apikey = "757b78a7fb6faecd6b3ba6ad97daac38";

        VagalumeService service = new RetrofitConfig().getVagalumeService(); //setando o retrofit
        Call<MusicaDados> musicaDadosCall = service.getMusica(art_name,mus_name, apikey);

        //quando houver resposta...
        musicaDadosCall.enqueue(new Callback<MusicaDados>() {
            @Override
            public void onResponse(Call<MusicaDados> call, Response<MusicaDados> response) {
                Log.i("I", "onResponse: " + response.raw());

                try {

                    TextView letraView = findViewById(R.id.letra);
                    TextView tituloView = findViewById(R.id.titulo);
                    TextView artView = findViewById(R.id.artista);

                    MusicaDados dadosMusica;
                    Musica nossaMusica;
                    Traducao nossaTraducao;

                    dadosMusica = response.body(); //obtém resposta
                    nossaMusica = dadosMusica.getMusica().get(0); //a api nos retorna um array do tipo MusicaRank, a música solicitada está na posição 0

                    if(nossaMusica.getTranslate()==null) { //
                        verTraducao.setVisibility(View.INVISIBLE);
                    }else{
                        nossaTraducao = nossaMusica.getTranslate().get(0);
                        traducao = nossaTraducao.getText();
                        verTraducao.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   TextView letraView = findViewById(R.id.letra);
                                   if(verTraducao.isChecked()){
                                       letraView.setText(traducao);
                                   }else{
                                       letraView.setText(letra);
                                   }
                               }
                        });
                    }

                    letra = nossaMusica.getText(); //na string Text está a letra

                    tituloView.setText(mus_name);
                    artView.setText(art_name);
                    letraView.setText(letra);






                }catch (Exception e){
                    Log.i("I", e.toString());
                    TextView letraView = findViewById(R.id.letra);
                    letraView.setText("Houve algum problema ao acessar essa música. :(");
                }
            }

            @Override
            public void onFailure(Call<MusicaDados> call, Throwable t) {
                Log.i("I", t.toString());
                TextView letraView = findViewById(R.id.letra);
                letraView.setText("Falha na requisição.");

            }
        });




    }
}
