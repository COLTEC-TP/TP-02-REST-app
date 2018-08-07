package com.example.a2016951790.tp_02_movieme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by a2016951790 on 07/08/18.
 */

public class DbController {
    private SQLiteDatabase db;
    private DbOpener banco;

    public DbController(Context context){
        banco = new DbOpener(context);
    }

    public String insertUser(String mail, String pass){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DbOpener.EMAIL_USUARIO, mail);
        valores.put(DbOpener.SENHA_USUARIO, pass);

        resultado = db.insert(DbOpener.TABELA_USUARIO, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Integer pegarUsuarioPorEmail(String email, String pass){

        db = banco.getReadableDatabase();

        Integer valor;

        String querySql = "SELECT senha FROM usuarios WHERE email = ?";
        Cursor cursor = db.rawQuery(querySql, new String[] {email});

        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            Log.i("User", cursor.getString(0));
            if(cursor.getString(0).equals(pass)){
                valor = 2;
            } else{
                valor = 1;
            }
        } else {
            valor = 0;
        }

        cursor.close();
        db.close();

        return valor;
    }


}
