package com.italo.ibooks;

import android.os.Parcel;
import android.os.Parcelable;


//Implemented Parcelable to serialize the Livro Object so that it can pass over.

public class Livro implements Parcelable{
    private String titulo;   //titulo
    private String autor;  // autor
    private String infoUrl;
    private String imageUrl;
    private String editora;  //editora

    public Livro(String titulo, String autor, String infoUrl, String imageUrl, String editora){
        this.titulo = titulo;         //titulo
        this.autor = autor;       //autor
        this.infoUrl = infoUrl;
        this.imageUrl = imageUrl;
        this.editora = editora;  //editora
    }

    private Livro(Parcel in) {
        titulo = in.readString();  //titulo
        autor = in.readString();  // autor
        infoUrl = in.readString();
        imageUrl = in.readString();
        editora = in.readString();  //editora
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Livro> CREATOR = new Creator<Livro>() {
        @Override
        public Livro createFromParcel(Parcel in) {
            return new Livro(in);
        }

        @Override
        public Livro[] newArray(int size) {
            return new Livro[size];
        }
    };


    public String getTitulo() {
        return titulo;
    }      //titulo

    public String getAutor() {
        return autor;
    }    // autor

    public String getInfoUrl() {
        return infoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEditora() {
        return editora;
    }  //editora


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);     //titulo
        parcel.writeString(autor);    //autor
        parcel.writeString(infoUrl);
        parcel.writeString(imageUrl);
        parcel.writeString(editora);  //editora
    }
}
