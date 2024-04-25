package model.lanchonete;

import model.ProdutoInterface;
import model.cinema.Filme;

public class ProdutoLanchonete implements ProdutoInterface {
    private String nome;
    private double preco;
    private int quantidade;

    public ProdutoLanchonete(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String getDescricao() {
        return ("Descrição do produto: [ \nNome: " + getNome() +
                "\nPreço: " + getPreco() +
                "\nQuantidade: " + getQuantidade() +
                "\n]");
    }
}
