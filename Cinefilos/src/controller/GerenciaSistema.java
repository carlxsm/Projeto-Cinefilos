package controller;

import model.Produto;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Gerente;
import model.sistema.usuario.Usuario;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GerenciaSistema implements Serializable {

    final Sistema sistema = new Sistema();
    final static List<CarrinhoCompras> totalCompras = new ArrayList<>();

    public GerenciaSistema() {
    }

    public static List<CarrinhoCompras> getTotalCompras() {
        return totalCompras;
    }

    public void adicionaCompras(CarrinhoCompras carrinhoCompras) {
        List<Produto> produtos = new ArrayList<>(carrinhoCompras.getCarrinhoDeCompras());
        CarrinhoCompras carrinhoComprasClone = new CarrinhoCompras(carrinhoCompras.getTipoCliente(),
                carrinhoCompras.getData(),
                carrinhoCompras.getGerenciaSistema(), carrinhoCompras.getGerenciaCinema(),
                produtos);
        totalCompras.add(carrinhoComprasClone);
        totalCompras.indexOf(carrinhoComprasClone);
    }

    public List<String> relatorioVendas() {
        List<String> relatorioVendas = new ArrayList<>();
        for (CarrinhoCompras carrinho : totalCompras) {
            System.out.println(carrinho);
            String infoData = "";
            if (carrinho.getData() != null) {
                infoData = carrinho.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            String infoUsuario = carrinho.getTipoCliente().toString();
            String infoValorCompra = String.valueOf(carrinho.valorComDesconto());
            String infoCompra = infoData + " | " + infoUsuario + " | R$" + infoValorCompra;
            relatorioVendas.add(infoCompra);
        }
        return relatorioVendas;
    }

    public void registrarNovoCliente(String nome, String email, String senha) {
        try {
            validaNomeSenha(nome);
            validaEmail(email);
            validaNomeSenha(senha);
        } catch (IllegalArgumentException iae) {
            throw iae;
        }
        if (!Sistema.verificaNomeUsuarioExiste(nome)) {
            if (!Sistema.verificaEmailUsuarioExiste(email)) {
                sistema.adicionaNovoUsuario(new Cliente(nome, email, senha));
            } else {
                throw new IllegalArgumentException("Email ja esta em uso");
            }
        } else {
            throw new IllegalArgumentException("Usuário já existe");
        }
    }

    public void registraNovoGerente(String nome, String email, String senha) {
        try {
            validaNomeSenha(nome);
            validaEmail(email);
            validaNomeSenha(senha);
        } catch (IllegalArgumentException iae) {
            throw iae;
        }
        if (!Sistema.verificaNomeUsuarioExiste(nome)) {
            sistema.adicionaNovoUsuario(new Gerente(nome, email, senha));
        } else {
            throw new IllegalArgumentException("Usuário já existe");
        }
    }

    public void fazerLogin(String email, String senha) {
        for (Usuario user : Sistema.getUsuariosCadastrados()) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                sistema.setLOGADO(user);
                return;
            }
        }
        throw new IllegalArgumentException("Email ou senha incorretos.");
    }

    public void validaNomeSenha(String nome) {
        if (nome.length() < 4 || nome.isBlank()) {
            throw new IllegalArgumentException("Entrada Inválida");
        }
    }

    public void validaEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(regex)) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
    }

    public CategoriaUsuario getCategoriaUsuario() {
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE) {
            return CategoriaUsuario.CLIENTE;
        } else if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.GERENTE) {
            return CategoriaUsuario.GERENTE;
        } else {
            return null;
        }
    }

    public void deslogar() {
        sistema.setLOGADO(null);
    }

    public void desligaSistema() {
        sistema.setStatusSistema(false);
    }

    public Sistema getSistema() {
        return sistema;
    }

    public static String geraCodigo() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000, 9999));
    }
}
