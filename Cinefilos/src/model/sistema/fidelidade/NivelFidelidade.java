package model.sistema.fidelidade;

import java.io.Serializable;

public enum NivelFidelidade implements Serializable {

    SILVER(1,1,35),GOLD(0.97,120,40),PLATINUM(0.94,160,45),BLACK(0.88,200,50);
    private double valorDesconto;
    private double valorInicialDesconto;
    private double pontosPorValorGasto;
    //TODO Desconto
    NivelFidelidade( double precoDesconto, double desconto,double pontosPorValorGasto) {
        this.valorDesconto = precoDesconto;
        this.valorInicialDesconto = desconto;
        this.pontosPorValorGasto = pontosPorValorGasto;
    }

    public double getPontosPorValorGasto() {
        return pontosPorValorGasto;
    }
    public double getValorDesconto() {
        return valorDesconto;
    }

    public double getValorInicialDesconto() {
        return valorInicialDesconto;
    }

    @Override
    public String toString() {
        return "NivelFidelidade{" +this.name()+'}';
    }
}
