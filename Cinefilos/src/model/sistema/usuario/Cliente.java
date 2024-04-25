package model.sistema.usuario;

import java.util.List;

import controller.CarrinhoCompras;
import model.Produto;
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

    public List<Produto> getCarrinhoCompras() {
        return carrinhoCompras.getCarrinhoDeCompras(); // TODO esse método está duplicado, só sei que aqui tem
                                                       // getCarrinho e em CarrinhoDeCompras tb, o correto seria apenas
                                                       // 1.
    }

    public CarrinhoCompras getCarrinhoComprasObj() { // TODO resolver o nome desse metodo
        return carrinhoCompras;
    }

    public ProgramaFidelidade getProgramaFidelidade() {
        return programaFidelidade;
    }

    public void setProgramaFidelidade(ProgramaFidelidade programaFidelidade) {
        this.programaFidelidade = programaFidelidade;
    }
}
