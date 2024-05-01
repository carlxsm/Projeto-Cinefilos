package model.lanchonete;

import model.Produto;

import java.io.Serializable;

public class ProdutoLanchonete extends Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProdutoLanchonete() {
        super();
    }

    public ProdutoLanchonete(String nome, double preco, int quantidade, String codigo) {
        super(nome, preco, quantidade, codigo);
    }


    @Override
    public String toString() {
        return super.getNome()+" | "+super.getPreco()+" | Cod: "+super.getCodigo();
    }

}
