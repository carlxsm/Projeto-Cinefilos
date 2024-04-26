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


    //Sistema
    public void abreCinema(){
        //TODO checar se tem arquivo pra carregar as listas
        //TODO Carrega lista Filmes cinema
        // TODO Carrega lista Salas cinema
        // TODO Carrega lista de Produtos lanchonete
        // TODO Carrega lista de Usuarios cadastrados
        // TODO Carrega lista de vendas realizadas
    }
    public void fechaCinema(){
        // TODO salva as listas no arquivo
        // TODO salva lista Filmes cinema
        // TODO salva lista Salas cinema
        // TODO salva lista de Produtos lanchonete
        // TODO salva lista de Usuarios cadastrados
        // TODO salva lista de vendas realizadas
    }
    public void criarContaCliente(String nome, String senha){
        try {
            gerenciaSistema.registrarNovoCliente(nome,senha);
        }catch (IllegalArgumentException iae){
            System.out.println();
        }
    }
    public void criarContaGerente(){
        gerenciaSistema.registraNovoGerente(null,null);
    }

    public void fazerLogin(String nome, String senha){
        if (!gerenciaSistema.getSistema().verificaUsuarioExiste(nome)){
            System.out.println("O usuário não existe.");
        }
        try {
            gerenciaSistema.fazerLogin(nome,senha);
        }catch (IllegalArgumentException iae){
            System.out.println("Senha incorreta");
        }
    }

    public void deslogar(){
        gerenciaSistema.deslogar();
    }

    public CategoriaUsuario getTipoUsuarioLogado() {
        return gerenciaSistema.getCategoriaUsuario();
    }


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

    public void limpaCarrinhoCompras(){
        ((Cliente) usuarioLogado).getCarrinhoCompras().esvaziarCarrinho();
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

    public double finalizaCompra(){
        double total = ((Cliente)usuarioLogado).getCarrinhoCompras().finalizaPedido();
        ((Cliente)usuarioLogado).getProgramaFidelidade().adicionaPontos(total);
        ((Cliente)usuarioLogado).getProgramaFidelidade().atualizaNivel();
        return total;
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


    //util
    public int entradaInteiro(){
        if (Sistema.scan.hasNextInt()){
            return Sistema.scan.nextInt();
        }
        System.out.println("Digite um número inteiro");
        return entradaInteiro();
    }

    public String entradaString(){
        String entrada = Sistema.scan.next();
        try {
            gerenciaSistema.validaNomeSenha(entrada);
            return entrada;
        }catch (IllegalArgumentException iae){
            System.out.println("Entrada inválida, tente novamente");
        }
        return entradaString();
    }

    public boolean isLogado(){
        if (Sistema.getLOGADO()!=null){
            return true;
        }return false;
    }
}
