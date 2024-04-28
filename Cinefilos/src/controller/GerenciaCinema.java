package controller;

import model.Produto;
import model.cinema.Filme;
import model.cinema.ProdutoIngressoCinema;
import model.cinema.Sala;

import java.util.*;

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
        String codigoIngresso = "C"+GerenciaSistema.geraCodigo();
        for (int i = 0; i < sala.getTipoSala().getQuantidadePoltronas();i++){
            ingressos.add(new ProdutoIngressoCinema(filme,sala,i,horario,codigoIngresso));
        }
        ingressosDoCinema.add(ingressos);
    }

    public List<String> getIngressosDisponiveis(){
        List<String> ingressosDisponiveis = new ArrayList<>();
        Set<String> chavesUnicas = new HashSet<>();
        for (List<ProdutoIngressoCinema> ingresso: ingressosDoCinema){
            for (ProdutoIngressoCinema prodIngresso: ingresso){
                String chaveUnica = prodIngresso.getFilme().getNomeFilme()+ " | "+prodIngresso.getSala().getNomeSala() +" | "+ prodIngresso.getHorario()+ " | "
                        +"Cod: "+ prodIngresso.getCodigo()+"| Ingressos disponiveis:  "+ quantidadeDeIngressosDisponiveis(prodIngresso.getCodigo());
                if (!chavesUnicas.contains(chaveUnica)){
                    ingressosDisponiveis.add(chaveUnica);
                    chavesUnicas.add(chaveUnica);
                }
            }
        }
        return ingressosDisponiveis;
    }

    public int quantidadeDeIngressosDisponiveis(String codigo){
        int quantidade = 0;
        for (List<ProdutoIngressoCinema> ingresso: ingressosDoCinema){
            for (ProdutoIngressoCinema prodIngresso: ingresso){
                if (prodIngresso.getCodigo().equals(codigo)){
                    quantidade++;
                }
            }
        }
        return quantidade;
    }

    public static Produto getIngressoPorCodigo(String cod){
        for (List<ProdutoIngressoCinema> ingresso: ingressosDoCinema){
            for (ProdutoIngressoCinema prodIngresso: ingresso){
                if (prodIngresso.getCodigo().equalsIgnoreCase(cod)){
                    return prodIngresso;
                }
            }
        }
        return null;
        //throw new IllegalArgumentException("Não existe ingresso com código informado.");
    }

    // OBSERVACAO
    public static void removeIngressoPorCodigo(String cod) {
        Iterator<List<ProdutoIngressoCinema>> iterator = ingressosDoCinema.iterator();
        while (iterator.hasNext()) {
            List<ProdutoIngressoCinema> listaIngressos = iterator.next();
            boolean ingressoRemovido = false; // Flag para controle da remoção
            for (ProdutoIngressoCinema prodIngresso : listaIngressos) {
                if (prodIngresso.getCodigo().equalsIgnoreCase(cod)) {
                    listaIngressos.remove(prodIngresso); // Remove apenas o ingresso específico
                    ingressoRemovido = true; // Marca como removido
                    break; // Sai do loop interno ao encontrar o ingresso
                }
            }

            // Se nenhum ingresso foi removido na iteração atual, verifica se a lista está vazia
            if (!ingressoRemovido && listaIngressos.isEmpty()) {
                iterator.remove(); // Remove a lista vazia se não houver mais ingressos
            }
        }
    }


    public Sala getSalaCinema(int indice){
        return salasCinema.get(indice);
    }
    public Filme getFilmeNaSala(int indice){
        return filmesEmCartaz.get(indice);
    }

    public static List<List<ProdutoIngressoCinema>> getIngressosDoCinema() {
        return ingressosDoCinema;
    }
}
