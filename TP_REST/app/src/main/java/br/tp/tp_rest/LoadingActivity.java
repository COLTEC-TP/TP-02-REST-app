package br.tp.tp_rest;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends Activity {
    AppDB db = new AppDB(this);
    final PokemonDAO dao = PokemonDAO.getInstance(this);
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        requisita(dao, this, db);
    }

    private void requisita(final PokemonDAO dao, final Context context, final AppDB db) {
        Callback<Pokemon> cb = new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                dao.addPokemon(pokemon);
                db.insert(pokemon);
                salvar_imagem(pokemon);
                Toast.makeText(context, pokemon.getName(), Toast.LENGTH_SHORT).show();
                id += 1;
                if (id-1 < dao.getNum_pokemons()) {
                    requisita(dao, context, db);
                } else {
                    Toast.makeText(context, "Todos os pokemons foram carregados", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "Falha ao carregar pokemons", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };

        dao.carregarPokemons(id, cb);

    }

    public void salvar_imagem(Pokemon p){

        // Baixa Imagens
        DownloadImage download = new DownloadImage();
        try {
            download.execute(p.getSprite().getUrl()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                // Create imageDir
                File mypath=new File(directory,String.valueOf(id-1) + ".png");

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mypath);
                    // Use the compress method on the BitMap object to write image to the OutputStream
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
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
