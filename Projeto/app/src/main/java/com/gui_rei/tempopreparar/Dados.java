package com.gui_rei.tempopreparar;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.gui_rei.tempopreparar.rest.current.ClimaAtual;
import com.gui_rei.tempopreparar.rest.days.Dias;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;

public class Dados {
    private static Context context;
    private static final Dados ourInstance = new Dados();
    private static final String FILEDIAS = "dadosdias";
    private static final String FILEATUAL = "dadosatual";

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
    public static void setContext(Context context) {
        Dados.context = context;
    }

    private boolean temDadosAtual(){
        if (climaAtual == null) return false;
        else return true;
    }
    private boolean temDadosDias(){
        if (climaDias == null) return false;
        else return true;
    }
    private ClimaAtual getBancoA(){
        File file = null;
        String string = "";
        FileInputStream inputStream;
        try {
            file = new File(context.getCacheDir(), FILEATUAL);
            if (!file.exists()){ //se ele nunca existiu indicar ao get que não tem essa cidade
                ClimaAtual climaAtual = new ClimaAtual();
                climaAtual.setId(0);
                return climaAtual;
            }
            inputStream = new FileInputStream(file);
            int c;
            while( (c = inputStream.read()) != -1){
                string = string + Character.toString((char)c);
            }
            Log.d("leitura", string);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ClimaAtual>(){}.getType();
        ClimaAtual obj = gson.fromJson(string, type);
        return obj;
    }
    private Dias getBancoD(){
        File file = null;
        String string = "";
        FileInputStream inputStream;
        try {
            file = new File(context.getCacheDir(), FILEDIAS);
            if (!file.exists()){ //se ele nunca existiu indicar ao get que não tem essa cidade
                Dias dias = new Dias();
                dias.setId(0);
                return dias;
            }
            inputStream = new FileInputStream(file);
            int c;
            while( (c = inputStream.read()) != -1){
                string = string + Character.toString((char)c);
            }
            Log.d("leitura", string);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<Dias>(){}.getType();
        Dias obj = gson.fromJson(string, type);
        return obj;
    }

    public void setClimaAtual(ClimaAtual climaAtual) {
        this.climaAtual = climaAtual;

        Gson gson = new Gson();
        String string = gson.toJson(climaAtual);

        File file;
        FileOutputStream outputStream;
        try {
            file = new File(context.getCacheDir(), FILEATUAL);
            outputStream = new FileOutputStream(file);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setClimaDias(Dias climaDias) {
        this.climaDias = climaDias;

        Gson gson = new Gson();
        String string = gson.toJson(climaDias);

        File file;
        FileOutputStream outputStream;
        try {
            file = new File(context.getCacheDir(), FILEDIAS);
            outputStream = new FileOutputStream(file);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ClimaAtual getClimaAtual(int cityId) {
        if(temDadosAtual()) {
            if (climaAtual.getId() == cityId) return climaAtual;
            else return null;
        }
        else {
            setClimaAtual(getBancoA());
            return getClimaAtual(cityId);
        }
    }
    public Dias getClimaDias(int cityId) {
        if(temDadosDias()) {
            if (climaDias.getId() == cityId) return climaDias;
            else return null;
        }
        else {
            setClimaDias(getBancoD());
            return getClimaDias(cityId);
        }
    }
}
