import facades.SistemaFacade;
import model.cinema.Filme;
import model.cinema.Sala;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import view.TelaCliente;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainCarlos {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        //TODO resolver login errado, usuario nao existe;
        // TODO resolver criação de usuarios com nome existente;
        // TODO remover os prints do facade
        // TODO tratar error na main
        // TODO criar classe menu

        SistemaFacade sistemaFacade = new SistemaFacade();
        sistemaFacade.abreCinema();

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

        sistemaFacade.teste();

        do {
            TelaCliente.imprimeMenuInicial();
            switch (entradaInteiro()){
                case 1: // Login
                    // TODO acho que da pra colocar isso em algum controller e chamar 1 vez só pelo facade
                    try {
                        System.out.println("Nome do usuario: ");
                        String nomeLogin = entradaString();
                        System.out.println("Senha do usuario: ");
                        String senhaLogin = entradaString();
                        sistemaFacade.fazerLogin(nomeLogin,senhaLogin);
                    }catch (IllegalArgumentException iae){
                        System.err.println("Nome ou Senha Incorretos.\n");
                        break;
                    }
                    // Menu Cliente
                    if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.CLIENTE){
                        while (Sistema.getLOGADO() != null){
                            TelaCliente.imprimeDadosCliente((Cliente) Sistema.getLOGADO());
                            System.out.println("1 - Filmes | 2 - Lanchonete | 3 - Fechar pedido | 4 - Deslogar"); // TODO fazer com que só exiba a op. 3 se o carrinho !empty()
                            switch (entradaInteiro()){
                                case 1: // exibição
                                    sistemaFacade.exibeIngressosDisponiveis();
                                    System.out.println("1 - Comprar | 0 - Voltar");
                                    switch (entradaInteiro()){
                                        case 1: // comprar
                                            System.out.println("Insira o código do ingresso que deseja comprar");
                                            String escolhaDoIngresso = entradaString();
                                            System.out.println("Insira a quantidade");
                                            int escolhaQuantidadeIngressos = entradaInteiro();
                                            try {
                                                sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoIngresso, escolhaQuantidadeIngressos);
                                                System.out.println("Ingressos adicionados!");
                                                sistemaFacade.verCarrinho();
                                            }catch (IllegalArgumentException iae){
                                                System.err.println(iae.getMessage());
                                            }catch (IndexOutOfBoundsException iobe){
                                                System.err.println(iobe.getMessage());
                                            }
                                            break;
                                        case 0: // voltar
                                            break;
                                        default:
                                            System.err.println("Opção Inválida");
                                    }
                                    break;
                                case 2: // exibição lanchonete
                                    sistemaFacade.exibeProdutosLanchoneteDisponiveis();
                                    System.out.println("1 - Comprar | 0 - Voltar");
                                    switch (entradaInteiro()){
                                        case 1: // comprar
                                            System.out.println("Insira o código do produto que deseja comprar");
                                            String escolhaDoProdutoLanchonete = entradaString();
                                            System.out.println("Insira a quantidade");
                                            int quantidadeProdutoLanchonete = entradaInteiro();
                                            try {
                                                sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoProdutoLanchonete,quantidadeProdutoLanchonete);
                                                System.out.println("Produtos adicionados!");
                                                sistemaFacade.verCarrinho();
                                            }catch (IllegalArgumentException iae){
                                                System.err.println(iae.getMessage());
                                            }catch (IndexOutOfBoundsException iobe){
                                                System.err.println(iobe.getMessage());
                                            }
                                            break;
                                        case 0: // voltar
                                            break;
                                        default:
                                            System.err.println("Opção inválida");
                                    }
                                    break;
                                case 3: // fechar pedido
                                    try{
                                        System.out.println("Total: R$"+sistemaFacade.finalizaCompra()); //TODO tentar mandar isso para um método da view
                                        System.out.println("Compra concluida");
                                    }catch (NoSuchElementException iae){
                                        System.out.println(iae.getMessage());
                                    }
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
                            System.out.println("1 - Atualizar exibições | 2 - Atualiza Lanchonete | 3 - Relatório | 4 - Sair");
                            switch (entradaInteiro()){
                                case 1: // Atualizar exibicoes
                                    System.out.println("1 - Editar cinema | 2 - Sair");
                                    switch (entradaInteiro()){
                                        case 1: // Adiciona Filme e Remove filme
                                            sistemaFacade.exibeIngressosDisponiveis();
                                            System.out.println("1 - Cria Filme | 2 - Remover filme | 3 - Cria Ingresso | 4 - Volta");
                                            switch (entradaInteiro()){
                                                case 1: // Criar filme
                                                    try {
                                                        String nomeFilme = entradaString();
                                                        int duracaoFilme = entradaInteiro();
                                                        sistemaFacade.criaNovoFilme(nomeFilme,duracaoFilme);
                                                        System.out.println("Filme criada com sucesso!");
                                                    }catch (IllegalArgumentException iae){
                                                        System.err.println(iae.getMessage());
                                                    }
                                                    break;
                                                case 2: // Remover filme
                                                    // TODO Isso aqui abaixo tem que ser um método que vai ser reaproveitado no case 3.
                                                    int i = 0;
                                                    for (Filme filme: sistemaFacade.filmesEmCartaz()){
                                                        System.out.println(filme.getNomeFilme() +" | "+ filme.getDuracao()+" | - Index: "+i );
                                                        i++;
                                                    }
                                                    try {
                                                        System.out.println("Informe o index do filme que deseja remover:");
                                                        sistemaFacade.removerFilmeCinema(entradaInteiro());
                                                    }catch (IllegalArgumentException iae){
                                                        System.out.println(iae.getMessage());
                                                    }
                                                    break;
                                                case 3 : // Cria ingresso
                                                    int j = 0;
                                                    for (Filme filme: sistemaFacade.filmesEmCartaz()){
                                                        System.out.println(filme.getNomeFilme() +" | "+ filme.getDuracao()+" | - Index: "+j );
                                                        j++;
                                                    }

                                                    System.out.println("Informe o index do filme :");
                                                    int indexFilme = entradaInteiro();

                                                    int k = 0;
                                                    for (Sala sala: sistemaFacade.getSalasDisponiveis()){
                                                        System.out.println(sala.getNomeSala() +" | "+ sala.getTipoSala()+" | - Index: "+ k );
                                                        k++;
                                                    }
                                                    System.out.println("Informe o index da sala :");
                                                    int indexSala = entradaInteiro();

                                                    System.out.println("Informe o horário:");
                                                    int horario = entradaInteiro();
                                                    try {
                                                        sistemaFacade.adicionarNovoFilmeCinema(indexSala,indexFilme,horario);
                                                    }catch (IllegalArgumentException iae){
                                                        System.err.println(iae.getMessage());
                                                    }catch (IndexOutOfBoundsException iobe){
                                                        System.err.println(iobe.getMessage());
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
                                    System.out.println("1 - Adiciona novo produto | 2 - Edita produto | 3 - Remove produto | 4- Sair");
                                    switch (entradaInteiro()){
                                        case 1: // adiciona
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
                                            break;
                                        case 2: // edita
                                            System.out.println("Codigo do produto:");
                                            String codigoProdutoEditado = entradaString();
                                            System.out.println(sistemaFacade.getProdutoLanchonete(codigoProdutoEditado));
                                            System.out.println("1 - Editar nome do produto | 2 - Edita preço do produto | 3 - Editar quantidade do produto | 4- Sair");
                                                switch (entradaInteiro()){
                                                    case 1: // editar nome
                                                        System.out.println("Digite o novo nome do produto");
                                                        String nomeProdutoEditadoNome = entradaString();
                                                        try {
                                                            sistemaFacade.editaNomeProdutoLanchonete(nomeProdutoEditadoNome,codigoProdutoEditado);
                                                            System.out.println("Produto editado!");
                                                        }catch (IllegalArgumentException iae){
                                                            System.out.println(iae.getMessage());
                                                        }
                                                        break;
                                                    case 2: // Edita preco
                                                        System.out.println("Digite o novo nome do produto");
                                                        double precoProdutoEditado = Double.parseDouble(entradaString());
                                                        try{
                                                            sistemaFacade.editaPrecoProdutoLanchonete(precoProdutoEditado,codigoProdutoEditado);
                                                            System.out.println("Produto editado!");
                                                        }catch (IllegalArgumentException iae){
                                                            System.out.println(iae.getMessage());
                                                        }
                                                        break;
                                                    case 3: // edita quantidade
                                                        System.out.println("Digite a quantidade do produto");
                                                        int quantidadeProdutoEditado = entradaInteiro();
                                                        try {
                                                            sistemaFacade.editaQuantidadeProdutoLanchonete(quantidadeProdutoEditado,codigoProdutoEditado);
                                                            System.out.println("Produto editado!");
                                                        }catch (IllegalArgumentException iae){
                                                            System.out.println(iae.getMessage());
                                                        }

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
                // TODO acho que da pra colocar isso em algum controller e chamar 1 vez só pelo facade
                case 2: // Criar conta
                    System.out.println("Nome do usuario: ");
                    String nomeCriarUsuario = entradaString();
                    System.out.println("Senha do usuario: ");
                    String senhaCriarUsuario = entradaString();
                    sistemaFacade.criarContaCliente(nomeCriarUsuario,senhaCriarUsuario);
                    if (sistemaFacade.isLogado()){
                        System.out.println("Usuário criado com sucesso!");
                    }
                    break;

                case 3: // Sair
                    sistemaFacade.fechaCinema(); //TODO Arquivos
                    break;

                default:
                    System.err.println("Opção inválida!");
            }

        }while (Sistema.statusSistema);

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
