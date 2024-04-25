package model.sistema;

import model.sistema.usuario.Usuario;

import java.util.ArrayList;

public class Sistema {

    private static Usuario LOGADO = null;

    private final ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();


    public static Usuario getLOGADO() {
        return LOGADO;
    }

    private static void setLOGADO(Usuario LOGADO) {
        Sistema.LOGADO = LOGADO;
    }

    public void deslogar(){
        //TODO
    }

    public void criarConta(){
        //TODO
    }
}
