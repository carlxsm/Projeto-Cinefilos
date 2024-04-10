package model.sistema.usuario;

import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.fidelidade.ProgramaFidelidade;

public class Cliente extends Usuario{
    public Cliente(String nome, String senha, int categoriaUsuario) {
        super(nome, senha, categoriaUsuario);
    }

}
