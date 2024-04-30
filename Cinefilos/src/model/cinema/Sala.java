package model.cinema;

import java.util.HashSet;
import java.util.Set;

public class Sala {

    // com essa estrutura, nao pode haver elementos repitidos
    // Set é uma interface que nao permite
    private Set<Filme> programacaoFilmes = new HashSet<>();
    private String nomeSala;
    private TipoSala tipoSala;

    public Sala(String nomeSala, String tipoSala) {
        this.nomeSala = nomeSala;
        this.tipoSala = TipoSala.valueOf(tipoSala);
    }

    public Set<Filme> getProgramacaoFilmes() {
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

    public void adicionarFilme(Filme filme) {
        programacaoFilmes.add(filme);
    }

    public void removeFilme(Filme filme) {
        programacaoFilmes.remove(filme);
    }

    public void atualizaFilme(Filme filmeAntigo, Filme filmeNovo) {
        if (programacaoFilmes.contains(filmeAntigo)) {
            programacaoFilmes.remove(filmeAntigo);
            programacaoFilmes.add(filmeNovo);
        }
    }
}
