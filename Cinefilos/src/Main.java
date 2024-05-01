import view.Menu;
import facades.SistemaFacade;
import model.cinema.Filme;
import model.cinema.Sala;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        // TODO remover os prints do facade

        SistemaFacade sistemaFacade = new SistemaFacade();
        try{
            sistemaFacade.abreCinema();
        }catch (FileNotFoundException e){

        }

        sistemaFacade.teste();

        sistemaFacade.criarContaGerente("admin","admin");
        sistemaFacade.criarContaCliente("user","user");

        sistemaFacade.criaNovaSalaCinema("Sala Normal 01", "BASICA");
        sistemaFacade.criaNovaSalaCinema("Sala 3D 01", "VIP");
        sistemaFacade.criaNovaSalaCinema("Sala Vip 01", "SALA3D");


        sistemaFacade.criaNovoFilme("As Crônicas de Nárnia",180);
        sistemaFacade.adicionarNovoFilmeCinema(0,0,1430);
        sistemaFacade.adicionarNovoFilmeCinema(2,0,1430);

        sistemaFacade.criaNovoFilme("A Fuga das Galinhas",210);
        sistemaFacade.adicionarNovoFilmeCinema(1,1,1430);


        sistemaFacade.criaNovoFilme("Shrek 2",200);
        sistemaFacade.adicionarNovoFilmeCinema(2,2,1430);

        sistemaFacade.criaNovoFilme("Shrek 1",200);
        sistemaFacade.adicionarNovoFilmeCinema(2,3,1700);


        sistemaFacade.criaNovoProdutoLanchonete("Pipoca Salgada", 18,100);
        sistemaFacade.criaNovoProdutoLanchonete("Pipoca Doce", 20,100);
        sistemaFacade.criaNovoProdutoLanchonete("Coquinha gelada", 8,80);


        do {
            Menu.menuPrincipal();
            switch (entradaInteiro()){
                case 1: // Login
                    try {
                        fazerLogin(sistemaFacade);
                    }catch (IllegalArgumentException iae){
                        System.err.println("Nome ou Senha Incorretos.\n");
                        break;
                    }
                    // Menu Cliente
                    if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.CLIENTE){
                        while (Sistema.getLOGADO() != null){
                            Menu.imprimeDadosCliente((Cliente) Sistema.getLOGADO());
                            Menu.menuSegundario();
                            switch (entradaInteiro()){
                                case 1: // exibição cinema
                                    sistemaFacade.exibeIngressosDisponiveis();
                                    Menu.menuComprarVoltar();
                                    switch (entradaInteiro()){
                                        case 1: // comprar ingresso
                                            comprarIngrsso(sistemaFacade);
                                            break;
                                        case 2: // voltar
                                            break;
                                        default:
                                            System.err.println("Opção Inválida");
                                    }
                                    break;
                                case 2: // exibição lanchonete
                                    sistemaFacade.exibeProdutosLanchoneteDisponiveis();
                                    Menu.menuComprarVoltar();
                                    switch (entradaInteiro()){
                                        case 1: // comprar
                                            comprarLanchonete(sistemaFacade);
                                            break;
                                        case 2: // voltar
                                            break;
                                        default:
                                            System.err.println("Opção inválida");
                                    }
                                    break;
                                case 3: // fechar pedido
                                    fecharPedido(sistemaFacade);
                                    break;
                                case 4: // cancela compra e sair do sistema
                                    sistemaFacade.cancelarCompra();
                                    break;
                                default:
                                    System.err.println("Opção inválida!");
                            }
                        }
                    }

                    // Menu gerente
                    else if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.GERENTE) {
                        while (Sistema.getLOGADO() != null){
                            Menu.imprimeMenuGerente();
                            switch (entradaInteiro()){
                                case 1: // Atualizar exibicoes
                                    Menu.imprimeMenuEditarCinema();
                                    switch (entradaInteiro()){
                                        case 1: // Adiciona Filme e Remove filme
                                            sistemaFacade.exibeIngressosDisponiveis();
                                            Menu.imprimeMenuAdicionarRemoverFilme();
                                            switch (entradaInteiro()){
                                                case 1: // Criar filme
                                                    try {
                                                        criarFilme(sistemaFacade);
                                                    }catch (IllegalArgumentException iae){
                                                        System.err.println(iae.getMessage());
                                                    }
                                                    break;
                                                case 2: // Remover filme
                                                    exibeListaDeFilmes(sistemaFacade);
                                                    try {
                                                        System.out.println("Informe o index do filme que deseja remover:");
                                                        sistemaFacade.removerFilmeCinema(entradaInteiro());
                                                    }catch (IllegalArgumentException iae){
                                                        System.out.println(iae.getMessage());
                                                    }
                                                    break;
                                                case 3 : // Cria ingresso
                                                    exibeListaDeFilmes(sistemaFacade);
                                                    System.out.println("Informe o index do filme :");
                                                    int indexFilme = entradaInteiro();

                                                    exibeListaDeSalas(sistemaFacade);
                                                    System.out.println("Informe o index da sala :");
                                                    int indexSala = entradaInteiro();

                                                    System.out.println("Informe o horário:");
                                                    int horario = entradaInteiro();
                                                    try {
                                                        sistemaFacade.adicionarNovoFilmeCinema(indexSala,indexFilme,horario);
                                                    }catch (IllegalArgumentException | IndexOutOfBoundsException iae){
                                                        System.err.println(iae.getMessage());
                                                    }
                                                    break;
                                                case 4 : // voltar
                                                    break;
                                            }
                                            break;
                                        case 2: // Sair
                                            break;
                                        default:
                                            System.err.println("Opção inválida!");
                                    }
                                    break;
                                case 2: // Atualizar lanchonete
                                    sistemaFacade.exibeProdutosLanchoneteDisponiveis();
                                    Menu.imprimeMenuAtualizalanchonete();
                                    switch (entradaInteiro()){
                                        case 1: // adiciona
                                            adicionarProdutoLanchonete(sistemaFacade);
                                            break;
                                        case 2: // edita
                                            System.out.println("Codigo do produto:");
                                            String codigoProdutoEditado = entradaString();
                                            System.out.println(sistemaFacade.getProdutoLanchonete(codigoProdutoEditado));
                                            Menu.imprimeMenuEditarLanchonete();
                                                switch (entradaInteiro()){
                                                    case 1: // editar nome
                                                        editarNomeProdutoLanchonete(sistemaFacade, codigoProdutoEditado);
                                                        break;
                                                    case 2: // Edita preco
                                                        editarPrecoProdutoLanchonete(sistemaFacade, codigoProdutoEditado);
                                                        break;
                                                    case 3: // edita quantidade
                                                        editarQuantidadeDeProdutoLanchonete(sistemaFacade, codigoProdutoEditado);

                                                        break;
                                                    case 4: // sair
                                                        break;
                                                    default:
                                                        System.err.println("Opção Inválida!");
                                                }
                                            break;
                                        case 3: // remove produto
                                            break;
                                        case 4: // voltar
                                            break;
                                        default:
                                            System.err.println("Opção inválida");
                                    }
                                    break;
                                case 3: // gerar relatorio de compras
                                    sistemaFacade.gerarRelatorio();
                                    break;
                                case 4: // sair do sistema
                                    sistemaFacade.deslogar();
                                    break;
                                default:
                                    System.err.println("Opção inválida");
                                    break;
                            }
                        }
                    }
                    break;
                case 2: // Criar conta
                    criarConta(sistemaFacade);
                    break;

                case 3: // Sair
                    System.out.println("Fechado!");
                    sistemaFacade.fechaCinema(); //TODO Arquivos
                    break;

                default:
                    System.err.println("Opção inválida!");
            }

        }while (Sistema.statusSistema);
    }

    private static void criarFilme(SistemaFacade sistemaFacade) {
        String nomeFilme = entradaString();
        int duracaoFilme = entradaInteiro();
        sistemaFacade.criaNovoFilme(nomeFilme,duracaoFilme);
        System.out.println("Filme criada com sucesso!");
    }

    private static void exibeListaDeSalas(SistemaFacade sistemaFacade) {
        int k = 0;
        for (Sala sala: sistemaFacade.getSalasDisponiveis()){
            System.out.println(sala.getNomeSala() +" | "+ sala.getTipoSala()+" | - Index: "+ k );
            k++;
        }
    }

    private static void exibeListaDeFilmes(SistemaFacade sistemaFacade) {
        int i = 0;
        for (Filme filme: sistemaFacade.filmesEmCartaz()){
            System.out.println(filme.getNomeFilme() +" | "+ filme.getDuracao()+" | - Index: "+i );
            i++;
        }
    }

    private static void editarPrecoProdutoLanchonete(SistemaFacade sistemaFacade, String codigoProdutoEditado) {
        System.out.println("Digite o novo nome do produto");
        double precoProdutoEditado = Double.parseDouble(entradaString());
        try{
            sistemaFacade.editaPrecoProdutoLanchonete(precoProdutoEditado, codigoProdutoEditado);
            System.out.println("Produto editado!");
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }

    private static void editarNomeProdutoLanchonete(SistemaFacade sistemaFacade, String codigoProdutoEditado) {
        System.out.println("Digite o novo nome do produto");
        String nomeProdutoEditadoNome = entradaString();
        try {
            sistemaFacade.editaNomeProdutoLanchonete(nomeProdutoEditadoNome, codigoProdutoEditado);
            System.out.println("Produto editado!");
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }

    private static void adicionarProdutoLanchonete(SistemaFacade sistemaFacade) {
        System.out.println("Nome do novo produto:");
        String nomeNovoProduto = entradaString();
        System.out.println("Preço do novo produto:");
        String precoNovoProduto = entradaString();
        System.out.println("Quantidade do novo produto:");
        int quantidadeNovoProduto = entradaInteiro();
        try{
            sistemaFacade.criaNovoProdutoLanchonete(nomeNovoProduto, Double.parseDouble(precoNovoProduto),quantidadeNovoProduto);
            System.out.println("Produtos adicionados!");
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }

    private static void editarQuantidadeDeProdutoLanchonete(SistemaFacade sistemaFacade, String codigoProdutoEditado) {
        System.out.println("Digite a quantidade do produto");
        int quantidadeProdutoEditado = entradaInteiro();
        try {
            sistemaFacade.editaQuantidadeProdutoLanchonete(quantidadeProdutoEditado, codigoProdutoEditado);
            System.out.println("Produto editado!");
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }

    private static void criarConta(SistemaFacade sistemaFacade) {
        System.out.println("Nome do usuario: ");
        String nomeCriarUsuario = entradaString();
        System.out.println("Senha do usuario: ");
        String senhaCriarUsuario = entradaString();
        sistemaFacade.criarContaCliente(nomeCriarUsuario,senhaCriarUsuario);
        if (sistemaFacade.isLogado()){
            System.out.println("Usuário criado com sucesso!");
        }
    }

    public static void fecharPedido(SistemaFacade sistemaFacade) {
        try{
            System.out.println("Total: R$"+ sistemaFacade.finalizaCompra());
            System.out.println("Compra concluida");
        }catch (NoSuchElementException iae){
            System.out.println(iae.getMessage());
        }
    }

    public static void comprarLanchonete(SistemaFacade sistemaFacade) {
        System.out.println("Insira o código do produto que deseja comprar");
        String escolhaDoProdutoLanchonete = entradaString();
        System.out.println("Insira a quantidade");
        int quantidadeProdutoLanchonete = entradaInteiro();
        try {
            sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoProdutoLanchonete,quantidadeProdutoLanchonete);
            System.out.println("Produtos adicionados!");
            sistemaFacade.verCarrinho();
        }catch (IllegalArgumentException | IndexOutOfBoundsException iae){
            System.err.println(iae.getMessage());
        }
    }

    public static void comprarIngrsso(SistemaFacade sistemaFacade) {
        System.out.println("Insira o código do ingresso que deseja comprar");
        String escolhaDoIngresso = entradaString();
        System.out.println("Insira a quantidade");
        int escolhaQuantidadeIngressos = entradaInteiro();
        try {
            sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoIngresso, escolhaQuantidadeIngressos);
            System.out.println("Ingressos adicionados!");
            sistemaFacade.verCarrinho();
        }catch (IllegalArgumentException | IndexOutOfBoundsException iae){
            System.err.println(iae.getMessage());
        }
    }

    public static void fazerLogin(SistemaFacade sistemaFacade) {
        System.out.println("Nome do usuario: ");
        String nomeLogin = entradaString();
        System.out.println("Senha do usuario: ");
        String senhaLogin = entradaString();
        sistemaFacade.fazerLogin(nomeLogin,senhaLogin);
    }

    public static int  entradaInteiro(){
        if(scan.hasNextInt()){
            return scan.nextInt();
        }scan.nextLine();
        System.out.println("Entrada invalida!");
        return entradaInteiro();
    }

    public static String entradaString(){
        String entrada = scan.next();
        try {
            scan.nextLine();
            return entrada;
        }catch (IllegalArgumentException e){
            System.out.println("Entrada inválida, tente novamente");
        }
        return entradaString();
    }
}
