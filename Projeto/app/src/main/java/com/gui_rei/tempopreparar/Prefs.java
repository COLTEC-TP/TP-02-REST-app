package com.gui_rei.tempopreparar;

public class Prefs {
    private static final Prefs ourInstance = new Prefs();

    public static int tempMostrarAviso = 1; //Se eh pra mostrar o aviso de rotina(seta pra 0 quando mostrar) //Esse é temporário, n eh pra salvar

    private int city;
    private int temRotinaAmanha = 0; //Para test por enquanto, depois ser uma classe q armazena data e mais

    public static Prefs getInstance() {
        return ourInstance;
    }
    private Prefs() {
        city = 6879;
    }


    public int getCity() {
        return city;
    }
    public void setCity(int city) {
        this.city = city;
    }

    public int getTemRotinaAmanha() {
        return temRotinaAmanha;
    }
    public void setTemRotinaAmanha(int temRotinaAmanha) {
        this.temRotinaAmanha = temRotinaAmanha;
    }
}
