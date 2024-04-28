package model.cinema;

import model.Produto;
import model.sistema.Sistema;
import model.sistema.usuario.Usuario;

import java.util.Objects;

public class ProdutoIngressoCinema extends Produto {
    private Filme filme;
    private Sala sala;
    private int assento;
    private int horario;

    public ProdutoIngressoCinema(Filme filme, Sala sala, int assento, int horario, String codigo) {
        super("Ingresso Cinema", sala.getTipoSala().getPreco(), sala.getTipoSala().getQuantidadePoltronas(),codigo);
        this.filme = filme;
        this.sala = sala;
        this.assento = assento;
        this.horario = horario;
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


    @Override
    public String toString() {
        return filme + "\n Sala:" + sala.getTipoSala()+" | R$ "+ sala.getTipoSala().getPreco() + " | Assento"+ getAssento();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoIngressoCinema that = (ProdutoIngressoCinema) o;
        return assento == that.assento && horario == that.horario && Objects.equals(filme, that.filme) && Objects.equals(sala, that.sala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filme, sala, assento, horario);
    }
}
