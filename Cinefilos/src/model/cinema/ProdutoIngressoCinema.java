package model.cinema;

import model.Produto;
import model.sistema.Sistema;
import model.sistema.usuario.Usuario;

public class ProdutoIngressoCinema extends Produto {
    private Filme filme;
    private Sala sala;
    private int assento;
    private int horario;
    private int codigo;

    public ProdutoIngressoCinema(Filme filme, Sala sala, int assento, int horario, int codigo) {
        super("Ingresso Cinema", sala.getTipoSala().getPreco(), sala.getTipoSala().getQuantidadePoltronas());
        this.filme = filme;
        this.sala = sala;
        this.assento = assento;
        this.horario = horario;
        this.codigo = codigo;
    }

    public Filme getFilme() {
        return filme;
    }

    public Sala getSala() {
        return sala;
    }

    public int getHorario() {
        return horario;
    }

    public int getAssento() {
        return assento;
    }

    public double getPreco(){
        return sala.getTipoSala().getPreco();
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return filme + "\n" + sala.getTipoSala()+" | "+ sala.getTipoSala().getPreco() + " | "+ getAssento();
    }
}
