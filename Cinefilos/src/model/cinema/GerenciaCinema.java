package model.cinema;

import java.util.SortedSet;
import java.util.TreeSet;

public class GerenciaCinema {
    private SortedSet<Sala> salasCinema = new TreeSet<>();
    private SortedSet<Filme> filmesEmCartaz = new TreeSet<>();

    public SortedSet<Sala> getSalasCinema() {
        return salasCinema;
    }

    public SortedSet<Filme> getFilmesEmCartaz() {
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
