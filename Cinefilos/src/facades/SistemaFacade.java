package facades;

import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import controller.GerenciaSistema;
import model.Produto;
import model.cinema.ProdutoIngressoCinema;
import model.cinema.Sala;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Gerente;
import model.sistema.usuario.Usuario;

import java.util.List;

public class SistemaFacade {

    GerenciaSistema gerenciaSistema = new GerenciaSistema();
    GerenciaCinema gerenciaCinema = new GerenciaCinema();
    GerenciaLanchonete gerenciaLanchonete = new GerenciaLanchonete();
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
            System.out.println(iae.getMessage());
        }
    }
    public void criarContaGerente(String nome, String senha){
        gerenciaSistema.registraNovoGerente(nome,senha);
    }

    public void fazerLogin(String nome, String senha){
        if (!gerenciaSistema.getSistema().verificaUsuarioExiste(nome)){
            throw new IllegalArgumentException("Usuario não existe.");
        }
        try {
            gerenciaSistema.fazerLogin(nome,senha);
        }catch (IllegalArgumentException iae){
            throw new IllegalArgumentException("Senha incorreta.");
        }
    }

    public void deslogar(){
        gerenciaSistema.deslogar();
    }

    public CategoriaUsuario getTipoUsuarioLogado() {
        return gerenciaSistema.getCategoriaUsuario();
    }


    // Cliente
    public void adicionaProdutoCarrinhoCompras(String codProduto, int quantidade){
        try{
            ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().adicionarProduto(codProduto, quantidade);
        }catch (IllegalArgumentException iae){
            throw iae;
        }
    }

    //fixme ACHO QUE PODEMOS APAGAR ESSE MÉTODO
    public void removeProdutoCarrinhoCompras(Produto produto){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE){
            ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().removeProduto(produto);
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }

    public void limpaCarrinhoCompras(){
        ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().esvaziarCarrinho();
    }

    public void verCarrinho(){
        System.out.println("Carrinho de compras:");
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.CLIENTE){
            for (Produto produto: ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().getCarrinhoDeCompras()){
                System.out.println(produto);
            }
        }else {
            throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
        }
    }

    public void exibeIngressosDisponiveis(){
        for (String ingresso: gerenciaCinema.getIngressosDisponiveis()){
            System.out.println(ingresso);
        }
    }

    public void exibeProdutosLanchoneteDisponiveis(){
        for (Produto produto: GerenciaLanchonete.getProdutosDisponiveis()){
            System.out.println(produto+ " Quantidade:"+produto.getQuantidade());
        }
    }

    public double finalizaCompra(){
        double total = ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().finalizaPedido();
        ((Cliente) Sistema.getLOGADO()).getProgramaFidelidade().adicionaPontos(total);
        ((Cliente) Sistema.getLOGADO()).getProgramaFidelidade().atualizaNivel();
        return total;
    }
    public void cancelarCompra() {
        ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().cancelarCompra();
        gerenciaSistema.deslogar();
    }

    // Gerente
       // Cinema
    public void criaNovaSalaCinema(String nomeSala, String tipoSala){
        gerenciaCinema.adicionaSalaCinema(new Sala(nomeSala,tipoSala));
    }
    public void criaNovoFilme(String nomeFilme, int duracaoFilme){
        gerenciaCinema.adicionaFilmeEmCartaz(nomeFilme,duracaoFilme);
    }
    public void adicionarNovoFilmeCinema(int indexSala, int indexFilme, int horario){

        gerenciaCinema.adicionaFilmeNaSala(gerenciaCinema.getSalaCinema(indexSala),gerenciaCinema.getFilmeNaSala(indexFilme),horario);
    }
    public void removerFilmeCinema(){}
       // Lanchonete
    public void criaNovoProdutoLanchonete(String nome, double preco,int quantidade){
        //nome,preco,quantidade
        gerenciaLanchonete.adicionaProduto(new ProdutoLanchonete(nome,preco,quantidade,("L"+GerenciaSistema.geraCodigo())));
    }
    public void adicionaNovoProdutoLanchonete(ProdutoLanchonete produtoLanchonete){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.GERENTE){
            ((Gerente) Sistema.getLOGADO()).getGerenciaLanchonete().adicionaProduto(produtoLanchonete);
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }
    public ProdutoLanchonete getProdutoLanchonete(String codigo){
        return GerenciaLanchonete.getProdLanchonetePorCodigo(codigo);
    }
    public void editaNomeProdutoLanchonete(String nome,String codigo){
        if (nome.isEmpty() || nome.isBlank()){
            throw new IllegalArgumentException("Insira um nome válido!");
        }
        ProdutoLanchonete produtoEditado = getProdutoLanchonete(codigo);
        produtoEditado.setNome(nome);
    }
    public void editaPrecoProdutoLanchonete(double preco,String codigo){
        if (preco <= 0){
            throw new IllegalArgumentException("O preço precisa ser maior que zero.");
        }
        ProdutoLanchonete produtoEditado = getProdutoLanchonete(codigo);
        produtoEditado.setPreco(preco);
    }
    public void editaQuantidadeProdutoLanchonete(int quantidade,String codigo){
        if (quantidade <= 0){
            throw new IllegalArgumentException("A quantidade precisa ser maior que zero.");
        }
        ProdutoLanchonete produtoEditado = getProdutoLanchonete(codigo);
        produtoEditado.setQuantidade(quantidade);
    }



    public void removerProdutoLanchonete(){

    }
    public void gerarRelatorio(){
        for (String venda: gerenciaSistema.relatorioVendas()){
            System.out.println(venda);
        }
    }

    //util
    //TODO ARRUMAR ESSES MÉTODOS DE ENTRADA




    public boolean isLogado(){
        if (Sistema.getLOGADO()!=null){
            return true;
        }return false;
    }

    public void teste(){
        int total = 0;
        for (List<ProdutoIngressoCinema> lista1: GerenciaCinema.getIngressosDoCinema()){
            for (Produto produto: lista1){
                total++;
            }
        }
        System.out.println(total);
    }



}
