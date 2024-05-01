package model.sistema.usuario;

import controller.CarrinhoCompras;
import model.Produto;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.fidelidade.ProgramaFidelidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Cliente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;


    private CarrinhoCompras carrinhoCompras = new CarrinhoCompras();
    private ProgramaFidelidade programaFidelidade = new ProgramaFidelidade(0);

    public Cliente(String nome, String senha) {
        super(nome, senha, CategoriaUsuario.CLIENTE);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(carrinhoCompras, cliente.carrinhoCompras) && Objects.equals(programaFidelidade, cliente.programaFidelidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), carrinhoCompras, programaFidelidade);
    }
}
