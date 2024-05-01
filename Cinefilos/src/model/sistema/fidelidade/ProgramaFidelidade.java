package model.sistema.fidelidade;

import java.io.Serializable;

public class ProgramaFidelidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private NivelFidelidade fidelidade = NivelFidelidade.SILVER;
    private int pontos;

    public ProgramaFidelidade(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public NivelFidelidade getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(NivelFidelidade fidelidade) {
        this.fidelidade = fidelidade;
    }

    public void atualizaNivel(){
        if (pontos >= 80){
            setFidelidade(NivelFidelidade.BLACK);
        } else if (pontos >= 50) {
            setFidelidade(NivelFidelidade.PLATINUM);
        }else if (pontos >= 20) {
            setFidelidade(NivelFidelidade.GOLD);
        }
    }

    public void adicionaPontos(double valorCompra){
        if (valorCompra >= fidelidade.getPontosPorValorGasto()){
            setPontos((int)(valorCompra / fidelidade.getPontosPorValorGasto()));
        }
    }
}
