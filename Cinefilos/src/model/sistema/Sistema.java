package model.sistema;

import model.sistema.usuario.Usuario;

import java.util.ArrayList;

public class Sistema {

    private static Usuario LOGADO = null;

    private final ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    public Sistema() {}

    public boolean isLogado(){
        if (LOGADO != null){
            return true;
        }else {
            return false;
        }
    }
    public Usuario logar(String login, String senha){
        for (Usuario usuario: usuariosCadastrados){
            if (usuario.getNome().equals(login) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        }
        throw new IllegalArgumentException("Usuario não existe");
    }

    public void deslogar(){
        //TODO
    }

    public void criarConta(){
        //TODO
    }
}
