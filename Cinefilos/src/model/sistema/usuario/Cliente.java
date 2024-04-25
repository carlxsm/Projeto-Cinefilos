package model.sistema.usuario;

import controller.CarrinhoCompras;
import model.Produto;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.fidelidade.ProgramaFidelidade;

import java.util.List;

public class Cliente extends Usuario{
    private CarrinhoCompras carrinhoCompras = new CarrinhoCompras();
    private ProgramaFidelidade programaFidelidade = new ProgramaFidelidade(0);

    public Cliente(String nome, String senha, CategoriaUsuario categoriaUsuario) {
        super(nome, senha, categoriaUsuario);
    }

//    public List<Produto> getCarrinhoCompras() {
//        return carrinhoCompras.getCarrinhoDeCompras(); //TODO esse método está duplicado, só sei que aqui tem getCarrinho e em CarrinhoDeCompras tb, o correto seria apenas 1.
//    }
    public CarrinhoCompras getCarrinhoCompras() { // TODO resolver o nome desse metodo
        return carrinhoCompras;
    }

    public ProgramaFidelidade getProgramaFidelidade() {
        return programaFidelidade;
    }
    public void setProgramaFidelidade(ProgramaFidelidade programaFidelidade) {
        this.programaFidelidade = programaFidelidade;
    }
}
