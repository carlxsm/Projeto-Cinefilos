package model.cinema;

public enum TipoSala {
    BASICA(20,60), SALA3D(30,60),VIP(40,20);

    private double preco;
    private int quantidadePoltronas;
    TipoSala(double preco, int quantidadePoltronas) {
        this.preco = preco;
        this.quantidadePoltronas = quantidadePoltronas;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadePoltronas() {
        return quantidadePoltronas;
    }
}



// SALA,FILME
