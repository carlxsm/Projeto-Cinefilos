package model.sistema.usuario;

import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.fidelidade.ProgramaFidelidade;

public class Cliente extends Usuario {

    private ProgramaFidelidade ProgramaFidelidade;
    private NivelFidelidade NivelFidelidade;

    public Cliente(String nome, String senha, int categoriaUsuario, int pontosInciais) {
        super(nome, senha, categoriaUsuario);
        this.ProgramaFidelidade = new ProgramaFidelidade(pontosInciais);
    }

    public int getPontos() {
        return ProgramaFidelidade.getPontos();
    }

    public NivelFidelidade getNivelFidelidade() {
        return NivelFidelidade;
    }

    public void acumulaPontos(int pontos) {
        ProgramaFidelidade.setPontos(ProgramaFidelidade.getPontos() + pontos);
    }

}
