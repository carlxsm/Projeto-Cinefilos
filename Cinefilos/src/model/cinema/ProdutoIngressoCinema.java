package model.cinema;

import model.ProdutoInterface;
import model.sistema.usuario.Usuario;

public class ProdutoIngressoCinema implements ProdutoInterface {
    private Filme filme;
    private Sala sala;
    private Usuario usuario;
    private int poltrona;

    public ProdutoIngressoCinema(Filme filme, Sala sala, Usuario usuario, int poltrona) {
        this.filme = filme;
        this.sala = sala;
        this.usuario = usuario;
        this.poltrona = poltrona;
    }

    public double getPreco() {
        // TODO implementar metodo
        return 0;

    }

    @Override
    public String getDescricao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescricao'");
    }
}
