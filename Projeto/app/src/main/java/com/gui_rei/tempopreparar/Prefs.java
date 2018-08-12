package com.gui_rei.tempopreparar;

public class Prefs {
    private static final Prefs ourInstance = new Prefs();

    private int city;

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
}
