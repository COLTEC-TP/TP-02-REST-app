package com.example.mtgo007.petfinder;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetalhePet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pet);

        final Bundle data = getIntent().getExtras();
        if(data != null){

            CircleImageView foto =  (CircleImageView) findViewById(R.id.foto_detalhe);
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
            endereco.setPaintFlags(endereco.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            endereco.setText(data.getString("endereco"));

            if(!"".equals(data.getString("endereco"))){
                endereco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("geo:0,0?q="+data.getString("endereco")+", "+data.getString("cidade")+", "+data.getString("estado"));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }

            TextView cidade = findViewById(R.id.cidade_detalhe);
            cidade.setText(data.getString("cidade"));

            TextView estado = findViewById(R.id.estado_detalhe);
            estado.setText(data.getString("estado"));

            TextView telefone = findViewById(R.id.telefone_detalhe);
            telefone.setPaintFlags(telefone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            telefone.setText(data.getString("telefone"));

            telefone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!"".equals(data.getString("telefone"))){
                        Uri uri = Uri.parse("tel:"+data.getString("telefone"));
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        DetalhePet.this.startActivity(intent);
                    }
                }
            });

            TextView descricao = findViewById(R.id.descricao_detalhe);
            descricao.setMovementMethod(new ScrollingMovementMethod());
            descricao.setText(data.getString("descricao"));
        }
    }
}
