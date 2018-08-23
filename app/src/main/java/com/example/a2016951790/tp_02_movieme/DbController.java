package com.example.a2016951790.tp_02_movieme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by a2016951790 on 07/08/18.
 */

public class DbController {
    private SQLiteDatabase db;
    private SQLiteDatabase db1;
    private DbOpener banco;

    public DbController(Context context){
        banco = new DbOpener(context);
    }

    public long insertUser(String mail, String pass, String name, String user){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DbOpener.EMAIL_USUARIO, mail);
        valores.put(DbOpener.USUARIO, user);
        valores.put(DbOpener.NOME_USUARIO, name);
        valores.put(DbOpener.SENHA_USUARIO, pass);

        resultado = db.insert(DbOpener.TABELA_USUARIO, null, valores);
        db.close();

        return resultado;

    }

    public void salvarFilme(int controle, int id_api, int id_user, String name, String gender, String grade, String year, String imagem, Context context){
        db = banco.getWritableDatabase();
        ContentValues valor;

        db1 = banco.getReadableDatabase();

        String querySql = "SELECT * FROM filmes WHERE id_api_find = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{String.valueOf(id_api)});
        cursor.moveToFirst();
        int errado = 0;

        if(cursor.getCount() > 0){
            if(cursor.getInt(1) == id_api && cursor.getInt(7) == controle) {
                errado++;
            } else if (cursor.getInt(1) == id_api && cursor.getInt(7) != controle){
                errado++;
                errado++;
            }
        }
        //TODO
        if(errado != 1) {
            valor = new ContentValues();
            valor.put(DbOpener.ID_API, id_api);
            valor.put(DbOpener.NAME_API, name);
            valor.put(DbOpener.GENDER_API, gender);
            valor.put(DbOpener.GRADE_API, grade);
            valor.put(DbOpener.YEAR_API, year);
            valor.put(DbOpener.IMAGE_API, imagem);
            valor.put(DbOpener.CONTROL, controle);

            long id = db.insert(DbOpener.TABELA_FILMES, null, valor);

            Integer i = (int) (long) id;

            Toast.makeText(context, String.valueOf(id_api), Toast.LENGTH_LONG).show();

            if (controle == 0)
                salvarFavorito(i, id_user);
            else if (controle == 1)
                salvarProximo(i, id_user);
        }
        else {
            Toast.makeText(context, "Ja existe", Toast.LENGTH_LONG).show();
        }

        cursor.close();
        db1.close();
    }

    public void salvarFavorito(int id_movie, int id_user){
        db = banco.getWritableDatabase();
        ContentValues valores;

        valores = new ContentValues();
        valores.put(DbOpener.ID_MOVIE, id_movie);
        valores.put(DbOpener.ID_USER, id_user);
        db.insert(DbOpener.TABELA_FAVORITOS, null, valores);

    }

    public Integer pegarUsuarioPorEmail(String user, String pass){

        db = banco.getReadableDatabase();

        Integer valor;

        String querySql = "SELECT * FROM usuarios WHERE user = ?";
        Cursor cursor = db.rawQuery(querySql, new String[] {user});

        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            if(cursor.getString(4).equals(pass)){
                valor = cursor.getInt(0);
            } else{
                valor = -2;
            }
        } else {
            valor = -1;
        }

        cursor.close();
        db.close();

        return valor;
    }

    public String[] pegarUsuarioPorID(String id){

        db = banco.getReadableDatabase();

        String[] valor = new String[2];

        String querySql = "SELECT * FROM usuarios WHERE _id = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{id});
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            if(cursor.getInt(0) == Integer.parseInt(id)) {
                valor[0] = cursor.getString(2);
                valor[1] = cursor.getString(3);
            }
        }

        cursor.close();
        db.close();

        return valor;
    }

    public ArrayList pegarFavoritoPorID(String id){

        db = banco.getReadableDatabase();

        ArrayList<Integer> valor = new ArrayList<>();

        String querySql = "SELECT * FROM favoritos WHERE id_user = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{id});
        cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                if(cursor.getInt(0) == Integer.parseInt(id)) {
                    valor.add(cursor.getInt(1));
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return valor;
    }

    public ArrayList<Filme> pegarFilmePorID(ArrayList<Integer>  valor){
        db = banco.getReadableDatabase();

        String querySql = "SELECT * FROM filmes WHERE id_movie = ?";
        ArrayList<Filme> filmes = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        int[ ] factorial = { 12, 16};


        for(int i = 0; i < valor.size(); i++) {
            Cursor cursor = db.rawQuery(querySql, new String[]{valor.get(i).toString()});
            cursor.moveToFirst();
            if(cursor.getInt(0) == valor.get(i)) {
                Filme filme = new Filme();
                filme.setId(cursor.getInt(1));
                filme.setTitulo(cursor.getString(2));
                filme.setSubtitle(cursor.getString(3));
                filme.setAno(cursor.getString(4));
                filme.setRating(cursor.getString(5));
                filme.setFoto(cursor.getString(6));
                filme.setGender(factorial);

                filmes.add(filme);
            }

        }

        return filmes;
    }

    public void salvarProximo(int id_movie, int id_user){
        db = banco.getWritableDatabase();
        ContentValues valores;

        valores = new ContentValues();
        valores.put(DbOpener.ID_MOVIE, id_movie);
        valores.put(DbOpener.ID_USER, id_user);
        db.insert(DbOpener.TABELA_NEXT, null, valores);

    }

    public ArrayList pegarProximoPorID(String id){

        db = banco.getReadableDatabase();

        ArrayList<Integer> valor = new ArrayList<>();

        String querySql = "SELECT * FROM proximos WHERE id_user = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{id});
        cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            do {
                if(cursor.getInt(0) == Integer.parseInt(id)) {
                    valor.add(cursor.getInt(1));
                }
            }
            while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return valor;
    }



}
