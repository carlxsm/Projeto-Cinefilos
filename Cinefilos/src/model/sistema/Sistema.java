package model.sistema;

import model.sistema.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    public static Usuario LOGADO = null;
    public static boolean statusSistema = true;

    private final static ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();

    public int adicionaNovoUsuario(Usuario usuario){
        usuariosCadastrados.add(usuario);
        return usuariosCadastrados.indexOf(usuario);
    }

    public static Usuario getLOGADO() {
        return LOGADO;
    }

    public void setLOGADO(Usuario LOGADO) {
        Sistema.LOGADO = LOGADO;
    }

    public boolean verificaUsuarioExiste(String nome){
        for (Usuario user: usuariosCadastrados){
            if(user.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }

    public List<Usuario> getUsuariosCadastrados(){
        return usuariosCadastrados;
    }

}
