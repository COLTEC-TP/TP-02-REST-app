package com.example.mtgo007.petfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalhePet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pet);

        Bundle data = getIntent().getExtras();
        if(data != null){

            ImageView foto = findViewById(R.id.foto_detalhe);
            if(data.getString("foto") != null){
                new DownloadImageTask(foto)
                        .execute(data.getString("foto"));
            }

            TextView nome = findViewById(R.id.nome_detalhe);
            nome.setText(data.getString("nome"));

            TextView idade = findViewById(R.id.idade_detalhe);
            idade.setText(data.getString("idade"));

            TextView sexo = findViewById(R.id.sexo_detalhe);
            sexo.setText(data.getString("sexo"));

            TextView raca = findViewById(R.id.raca_detalhe);
            raca.setText(data.getString("raca"));

            TextView endereco = findViewById(R.id.endereco_detalhe);
            endereco.setText(data.getString("endereco"));

            TextView cidade = findViewById(R.id.cidade_detalhe);
            cidade.setText(data.getString("cidade"));

            TextView estado = findViewById(R.id.estado_detalhe);
            estado.setText(data.getString("estado"));

            TextView telefone = findViewById(R.id.telefone_detalhe);
            telefone.setText(data.getString("telefone"));

            TextView descricao = findViewById(R.id.descricao_detalhe);
            descricao.setText(data.getString("descricao"));
        }
    }
}
