package model.sistema.fidelidade;

public enum NivelFidelidade {
    SILVER(0), GOLD(1), PLATINUM(2), BLACK(3);

    private int value;

    // TODO Desconto
    NivelFidelidade(int value) {
        this.value = value;
    }
}
