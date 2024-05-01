package facades;

import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import controller.GerenciaSistema;
import model.Produto;
import model.cinema.Filme;
import model.cinema.Sala;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Gerente;
import model.sistema.usuario.Usuario;

import java.io.IOException;
import java.util.List;

public class SistemaFacade {

    public SistemaFacade() {
    }

    GerenciaSistema gerenciaSistema = new GerenciaSistema();
    GerenciaCinema gerenciaCinema = new GerenciaCinema();
    GerenciaLanchonete gerenciaLanchonete = new GerenciaLanchonete();


    //Sistema
    public void abreCinema() throws IOException, ClassNotFoundException {
        Sistema.importUsuarios();
        Sistema.importTotalCompras();
        Sistema.importProdutosLanchonetes();
        Sistema.importSalasCinema();
        Sistema.importFilmesEmCartaz();
        Sistema.importIngressoDisponiveis();
    }
    public void fechaCinema() throws IOException {
        Sistema.salvaUsuarios();
        Sistema.salvaTotalCompras();
        Sistema.salvaProdutosLanchonetes();
        Sistema.salvaSalasCinema();
        Sistema.salvaFilmesEmCartaz();
        Sistema.salvaIngressosDisponiveis();
        gerenciaSistema.deslogar();
        gerenciaSistema.desligaSistema();
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
        if (!Sistema.verificaUsuarioExiste(nome)){
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

    public List<Filme> filmesEmCartaz(){
        return GerenciaCinema.getFilmesEmCartaz();
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
    public List<Sala> getSalasDisponiveis(){
        return GerenciaCinema.getSalasCinema();
    }


    public void removerFilmeCinema(int indexFilme){
        GerenciaCinema.removeFilmeCinema(gerenciaCinema.getFilmeNaSala(indexFilme));
    }
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
        if (nome.isBlank() || nome.length() < 4){
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


    public void gerarRelatorio(){
        for (String venda: gerenciaSistema.relatorioVendas()){
            System.out.println(venda);
        }
    }

    //util

    public boolean isLogado(){
        if (Sistema.getLOGADO()!=null){
            return true;
        }return false;
    }

    public void teste(){
        int total = 0;
        for (Usuario user: Sistema.getUsuariosCadastrados()){
            System.out.println(user.getNome());
        }
        System.out.println(total);
    }



}
