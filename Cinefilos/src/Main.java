import controller.GerenciaCinema;
import facades.SistemaFacade;
import model.cinema.TipoSala;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import view.TelaCliente;
public class Main {

    public static void main(String[] args) {

        SistemaFacade sistemaFacade = new SistemaFacade();
        sistemaFacade.abreCinema();

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
            switch (Sistema.scan.nextInt()){
                case 1: // Login
                    // TODO acho que da pra colocar isso em algum controller e chamar 1 vez só pelo facade
                    System.out.println("Nome do usuario: ");
                    String nomeLogin = sistemaFacade.entradaString();
                    System.out.println("Senha do usuario: ");
                    String senhaLogin = sistemaFacade.entradaString();
                    sistemaFacade.fazerLogin(nomeLogin,senhaLogin);
                    // Menu Cliente
                    if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.CLIENTE){
                        while (Sistema.getLOGADO() != null){
                            TelaCliente.imprimeDadosCliente((Cliente) Sistema.getLOGADO());
                            //TODO view
                            System.out.println("1 - Filmes | 2 - Lanchonete | 3 - Fechar pedido | 4 - Deslogar"); // TODO fazer com que só exiba a op. 3 se o carrinho !empty()
                            switch (Sistema.scan.nextInt()){
                                case 1: // exibição
                                    sistemaFacade.exibeIngressosDisponiveis();
                                    System.out.println("1 - Comprar | 0 - Voltar");
                                    switch (Sistema.scan.nextInt()){
                                        case 1:
                                            System.out.println("Insira o código do ingresso que deseja comprar");
                                            String escolhaDoIngresso = sistemaFacade.entradaString();
                                            System.out.println("Insira a quantidade");
                                            int escolhaQuantidadeIngressos = sistemaFacade.entradaInteiro();
                                            sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaDoIngresso, escolhaQuantidadeIngressos);
                                            sistemaFacade.teste();
                                            System.out.println("Ingressos adicionados!");
                                            sistemaFacade.verCarrinho();
                                            break;
                                        case 0: // Voltar
                                            break;
                                    }
                                    break;
                                case 2: // exibição e escolha produto Lanchonete
                                    sistemaFacade.verProdutoLanchonete();
                                    String escolhaLanchonete = String.valueOf(sistemaFacade.entradaInteiro());
                                    int quantidadeCompraProdutosLanchonete = sistemaFacade.entradaInteiro();
                                    if (!escolhaLanchonete.equals("0")){
                                        sistemaFacade.adicionaProdutoCarrinhoCompras(escolhaLanchonete,quantidadeCompraProdutosLanchonete);
                                        break;
                                    }
                                    break;
                                case 3: // fechar pedido
                                    sistemaFacade.verCarrinho();
                                    try{
                                        System.out.println(sistemaFacade.finalizaCompra()); //TODO tentar mandar isso para um método da view
                                    }catch (IllegalArgumentException iae){
                                        System.out.println(iae.getMessage());
                                    }
                                    break;
                                case 4: // cancela compra e sair do sistema
                                    sistemaFacade.limpaCarrinhoCompras();
                                    sistemaFacade.deslogar();
                                    break;
                            }
                        }
                    } else if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.GERENTE) {
                        while (Sistema.getLOGADO() != null){

                            switch (Sistema.scan.nextInt()){
                                case 1:
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                            }
                        }
                    }
                    break;
                // TODO acho que da pra colocar isso em algum controller e chamar 1 vez só pelo facade
                case 2: // Criar conta
                    System.out.println("Nome do usuario: ");
                    String nomeCriarUsuario = sistemaFacade.entradaString();
                    System.out.println("Senha do usuario: ");
                    String senhaCriarUsuario = sistemaFacade.entradaString();
                    sistemaFacade.criarContaCliente(nomeCriarUsuario,senhaCriarUsuario);
                    if (sistemaFacade.isLogado()){
                        System.out.println("Usuário criado com sucesso!");
                    }
                    break;

                case 3:
                    sistemaFacade.fechaCinema();
                    break;
            }

        }while (Sistema.statusSistema);

    }
}
