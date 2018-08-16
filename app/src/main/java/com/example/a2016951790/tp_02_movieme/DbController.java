package com.example.a2016951790.tp_02_movieme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a2016951790 on 07/08/18.
 */

public class DbController {
    private SQLiteDatabase db;
    private DbOpener banco;

    public DbController(Context context){
        banco = new DbOpener(context);
    }

    public String insertUser(String mail, String pass, String name, String user){
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

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

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
