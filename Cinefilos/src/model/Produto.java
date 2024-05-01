package model;

import java.io.Serializable;

public abstract class Produto implements Serializable {

    String nome;
    double preco;
    int quantidade;
    String codigo;


    public Produto(String nome, double preco, int quantidade, String codigo) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.codigo = codigo;

    }

    public Produto() {

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
