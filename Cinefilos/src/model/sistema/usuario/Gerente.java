package model.sistema.usuario;

import controller.GerenciaLanchonete;
import controller.GerenciaSistema;

public class Gerente extends Usuario{

    private final GerenciaSistema gerenciaCinema = new GerenciaSistema();
    private final GerenciaLanchonete gerenciaLanchonete = new GerenciaLanchonete();

    public Gerente(String nome, String senha) {
        super(nome, senha, CategoriaUsuario.GERENTE);
    }

    public GerenciaSistema getGerenciaCinema() {
        return gerenciaCinema;
    }

    public GerenciaLanchonete getGerenciaLanchonete() {
        return gerenciaLanchonete;
    }
}
