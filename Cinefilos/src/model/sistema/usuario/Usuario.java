package model.sistema.usuario;

import java.util.Objects;

public abstract class Usuario {

    private String nome;
    private String senha;
    private CategoriaUsuario categoriaUsuario;

    public Usuario() {
    }

    public Usuario(String nome, String senha, CategoriaUsuario categoriaUsuario2) {
        this.nome = nome;
        this.senha = senha;
        this.categoriaUsuario = categoriaUsuario2;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(senha, usuario.senha) && categoriaUsuario == usuario.categoriaUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, senha, categoriaUsuario);
    }
}
