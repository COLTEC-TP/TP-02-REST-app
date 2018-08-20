package com.example.a2016951790.tp_02_movieme;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by a2016951790 on 07/08/18.
 */

    public class DbOpener extends SQLiteOpenHelper {
        static final String NOME_BANCO_USUARIO = "usuario.db";
        static final String TABELA_USUARIO = "usuarios";
        static final String ID = "_id";
        static final String NOME_USUARIO = "name";
        static final String USUARIO = "user";
        static final String EMAIL_USUARIO = "email";
        static final String SENHA_USUARIO = "senha";
        static final int VERSAO = 1;


        static final String TABELA_FILMES = "filmes";
        static final String ID_FILMES = "id_movie";
        static final String ID_API = "id_api_find";

        static final String TABELA_FAVORITOS = "favoritos";
        static final String ID_USER = "id_user";
        static final String ID_MOVIE = "id_movie";

    public DbOpener(Context context){
        super(context, NOME_BANCO_USUARIO,null,VERSAO);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String tabela_user = "CREATE TABLE IF NOT EXISTS "+TABELA_USUARIO+" ("
                    + ID + " integer primary key autoincrement,"
                    + USUARIO + " text,"
                    + NOME_USUARIO + " text,"
                    + EMAIL_USUARIO + " text,"
                    + SENHA_USUARIO + " text"
                    +");";
            String tabela_movies = "CREATE TABLE "+TABELA_FILMES+" ("
                    + ID_FILMES + " integer primary key autoincrement,"
                    + ID_API + " integer"
                    +");";
            String tabela_fav = "CREATE TABLE "+TABELA_FAVORITOS+" ("
                    + ID_USER + " integer,"
                    + ID_MOVIE + " integer,"
                    + "CONSTRAINT fk_fav_user FOREIGN KEY (id_user) REFERENCES usuarios(_id),"
                    +  "CONSTRAINT fk_fav_movie FOREIGN KEY (id_movie) REFERENCES filmes(id_movie)"
                    +");";

            StringBuilder sql = new StringBuilder();
            sql.append(tabela_user).append(tabela_movies).append(tabela_fav);
            Log.d("TAG", sql.toString());
            db.execSQL(sql.toString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
            onCreate(db);
        }


}
