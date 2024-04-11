package model.sistema;

import model.sistema.usuario.Usuario;

import java.util.ArrayList;

public class Sistema {
    private static int LOGADO = 0;
    private static boolean LOGADO = false;

    private final ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    public Sistema() {}

    public boolean isLogado(){
        if (LOGADO){
            return true;
        }else {
            return false;
        }
    }
    public void desloga(){
        LOGADO = false;
    }
    public void logar(){
        //TODO
    }
    public void deslogar(){
        //TODO
    }
    public void criarConta(){
        //TODO
    }
}
