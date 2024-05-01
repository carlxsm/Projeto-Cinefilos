package model.cinema;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeFilme;
    private int duracao;

    public Filme(String nomeFilme,int duracao) {
        this.nomeFilme = nomeFilme;
        this.duracao = duracao;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return getNomeFilme() +" | "+ getDuracao()+" minutos";
    }
}
