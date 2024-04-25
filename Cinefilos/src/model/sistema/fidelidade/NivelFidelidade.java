package model.sistema.fidelidade;

public enum NivelFidelidade {
    SILVER(1,1,1),GOLD(1,0.97,120),PLATINUM(2,0.94,160),BLACK(3,0.88,200);
    private int valor;
    private double precoDesconto;
    private double desconto;

    //TODO Desconto
    NivelFidelidade(int valor, double precoDesconto, double desconto) {

        this.valor = valor;
        this.precoDesconto = precoDesconto;
        this.desconto = desconto;
    }

    public int getValor() {
        return valor;
    }

    public double getPrecoDesconto() {
        return precoDesconto;
    }

    public double getDesconto() {
        return desconto;
    }
}
