package model.sistema.usuario;

import model.sistema.fidelidade.ProgramaFidelidade;

public abstract class Usuario {
    private String nome;
    private String senha;
    private CategoriaUsuario categoriaUsuario;


    public Usuario(String nome, String senha,CategoriaUsuario categoriaUsuario) {
        this.nome = nome;
        this.senha = senha;
        this.categoriaUsuario = categoriaUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public CategoriaUsuario getCategoriaUsuario() {
        return categoriaUsuario;
    }

    public void setCategoriaUsuario(CategoriaUsuario categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }
}
