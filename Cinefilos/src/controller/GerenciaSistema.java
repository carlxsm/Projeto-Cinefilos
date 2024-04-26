package controller;

import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Gerente;
import model.sistema.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciaSistema {

    final Sistema sistema = new Sistema();
    final List<CarrinhoCompras> totalCompras = new ArrayList<>();

    public List<CarrinhoCompras> getTotalCompras() {
        return totalCompras;
    }

    public int adicionaCompras(CarrinhoCompras carrinhoCompras) {
        totalCompras.add(carrinhoCompras);
        return totalCompras.indexOf(carrinhoCompras);
    }

    public void registrarNovoCliente(String nome, String senha){
        try {
            validaNomeSenha(nome);
            validaNomeSenha(senha);
        }catch (IllegalArgumentException iae){
            throw iae;
        }
        if (!sistema.verificaUsuarioExiste(nome)){
            sistema.adicionaNovoUsuario(new Cliente(nome,senha, CategoriaUsuario.CLIENTE));
        }else{
            throw new IllegalArgumentException("Usuário já existe");
        }
    }

    public void registraNovoGerente(String nome, String senha){
        try {
            validaNomeSenha(nome);
            validaNomeSenha(senha);
        }catch (IllegalArgumentException iae){
            throw iae;
        }
        if (!sistema.verificaUsuarioExiste(nome)){
            sistema.adicionaNovoUsuario(new Gerente(nome,senha, CategoriaUsuario.GERENTE));
        }else{
            throw new IllegalArgumentException("Usuário já existe");
        };
    }

    public void fazerLogin(String nome, String senha){
        for (Usuario user: sistema.getUsuariosCadastrados()){
            if (user.getNome().equals(nome) && user.getSenha().equals(senha)){
                sistema.setLOGADO(user);
            }
        }
        if (Sistema.getLOGADO() == null){
            throw new IllegalArgumentException("Senha incorreta");
        }
    }

    public void validaNomeSenha(String nome){
        if (nome.isEmpty() || nome.length() < 4 || nome.isBlank()){
            throw new IllegalArgumentException("Entrada Inválida");
        }
    }

    public CategoriaUsuario getCategoriaUsuario(){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE){
            return CategoriaUsuario.CLIENTE;
        }else if (Sistema.getLOGADO().getCategoriaUsuario()  == CategoriaUsuario.GERENTE){
            return CategoriaUsuario.GERENTE;
        }else{
            return null;
        }
    }

    public void deslogar(){
        sistema.setLOGADO(null);
    }

    public Sistema getSistema(){
        return sistema;
    }
}
