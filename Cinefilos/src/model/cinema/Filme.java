package model.cinema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Filme {

    private String nomeFilme;
    private LocalTime horaFilme;
    private LocalDate dataFilme;
    private final ArrayList<Sala> salasExibicao = new ArrayList<>();

    public Filme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public LocalTime getHoraFilme() {
        return horaFilme;
    }

    public void setHoraFilme(LocalTime horaFilme) {
        this.horaFilme = horaFilme;
    }

    public LocalDate getDataFilme() {
        return dataFilme;
    }

    public void setDataFilme(LocalDate dataFilme) {
        this.dataFilme = dataFilme;
    }

    public ArrayList<Sala> getSalasExibicao() {
        return salasExibicao;
    }
    public int adicionarSalaExibicao(Sala sala){
        salasExibicao.add(sala);
        return salasExibicao.indexOf(sala);
    }
    public void removeSalaExibicao(Sala sala){
        salasExibicao.remove(sala);
    }


}
