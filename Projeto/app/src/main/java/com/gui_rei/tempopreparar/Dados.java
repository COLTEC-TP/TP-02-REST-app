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

    private boolean temDadosAtual(){
        if (climaAtual == null) return false;
        else return true;
    }
    private boolean temDadosDias(){
        if (climaDias == null) return false;
        else return true;
    }

    public void setClimaAtual(ClimaAtual climaAtual) {
        this.climaAtual = climaAtual;
    }
    public void setClimaDias(Dias climaDias) {
        this.climaDias = climaDias;
    }
    public ClimaAtual getClimaAtual(int cityId) {
        if(temDadosAtual()) {
            if (climaAtual.getId() == cityId) return climaAtual;
            else return null;
        }
        else return null;
    }
    public Dias getClimaDias(int cityId) {
        if(temDadosDias()) {
            if (climaDias.getId() == cityId) return climaDias;
            else return null;
        }
        else return null;
    }
}
