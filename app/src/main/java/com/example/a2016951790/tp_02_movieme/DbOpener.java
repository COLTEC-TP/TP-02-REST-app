package com.example.a2016951790.tp_02_movieme;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a2016951790 on 07/08/18.
 */

    public class DbOpener extends SQLiteOpenHelper {
        static final String NOME_BANCO_USUARIO = "usuario.db";
        static final String TABELA_USUARIO = "usuarios";
        static final String ID = "_id";
        static final String EMAIL_USUARIO = "email";
        static final String SENHA_USUARIO = "senha";
        static final int VERSAO = 1;

    public DbOpener(Context context){
        super(context, NOME_BANCO_USUARIO,null,VERSAO);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE "+TABELA_USUARIO+" ("
                    + ID + " integer primary key autoincrement,"
                    + EMAIL_USUARIO + " text,"
                    + SENHA_USUARIO + " text"
                    +")";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
            onCreate(db);
        }


}
