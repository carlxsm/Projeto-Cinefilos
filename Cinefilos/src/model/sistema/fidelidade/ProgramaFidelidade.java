package model.sistema.fidelidade;

public class ProgramaFidelidade {
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
        //TODO Atualiza Nível
    }
}
