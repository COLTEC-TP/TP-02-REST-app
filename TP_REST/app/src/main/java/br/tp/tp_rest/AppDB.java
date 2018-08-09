package br.tp.tp_rest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AppDB extends SQLiteOpenHelper {

        private static String DB_NOME = "Pokemons";
        private static final int DB_VERSAO = 1;
        private static final String SCRIPT_CREATE = "CREATE TABLE Pokemons (nome TEXT)";

        public AppDB(Activity context){
            super(context, DB_NOME, null, DB_VERSAO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void insert(Pokemon p){
            SQLiteDatabase db = this.getWritableDatabase();
            try {
                ContentValues cv = new ContentValues();
                cv.put("nome", p.getName());
                db.insert("Pokemons", null, cv);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                db.close();
            }
        }
        public ArrayList<Pokemon> getAllPokemons() {
            ArrayList<Pokemon> pokemons = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();

            try {
                // faz a leitura dos dados do banco
                Cursor c = db.query("Pokemons", null, null, null, null, null, null);

                if(c.moveToFirst()) {
                    do {
                        String nome = c.getString(c.getColumnIndex("nome"));

                        Pokemon p = new Pokemon(nome);

                        // adiciona user na lista que será retornada
                        pokemons.add(p);
                    } while(c.moveToNext());
                }
            } catch (Exception e) {
                // trata exceção
            } finally {
                db.close();
            }

            return pokemons;
        }

        public void insertAll(ArrayList<Pokemon> pokemons) {
            SQLiteDatabase db = this.getWritableDatabase();
            for (int i = 0; i < pokemons.size(); i++) {
                try {
                    ContentValues cv = new ContentValues();
                    cv.put("nome", pokemons.get(i).getName());
                    db.insert("Pokemons", null, cv);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.close();
                }
            }
        }

        public void deleteAll(){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from Pokemons");
        }
    }