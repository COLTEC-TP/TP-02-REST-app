package com.example.a2016951790.tp_02_movieme;

/**
 * Created by a2016951790 on 14/08/18.
 */

public class Filme {

    private String Titulo;
    private String Rating;
    private int[] Gender;
    private String Ano;
    private String foto;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public int[] getGender() {
        return Gender;
    }

    public void setGender(int[] gender) {
        Gender = gender;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String ano) {
        Ano = ano;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
