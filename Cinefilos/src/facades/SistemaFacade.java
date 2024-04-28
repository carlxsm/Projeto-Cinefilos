package facades;

import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import controller.GerenciaSistema;
import model.Produto;
import model.cinema.Filme;
import model.cinema.Sala;
import model.cinema.TipoSala;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Gerente;
import model.sistema.usuario.Usuario;

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
    public void adicionaProdutoLanchoneteCarrinhoCompras(int produto){
        try{
            Produto produtoEscolhido = GerenciaLanchonete.getProdutosDisponiveis().get(produto);
            ((Cliente) usuarioLogado).getCarrinhoCompras().adicionarProduto(produtoEscolhido);
        }catch (IndexOutOfBoundsException iobe){
            System.out.println(iobe.getMessage());
        }
    }

    public void adicionaIngressoCinemaCarrinhoCompras(int produto){
        try{
            //Produto produtoEscolhido = GerenciaCinema.getProdutosDisponiveis().get(produto);
            //((Cliente) usuarioLogado).getCarrinhoCompras().adicionarProduto(produtoEscolhido);
        }catch (IndexOutOfBoundsException iobe){
            System.out.println(iobe.getMessage());
        }
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
            System.out.println(filme);
        }
    }
    public void exibeIngressosDisponiveis(){
        for (String ingresso: gerenciaCinema.getIngressosDisponiveis()){
            System.out.println(ingresso);
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
    public void adicionaNovoProdutoLanchonete(ProdutoLanchonete produtoLanchonete){
        if (Sistema.getLOGADO().getCategoriaUsuario() == CategoriaUsuario.GERENTE){
            ((Gerente) usuarioLogado).getGerenciaLanchonete().adicionaProduto(produtoLanchonete);
        }
        throw new IllegalArgumentException("Você não tem permissão para fazer isso!");
    }

    public void editarProdutoLanchonete(){

    }

    public void removerProdutoLanchonete(){

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
