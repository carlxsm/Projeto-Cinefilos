package model.cinema;

public enum TipoSala {
    BASICA("Básica",20,60), SALA3D("Sala 3D",30,60),VIP("Sala VIP",40,20);

    private String nome;
    private double preco;
    private int quantidadePoltronas;
    TipoSala(String nome, double preco, int quantidadePoltronas) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadePoltronas = quantidadePoltronas;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadePoltronas() {
        return quantidadePoltronas;
    }

    @Override
    public String toString() {
        return "TipoSala "+ this.nome;
    }
}



// SALA,FILME
