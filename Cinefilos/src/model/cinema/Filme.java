package model.cinema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Filme {

    private String nomeFilme;
    private int duracao;
    private int horario;

    public Filme(String nomeFilme, int duracao, int horario) {
        this.nomeFilme = nomeFilme;
        this.duracao = duracao;
        this.horario = horario;
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

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return getNomeFilme() + " | " + getDuracao() + " minutos";
    }
}
