import facades.SistemaFacade;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import view.TelaCliente;

import java.util.Scanner;

public class Main {
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

        sistemaFacade.teste();

        do {
            TelaCliente.imprimeMenuInicial();
            switch (entradaInteiro()){
                case 1: // Login
                    // TODO acho que da pra colocar isso em algum controller e chamar 1 vez só pelo facade
                    System.out.println("Nome do usuario: ");
                    String nomeLogin = entradaString();
                    System.out.println("Senha do usuario: ");
                    String senhaLogin = entradaString();
                    sistemaFacade.fazerLogin(nomeLogin,senhaLogin);
                    // Menu Cliente
                    if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.CLIENTE){
                        while (Sistema.getLOGADO() != null){
                            TelaCliente.imprimeDadosCliente((Cliente) Sistema.getLOGADO());
                            System.out.println("1 - Filmes | 2 - Lanchonete | 3 - Fechar pedido | 4 - Deslogar"); // TODO fazer com que só exiba a op. 3 se o carrinho !empty()
                            switch (entradaInteiro()){ //TODO fazer os default
                                case 1: // exibição
                                    sistemaFacade.exibeIngressosDisponiveis();
                                    System.out.println("1 - Comprar | 0 - Voltar");
                                    switch (entradaInteiro()){
                                        case 1: // comprar
                                            System.out.println("Insira o código do ingresso que deseja comprar");
                                            String escolhaDoIngresso = entradaString();
                                            System.out.println("Insira a quantidade");
                                            int escolhaQuantidadeIngressos = entradaInteiro();
                                            sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoIngresso, escolhaQuantidadeIngressos);
                                            System.out.println("Ingressos adicionados!");
                                            sistemaFacade.verCarrinho();
                                            break;
                                        case 0: // voltar
                                            break;
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
                                            sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoProdutoLanchonete,quantidadeProdutoLanchonete);
                                            System.out.println("Produtos adicionados!");
                                            sistemaFacade.verCarrinho();
                                            break;
                                        case 0: // voltar
                                            break;
                                    }
                                    break;
                                case 3: // fechar pedido
                                    try{
                                        System.out.println("Compra concluida");
                                        System.out.println("Total: R$"+sistemaFacade.finalizaCompra()); //TODO tentar mandar isso para um método da view
                                    }catch (IllegalArgumentException iae){
                                        System.out.println(iae.getMessage());
                                    }
                                    break;
                                case 4: // cancela compra e sair do sistema
                                    sistemaFacade.cancelarCompra();
                                    break;
                            }
                        }
                    }
                    // Menu gerente
                    else if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.GERENTE) {
                        while (Sistema.getLOGADO() != null){
                            System.out.println("1 - Atualizar exibições | 2 - Atualiza Lanchonete | 3 - Relatório | 4 - Sair");
                            switch (entradaInteiro()){
                                case 1: // Atualizar exibicoes
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
                                            sistemaFacade.criaNovoProdutoLanchonete(nomeNovoProduto, Double.parseDouble(precoNovoProduto),quantidadeNovoProduto);
                                            System.out.println("Produtos adicionados!");
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
                                                        sistemaFacade.editaNomeProdutoLanchonete(nomeProdutoEditadoNome,codigoProdutoEditado);
                                                        System.out.println("Produto editado!");
                                                        break;
                                                    case 2: // Edita preco
                                                        System.out.println("Digite o novo nome do produto");
                                                        double precoProdutoEditado = Double.parseDouble(entradaString());
                                                        sistemaFacade.editaPrecoProdutoLanchonete(precoProdutoEditado,codigoProdutoEditado);
                                                        System.out.println("Produto editado!");
                                                        break;
                                                    case 3: // edita quantidade
                                                        System.out.println("Digite a quantidade do produto");
                                                        int quantidadeProdutoEditado = entradaInteiro();
                                                        sistemaFacade.editaQuantidadeProdutoLanchonete(quantidadeProdutoEditado,codigoProdutoEditado);
                                                        System.out.println("Produto editado!");
                                                        break;
                                                    case 4: // sair
                                                        break;
                                                }
                                            break;
                                        case 3: // remove
                                            break;
                                        case 4: // voltar
                                            break;
                                    }
                                    break;
                                case 3: // gerar relatorio de compras
                                    sistemaFacade.gerarRelatorio();
                                    break;
                                case 4: // sair do sistema
                                    sistemaFacade.deslogar();
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

                case 3:
                    sistemaFacade.fechaCinema(); //TODO Arquivos
                    break;
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
