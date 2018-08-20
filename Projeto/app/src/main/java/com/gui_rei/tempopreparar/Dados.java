package com.gui_rei.tempopreparar;

import com.gui_rei.tempopreparar.rest.current.ClimaAtual;
import com.gui_rei.tempopreparar.rest.days.Dias;

public class Dados { //TODO Salvar no banco de dados
    private static final Dados ourInstance = new Dados();

    //TODO Guardar dados de mais de uma cidade
    private ClimaAtual climaAtual;
    private Dias climaDias;

    public static Dados getInstance(){
        return ourInstance;
    }

    private Dados(){
        climaAtual = null;
        climaDias = null;
    }

    public void setClimaAtual(ClimaAtual climaAtual) {
        this.climaAtual = climaAtual;
    }
    public void setClimaDias(Dias climaDias) {
        this.climaDias = climaDias;
    }
    public ClimaAtual getClimaAtual(int cityId) {
        if(climaAtual != null) {
            if (climaAtual.getId() == cityId) return climaAtual;
            else return null;
        }
        else return null;
    }
    public Dias getClimaDias(int cityId) {
        if(climaDias != null) {
            if (climaDias.getId() == cityId) return climaDias;
            else return null;
        }
        else return null;
    }
}
