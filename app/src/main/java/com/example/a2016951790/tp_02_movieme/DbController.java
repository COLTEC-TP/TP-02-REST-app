package com.example.a2016951790.tp_02_movieme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by a2016951790 on 07/08/18.
 */

public class DbController {
    private SQLiteDatabase db;
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

    public void salvarFilme(int id_api, int id_user, Context context){
        db = banco.getWritableDatabase();
        ContentValues valor;

        valor = new ContentValues();
        valor.put(DbOpener.ID_API, id_api);

        long id = db.insert(DbOpener.TABELA_FILMES, null, valor);

        Integer i = (int) (long) id;

        Toast.makeText(context, i.toString(), Toast.LENGTH_LONG).show();

        salvarFavorito(i, id_user);
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


}
