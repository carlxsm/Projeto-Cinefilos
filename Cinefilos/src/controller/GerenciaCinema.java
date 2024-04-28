package controller;

import model.cinema.Filme;
import model.cinema.ProdutoIngressoCinema;
import model.cinema.Sala;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GerenciaCinema {
    private static List<Sala> salasCinema = new ArrayList<>();
    private static List<Filme> filmesEmCartaz = new ArrayList<>();
    private static List<List<ProdutoIngressoCinema>> ingressosDoCinema = new ArrayList<>();


    public static List<Sala> getSalasCinema() {
        return salasCinema;
    }

    public static List<Filme> getFilmesEmCartaz() {
        return filmesEmCartaz;
    }

    public void adicionaFilmeEmCartaz(String nomeFilme, int duracao) {
        Filme novoFilme = new Filme(nomeFilme,duracao);
        filmesEmCartaz.add(novoFilme);
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

    // TODO esse método parece encaixar melhor no Facade.
    public void adicionaFilmeNaSala(Sala sala, Filme filme,int horario){
        criaIngressos(filme,sala,horario);
        sala.adicionarFilme(filme);
    }

    public void criaIngressos(Filme filme, Sala sala, int horario){
        List<ProdutoIngressoCinema> ingressos = new ArrayList<>();
        for (int i = 0; i < sala.getTipoSala().getQuantidadePoltronas();i++){
            ingressos.add(new ProdutoIngressoCinema(filme,sala,i,horario));
        }
        ingressosDoCinema.add(ingressos);
    }

    public List<String> getIngressosDisponiveis(){
        List<String> ingressosDisponiveis = new ArrayList<>();
        Set<String> chavesUnicas = new HashSet<>();
        for (List<ProdutoIngressoCinema> ingresso: ingressosDoCinema){
            for (ProdutoIngressoCinema prodIngresso: ingresso){
                String chaveUnica = prodIngresso.getFilme().getNomeFilme()+ " | "+prodIngresso.getSala().getNomeSala() +" | "+ prodIngresso.getHorario();
                if (!chavesUnicas.contains(chaveUnica)){
                    ingressosDisponiveis.add(chaveUnica);
                    chavesUnicas.add(chaveUnica);
                }
            }
        }
        return ingressosDisponiveis;
    }

    public Sala getSalaCinema(int indice){
        return salasCinema.get(indice);
    }
    public Filme getFilmeNaSala(int indice){
        return filmesEmCartaz.get(indice);
    }
}
