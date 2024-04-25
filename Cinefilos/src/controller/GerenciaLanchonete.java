package controller;

import model.lanchonete.ProdutoLanchonete;

import java.util.SortedSet;
import java.util.TreeSet;

public class GerenciaLanchonete {

    private SortedSet<ProdutoLanchonete> produtosDisponiveis = new TreeSet<>();

    public SortedSet<ProdutoLanchonete> getProdutosDisponiveis(){
        return produtosDisponiveis;
    }
    public void adicionaProduto(ProdutoLanchonete produtoLanchonete){
        produtosDisponiveis.add(produtoLanchonete);
    }
    public void remove(ProdutoLanchonete produtoLanchonete){
        produtosDisponiveis.remove(produtoLanchonete);
    }
}
