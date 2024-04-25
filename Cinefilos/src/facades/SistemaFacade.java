package facades;

import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import controller.GerenciaSistema;
import model.Produto;
import model.cinema.Filme;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Gerente;
import model.sistema.usuario.Usuario;

public class SistemaFacade {

    GerenciaSistema gerenciaSistema = new GerenciaSistema();
    public static Usuario usuarioLogado = Sistema.getLOGADO(); // gerente ou usuario
    public static Usuario usuarioLogadoTeste = Sistema.getLOGADO(); // gerente ou usuario


    //Sistema
    public void abreCinema(){
        //TODO checar se tem arquivo pra carregar as listas
    }
    public void fechaCinema(){
        // TODO salva as listas no arquivo
    }
    public void criarConta(){}
    public void logar(){}
    public void deslogar(){}
    public void finalizaCompra(){}

    // Cliente
    public void adicionaProdutoCarrinhoCompras(Produto produto){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE){
            ((Cliente) usuarioLogado).getCarrinhoCompras().adicionarProduto(produto);
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }

    public void removeProdutoCarrinhoCompras(Produto produto){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE){
            ((Cliente) usuarioLogado).getCarrinhoCompras().removeProduto(produto);
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }

    public void verCarrinho(){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE){
            for (Produto produto: ((Cliente) usuarioLogado).getCarrinhoCompras().getCarrinhoDeCompras()){
                System.out.println(produto.getNome()); // TODO fazer um toString | Definir toString do Ingresso e do ProdutoLanchonete | Nome,Preco e Index
            }
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }

    public void verFilmesDisponiveis(){
        for (Filme filme: GerenciaCinema.getFilmesEmCartaz()){
            System.out.println(filme.getNomeFilme()); // TODO fazer um toString | Definir toString do filme | Nome, duração
        }
    }

    public void verProdutoLanchonete(){
        for (Produto produto: GerenciaLanchonete.getProdutosDisponiveis()){
            System.out.println(produto.getNome()); // TODO fazer um toString | Definir toString do produto lanchonete | Nome, preco, quantidade disponivel
        }
    }

    // Gerente
    public void adicionaNovoProdutoLanchonete(ProdutoLanchonete produtoLanchonete){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.GERENTE){
            ((Gerente) usuarioLogado).getGerenciaLanchonete().adicionaProduto(produtoLanchonete);
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }

    public void editarProduto(){

    }

    public void removerProduto(){

    }

// CARLOS COM SISTEMA
// Quem ficar com Cliente vai definir como vai ser o relacionamento dos controllers com a view : Marcos
// Quem ficar com Gerente vai definir como vai ser o relacionamento dos controllers com a view : Mateus

// Definir como será o Ingresso, horario filme, como isso vai ser organizado na lista e no ingresso.



}
