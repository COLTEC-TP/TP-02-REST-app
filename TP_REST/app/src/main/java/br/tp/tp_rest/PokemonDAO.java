package br.tp.tp_rest;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDAO {

    private static int num_pokemons = 10;
    private ArrayList<Pokemon> pokemons;
    private Context context;

    // singleton para lidar com única instância do DAO
    private static PokemonDAO instance;


    public static PokemonDAO getInstance(Context context) {

        if(instance == null)
            instance = new PokemonDAO(context);

        return instance;
    }

    // Construtor Privado
    private PokemonDAO(Context context) {
        this.pokemons = new ArrayList<>();
        this.context = context;
    }


    // Inicializar DAO //
    public void inicializar(Activity activity){
        AppDB db = new AppDB(activity);
        this.pokemons = db.getAllPokemons();
        for (int i=0; i<this.pokemons.size(); i++){
            ContextWrapper cw = new ContextWrapper(this.context);
            File dir= cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f = new File(dir.getAbsolutePath(), String.valueOf(i+1) + ".png");
            Bitmap b = null;
            try {
                b = BitmapFactory.decodeStream(new FileInputStream(f));
                this.pokemons.get(i).setImagem(b); // Adiciona imagem ao pokemon
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(context, "Arquivo não encontrado", Toast.LENGTH_SHORT).show();
            }
            this.pokemons.get(i).setId(i);
        }
    }

    // Faz requisição por um pokemon
    public void carregarPokemons(final int pokemon_id, Callback<Pokemon> cb) {

        PokemonService service = new RetrofitConfig().getPersonagemService();
        Call<Pokemon> enderecoCall = service.getPokemon(String.valueOf(pokemon_id));

        // fazendo a requisição de forma assíncrona
        enderecoCall.enqueue(cb);
    }

    // Faz a filtragem dos pokemons por nome //

    public ArrayList<Pokemon> filtrarPokemons(String nome) {
        ArrayList<Pokemon> pokemonsFiltrados = new ArrayList<>();
        for (int i=0; i<num_pokemons; i++){
            if(pokemons.get(i).getName().toUpperCase().contains(nome.toUpperCase())){
                pokemonsFiltrados.add(pokemons.get(i));
            }
        }
        return pokemonsFiltrados;
    }

    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    // Adiciona Pokemon //
    public void addPokemon(Pokemon pokemon, Activity activity){
        this.pokemons.add(pokemon);
        AppDB db = new AppDB(activity);
        db.insert(pokemon);
    }


    // Deleta todos os Pokemon //
    public void deleteAllPokemon (Activity activity){
        this.pokemons = new ArrayList<>();
        AppDB db = new AppDB(activity);
        db.deleteAll();
    }

    public int getNum_pokemons(){
        return num_pokemons;
    }

    public Context getContext() {
        return context;
    }
}
