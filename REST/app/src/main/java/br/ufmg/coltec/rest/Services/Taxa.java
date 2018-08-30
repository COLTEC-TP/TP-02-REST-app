package br.ufmg.coltec.rest.Services;

/**
 * Created by a2016951600 on 22/08/18.
 */

public class Taxa {

    private String nome;
    private float valor;

    public Taxa(String nome, float valor){
        this.nome=nome;
        this.valor=valor;
    }

    public float getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
