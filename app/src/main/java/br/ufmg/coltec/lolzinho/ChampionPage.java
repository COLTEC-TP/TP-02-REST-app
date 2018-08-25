package br.ufmg.coltec.lolzinho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChampionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_page);

        Bundle activityBundle = this.getIntent().getExtras();
        final String nome = activityBundle.getString("nome");
        final String titulo = activityBundle.getString("titulo");
        final int id = activityBundle.getInt("id");

        TextView name = findViewById(R.id.ChampName);
        name.setText(nome);
        TextView title = findViewById(R.id.ChampTitle);
        title.setText(titulo);

        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute("http://ddragon.leagueoflegends.com/cdn/8.16.1/img/champion/"+ nome  +".png");

    }
}
