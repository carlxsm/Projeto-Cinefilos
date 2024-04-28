package controller;

import model.Produto;
import model.lanchonete.ProdutoLanchonete;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class GerenciaLanchonete {

    private static List<ProdutoLanchonete> produtosDisponiveis = new ArrayList<>();

    public static List<ProdutoLanchonete> getProdutosDisponiveis(){
        return produtosDisponiveis;
    }

    public static Produto getProdLanchonetePorCodigo(String codigo) {
        //TODO implementar esse método como no GerenciaCinema
        return null;
    }

    public void adicionaProduto(ProdutoLanchonete produtoLanchonete){
        produtosDisponiveis.add(produtoLanchonete);
    }
    public void remove(ProdutoLanchonete produtoLanchonete){
        produtosDisponiveis.remove(produtoLanchonete);
    }
}
