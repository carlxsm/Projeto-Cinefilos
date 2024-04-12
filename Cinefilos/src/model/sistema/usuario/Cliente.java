package model.sistema.usuario;

import controller.CarrinhoCompras;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.fidelidade.ProgramaFidelidade;

public class Cliente extends Usuario{
    private CarrinhoCompras carrinhoCompras = new CarrinhoCompras();
    private ProgramaFidelidade programaFidelidade = new ProgramaFidelidade(0);
    public Cliente(String nome, String senha, CategoriaUsuario categoriaUsuario) {
        super(nome, senha, categoriaUsuario);
    }

    public CarrinhoCompras getCarrinhoCompras() {
        return carrinhoCompras; //TODO esse método está duplicado, só sei que aqui tem getCarrinho e em CarrinhoDeCompras tb, o correto seria apenas 1.
    }
    public ProgramaFidelidade getProgramaFidelidade() {
        return programaFidelidade;
    }
    public void setProgramaFidelidade(ProgramaFidelidade programaFidelidade) {
        this.programaFidelidade = programaFidelidade;
    }
}
