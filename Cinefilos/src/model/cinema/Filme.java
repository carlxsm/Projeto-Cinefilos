package model.cinema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Filme {

    private String nomeFilme;
    private int horarioFilme;
    private int duracao;

    public Filme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }



}
