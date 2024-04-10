package model.cinema;

import model.Produto;
import model.sistema.usuario.Usuario;

public class ProdutoIngressoCinema implements Produto {
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
    public double getPreco(){
        //TODO implementar metodo
        return 0;

    }
}
