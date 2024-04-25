package controller;

import model.cinema.Filme;
import model.cinema.Sala;

import java.util.ArrayList;
import java.util.List;

public class GerenciaCinema {
    private static List<Sala> salasCinema = new ArrayList<>();
    private static List<Filme> filmesEmCartaz = new ArrayList<>();

    public static List<Sala> getSalasCinema() {
        return salasCinema;
    }

    public static List<Filme> getFilmesEmCartaz() {
        return filmesEmCartaz;
    }

    public void adicionaFilmeEmCartaz(Filme filme) {
        filmesEmCartaz.add(filme);
    }

    public void removeFilmeEmCartaz(Filme filme) {
        filmesEmCartaz.remove(filme);
    }
    public void adicionaSalaCinema(Sala sala){
        salasCinema.add(sala);
    }

    public void removeSalaCinema(Sala sala){
        salasCinema.remove(sala);
    }
}
