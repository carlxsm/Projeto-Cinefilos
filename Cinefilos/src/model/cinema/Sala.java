package model.cinema;

import java.util.ArrayList;

public class Sala {
    private final int  QUANTIDADE_POLTRONAS;
    private ArrayList<Filme> programacaoFilmes = new ArrayList<>();
    private String nomeSala;
    private String filmeAtual;
    private TipoSala tipoSala;

    public Sala(int QUANTIDADE_POLTRONAS, ArrayList<Filme> programacaoFilmes, String nomeSala, String filmeAtual, TipoSala tipoSala) {
        this.QUANTIDADE_POLTRONAS = QUANTIDADE_POLTRONAS;
        this.programacaoFilmes = programacaoFilmes;
        this.nomeSala = nomeSala;
        this.filmeAtual = filmeAtual;
        this.tipoSala = tipoSala;
    }

    public int getQUANTIDADE_POLTRONAS() {
        return QUANTIDADE_POLTRONAS;
    }

    public ArrayList<Filme> getProgramacaoFilmes() {
        return programacaoFilmes;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public String getFilmeAtual() {
        return filmeAtual;
    }

    public void setFilmeAtual(String filmeAtual) {
        this.filmeAtual = filmeAtual;
    }
    public TipoSala getTipoSala() {
        return tipoSala;
    }
    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
    public int adicionarFilme( Filme filme){
        programacaoFilmes.add(filme);
        return programacaoFilmes.indexOf(filme);
    }
    public void removeFilme(Filme filme){
        programacaoFilmes.remove(filme);
    }
    public void atualizaFilme(){
        // TODO FAZER METODO

    }
}
