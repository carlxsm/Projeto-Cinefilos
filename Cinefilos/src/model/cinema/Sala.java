package model.cinema;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private List<Filme> programacaoFilmes = new ArrayList<>(); // TODO esse atributo so está servindo para verificar se o filme já está cadastrado na sala, mas ele não ta legal não, ta bem zoado.
    private String nomeSala;
    private TipoSala tipoSala;

    public Sala( String nomeSala, String tipoSala) {
        this.nomeSala = nomeSala;
        this.tipoSala = TipoSala.valueOf(tipoSala);
    }

    public List<Filme> getProgramacaoFilmes() {
        return programacaoFilmes;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public int adicionarFilme( Filme filme){
        if (!programacaoFilmes.contains(filme)){
            programacaoFilmes.add(filme);
            return programacaoFilmes.indexOf(filme);
        }
        return -1;
    }
    public void removeFilme(Filme filme){
        programacaoFilmes.remove(filme);
    }
    public void atualizaFilme(){
        // TODO FAZER METODO

    }
}
