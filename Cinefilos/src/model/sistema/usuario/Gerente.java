package model.sistema.usuario;

import controller.GerenciaLanchonete;
import controller.GerenciaSistema;

import java.io.Serializable;
import java.util.Objects;

public class Gerente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public Gerente() {
        super("", "", "", CategoriaUsuario.GERENTE); // Chamada ao construtor da superclasse Usuario
    }

    private final GerenciaSistema gerenciaCinema = new GerenciaSistema();
    private final GerenciaLanchonete gerenciaLanchonete = new GerenciaLanchonete();

    public Gerente(String nome, String email, String senha) {
        super(nome, email, senha, CategoriaUsuario.GERENTE);
    }

    public GerenciaSistema getGerenciaCinema() {
        return gerenciaCinema;
    }

    public GerenciaLanchonete getGerenciaLanchonete() {
        return gerenciaLanchonete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Gerente gerente = (Gerente) o;
        return Objects.equals(gerenciaCinema, gerente.gerenciaCinema)
                && Objects.equals(gerenciaLanchonete, gerente.gerenciaLanchonete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gerenciaCinema, gerenciaLanchonete);
    }
}
