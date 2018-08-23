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
        static final String NOME_USUARIO = "name";
        static final String USUARIO = "user";
        static final String EMAIL_USUARIO = "email";
        static final String SENHA_USUARIO = "senha";
        static final int VERSAO = 1;


        static final String TABELA_FILMES = "filmes";
        static final String ID_FILMES = "id_movie";
        static final String ID_API = "id_api_find";
        static final String NAME_API = "name_api";
        static final String GENDER_API = "gender_api";
        static final String GRADE_API = "grade_api";
        static final String YEAR_API = "year_api";
        static final String IMAGE_API = "image_api";
        static final String CONTROL = "control";

        static final String TABELA_FAVORITOS = "favoritos";
        static final String ID_USER = "id_user";
        static final String ID_MOVIE = "id_movie";

        static final String TABELA_NEXT = "proximos";
        static final String ID_CONTROLE = "controle";

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
                    + ID_API + " integer,"
                    + NAME_API + " text,"
                    + GENDER_API + " text,"
                    + YEAR_API + " text,"
                    + GRADE_API + " text,"
                    + IMAGE_API + " text,"
                    + CONTROL + " text"
                    +");";
            String tabela_fav = "CREATE TABLE "+TABELA_FAVORITOS+" ("
                    + ID_USER + " integer,"
                    + ID_MOVIE + " integer,"
                    + "CONSTRAINT fk_fav_user FOREIGN KEY (id_user) REFERENCES usuarios(_id),"
                    +  "CONSTRAINT fk_fav_movie FOREIGN KEY (id_movie) REFERENCES filmes(id_movie)"
                    +");";
            String tabela_next = "CREATE TABLE "+TABELA_NEXT+" ("
                    + ID_USER + " integer,"
                    + ID_MOVIE + " integer,"
                    + ID_CONTROLE + " integer,"
                    + "CONSTRAINT fk_fav_user FOREIGN KEY (id_user) REFERENCES usuarios(_id),"
                    +  "CONSTRAINT fk_fav_movie FOREIGN KEY (id_movie) REFERENCES filmes(id_movie)"
                    +");";

            db.execSQL(tabela_user);
            db.execSQL(tabela_movies);
            db.execSQL(tabela_fav);
            db.execSQL(tabela_next);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
            onCreate(db);
        }


}
