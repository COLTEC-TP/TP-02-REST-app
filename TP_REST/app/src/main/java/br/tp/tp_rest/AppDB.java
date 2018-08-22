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
        private static final String SCRIPT_CREATE = "CREATE TABLE Pokemons (nome TEXT, agilidade INT, hp INT, ataque INT, defesa INT, super_ataque INT, super_defesa INT, imagem TEXT, tipo1 TEXT, tipo2 TEXT)";

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
                cv.put("imagem", p.getSprite().getUrl());
                /** 0 -> agilidade
                 *  1 -> super defesa
                 *  2 -> super ataque
                 *  3 -> defesa
                 *  4 -> ataque
                 *  5-> hp
                **/

                cv.put("ataque", p.getStats().get(4).getBase_stat());
                cv.put("defesa", p.getStats().get(3).getBase_stat());
                cv.put("agilidade", p.getStats().get(0).getBase_stat());
                cv.put("hp", p.getStats().get(5).getBase_stat());
                cv.put("super_ataque", p.getStats().get(2).getBase_stat());
                cv.put("super_defesa", p.getStats().get(1).getBase_stat());

                cv.put("tipo1", p.getPokeTypes().get(0).getNamePokeType());
                if (p.getPokeTypes().size() > 1){
                    cv.put("tipo2", p.getPokeTypes().get(1).getNamePokeType());
                }else{
                    cv.put("tipo2", "null");
                }

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
                        ArrayList<Stat> stats = new ArrayList<>();
                        stats.add(new Stat(c.getInt(c.getColumnIndex("ataque"))));
                        stats.add(new Stat(c.getInt(c.getColumnIndex("defesa"))));
                        stats.add(new Stat(c.getInt(c.getColumnIndex("agilidade"))));
                        stats.add(new Stat(c.getInt(c.getColumnIndex("hp"))));
                        stats.add(new Stat(c.getInt(c.getColumnIndex("super_ataque"))));
                        stats.add(new Stat(c.getInt(c.getColumnIndex("super_defesa"))));

                        Sprite sprite = new Sprite(c.getString(c.getColumnIndex("imagem")));

                        ArrayList<PokeType> types = new ArrayList<>();
                        PokeType tipo1 = new PokeType(new NameOfPokeType(c.getString(c.getColumnIndex("tipo1"))));
                        PokeType tipo2 = new PokeType(new NameOfPokeType(c.getString(c.getColumnIndex("tipo2"))));
                        types.add(tipo1);
                        if (!tipo2.getNamePokeType().equals("null")){
                            types.add(tipo2);
                        }

                        Pokemon p = new Pokemon(nome, stats, sprite, types);

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


        public void deleteAll(){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from Pokemons");
        }
    }