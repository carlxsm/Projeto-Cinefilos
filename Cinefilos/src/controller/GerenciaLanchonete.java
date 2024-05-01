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

    public static ProdutoLanchonete getProdLanchonetePorCodigo(String codigo) {
        for (ProdutoLanchonete prod : produtosDisponiveis) {
            if (prod.getCodigo().equalsIgnoreCase(codigo)) {
                return prod;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado.");
    }

    //FIXME Não esta atualizando a quantidade, quando adicionado no carrinho
    public static void atualizaQuantidadeProduto(String codigo, int quantidade){
        for (ProdutoLanchonete prod : produtosDisponiveis) {
            if (prod.getCodigo().equalsIgnoreCase(codigo)) {
                prod.setQuantidade(quantidade);
            }
        }
    }
    public void adicionaProduto(ProdutoLanchonete produtoLanchonete){
        if (produtoLanchonete.getNome().isEmpty()|| produtoLanchonete.getNome().isBlank() || produtoLanchonete.getNome().length() < 4){
            throw new IllegalArgumentException("Insira um nome válido!");
        }
        if (produtoLanchonete.getPreco() < 0){
            throw new IllegalArgumentException("O preço precisa ser maior que zero");
        }
        if (produtoLanchonete.getQuantidade() < 0){
            throw new IllegalArgumentException("O quantidade precisa ser maior que zero");
        }
        produtosDisponiveis.add(produtoLanchonete);
    }
    public void remove(ProdutoLanchonete produtoLanchonete){
        produtosDisponiveis.remove(produtoLanchonete);
    }
}
