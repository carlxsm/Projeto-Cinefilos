package model.lanchonete;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoLanchonete extends Produto {

    private static List<ProdutoLanchonete> listaProdutos = new ArrayList<>();

    public ProdutoLanchonete(String nome, double preco, int quantidade, String codigo) {
        super(nome, preco, quantidade, codigo);
        listaProdutos.add(this);
    }

    public static List<ProdutoLanchonete> getListaProdutos() {
        return listaProdutos;
    }

    @Override
    public String toString() {
        return super.getNome() + " | " + super.getPreco() + " | " + super.getCodigo();
    }
}
