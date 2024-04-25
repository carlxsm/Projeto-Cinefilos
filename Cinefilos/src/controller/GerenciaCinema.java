package controller;

import model.cinema.Filme;
import model.cinema.Sala;

import java.util.SortedSet;
import java.util.TreeSet;

public class GerenciaCinema {
    private static SortedSet<Sala> salasCinema = new TreeSet<>();
    private static SortedSet<Filme> filmesEmCartaz = new TreeSet<>();

    public static SortedSet<Sala> getSalasCinema() {
        return salasCinema;
    }

    public static SortedSet<Filme> getFilmesEmCartaz() {
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
