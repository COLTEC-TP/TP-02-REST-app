package br.tp.tp_rest;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Bundle bundle = this.getIntent().getExtras();
        int position = bundle.getInt("position");

        PokemonDAO pokemonDAO = PokemonDAO.getInstance(this);

        manipulaLayout(pokemonDAO.getPokemons().get(position));
    }

    private void manipulaLayout(Pokemon pokemon) {

        TextView nome = findViewById(R.id.Pokemon_Nome);

        TextView tipo_1 = findViewById(R.id.Tipo_1);
        TextView tipo_2 = findViewById(R.id.Tipo_2);

        ImageView imagem = findViewById(R.id.Pokemon_Img);

        //números de stats
        TextView ataque = findViewById(R.id.Ataque_Num);
        TextView defesa = findViewById(R.id.Defesa_Num);
        TextView ataque_especial = findViewById(R.id.Ataque_Especial_Num);
        TextView defesa_especial = findViewById(R.id.Defesa_Especial_Num);
        TextView agilidade = findViewById(R.id.Agilidade_Num);
        TextView hp = findViewById(R.id.HP_Num);

        //valores da progressbar
        ProgressBar bar_ataque = findViewById(R.id.Bar_Ataque);
        ProgressBar bar_defesa = findViewById(R.id.Bar_Defesa);
        ProgressBar bar_ataque_especial = findViewById(R.id.Bar_Ataque_Especial);
        ProgressBar bar_defesa_especial = findViewById(R.id.Bar_Defesa_Especial);
        ProgressBar bar_agilidade = findViewById(R.id.Bar_Agilidade);
        ProgressBar bar_hp = findViewById(R.id.Bar_HP);

        //  ----sets----
        nome.setText(pokemon.getName());

        ArrayList<PokeType> pokeTypes = pokemon.getPokeTypes();
        tipo_1.setText(String.valueOf(pokeTypes.get(0).getNamePokeType()));
        if (pokeTypes.size() > 1) { // Se possui mais de um tipo
            tipo_2.setText(String.valueOf(pokeTypes.get(1).getNamePokeType()));
        }else{
            tipo_2.setText("  ");
        }

        imagem.setImageBitmap(pokemon.getImagem());

        ArrayList<Stat> stats = pokemon.getStats();
        ataque.setText(String.valueOf(stats.get(0).getBase_stat()));
        defesa.setText(String.valueOf(stats.get(1).getBase_stat()));
        agilidade.setText(String.valueOf(stats.get(2).getBase_stat()));
        hp.setText(String.valueOf(stats.get(3).getBase_stat()));
        ataque_especial.setText(String.valueOf(stats.get(4).getBase_stat()));
        defesa_especial.setText(String.valueOf(stats.get(5).getBase_stat()));

        bar_ataque.setProgress(stats.get(0).getBase_stat());
        bar_defesa.setProgress(stats.get(1).getBase_stat());
        bar_agilidade.setProgress(stats.get(2).getBase_stat());
        bar_hp.setProgress(stats.get(3).getBase_stat());
        bar_ataque_especial.setProgress(stats.get(4).getBase_stat());
        bar_defesa_especial.setProgress(stats.get(5).getBase_stat());

    }
}
