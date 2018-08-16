package br.tp.tp_rest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    PokemonDAO dao = PokemonDAO.getInstance(this);
    PokemonAdapter adapter = null;
    AppDB db = new AppDB(this);
    RecyclerView.LayoutManager layoutManager = null;
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    int pokemon_img = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (db.getAllPokemons().size() != dao.getNum_pokemons()) {
            db.deleteAll();
            Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
            startActivity(intent);
        } else {
            pokemons = db.getAllPokemons();

            // Atualiza DAO
            for (int i=0; i<pokemons.size(); i++){
                dao.addPokemon(pokemons.get(i));
            }

            // Baixa Imagens
            DownloadImage download = new DownloadImage();
            try {
                download.execute(pokemons.get(pokemon_img).getSprite().getUrl()).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }



        // Prepara Action Bar //
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<Pokemon> pokemonsFiltrados = dao.filtrarPokemons(s);
                adapter.atualiza(pokemonsFiltrados);
                adapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // executado enquanto texto é alterado pelo usuário
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.atualiza(db.getAllPokemons());
                adapter.notifyDataSetChanged();
                layoutManager.scrollToPosition(0);

                // Baixa Imagens
                DownloadImage download = new DownloadImage();
                try {
                    download.execute(pokemons.get(pokemon_img).getSprite().getUrl()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private class DownloadImage extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap imagemBitmap = null;
            try{
                imagemBitmap = baixarImagem(params[0]);
            }catch (IOException e){
                e.printStackTrace();
            }

            return imagemBitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(bitmap!=null) {
                pokemons.get(pokemon_img).setBitmap(bitmap);
                if (pokemon_img == pokemons.size() - 1){ // Terminou de carregar as imagens
                    //ListView listPokemons = findViewById(R.id.pokemonsList);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pokemonsList);

                    adapter = new PokemonAdapter(pokemons, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                }else{
                    pokemon_img++;
                    DownloadImage download = new DownloadImage();
                    try {
                        download.execute(pokemons.get(pokemon_img).getSprite().getUrl()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static Bitmap baixarImagem(String url) throws IOException{
        URL endereco;
        InputStream inputStream;
        Bitmap imagem;

        endereco = new URL(url);
        inputStream = endereco.openStream();
        imagem = BitmapFactory.decodeStream(inputStream);

        inputStream.close();

        return imagem;
    }
}
