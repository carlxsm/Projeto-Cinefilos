package model.sistema.usuario;

public abstract class Usuario {
    private String nome;
    private String senha;
    private CategoriaUsuario categoriaUsuario;
    public Usuario(String nome, String senha,int categoriaUsuario) {
        this.nome = nome;
        this.senha = senha;
        this.categoriaUsuario = CategoriaUsuario.valueOf(String.valueOf(categoriaUsuario));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
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
