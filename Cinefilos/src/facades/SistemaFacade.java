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
        gerenciaSistema.registrarNovoCliente(nome,senha);

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

    public List<Produto> verCarrinho(){
        return ((Cliente) Sistema.getLOGADO()).getCarrinhoCompras().getCarrinhoDeCompras();
    }

    public List<String> exibeIngressosDisponiveis(){
        return gerenciaCinema.getIngressosDisponiveis();
    }

    public List<ProdutoLanchonete> exibeProdutosLanchoneteDisponiveis(){
        return GerenciaLanchonete.getProdutosDisponiveis();
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


    public List<String> gerarRelatorio(){
        return gerenciaSistema.relatorioVendas();
    }

    //util

    public boolean isLogado(){
        if (Sistema.getLOGADO()!=null){
            return true;
        }return false;
    }

}
