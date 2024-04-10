package model.cinema;

public enum TipoSala {
    BASICA(0), SALA3D(1), VIP(2);

    private int value;

    TipoSala(int value) {
        this.value = value;
    }

}
