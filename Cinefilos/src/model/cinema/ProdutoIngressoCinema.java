package model.cinema;

import model.Produto;
import model.sistema.usuario.Usuario;

public class ProdutoIngressoCinema extends Produto {
    private Filme filme;
    private Sala sala;
    private Usuario usuario;
    private int poltrona;

    public ProdutoIngressoCinema(String nome, double preco, int quantidade, Filme filme, Sala sala, Usuario usuario, int poltrona) {
        super(nome, preco, quantidade);
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
