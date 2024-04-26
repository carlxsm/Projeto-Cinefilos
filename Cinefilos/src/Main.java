import facades.SistemaFacade;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import view.TelaCliente;
public class Main {

    public static void main(String[] args) {

        SistemaFacade sistemaFacade = new SistemaFacade();
        sistemaFacade.abreCinema();
        sistemaFacade.criarContaCliente("admin","admin");

        do {
            TelaCliente.imprimeMenuInicial();
            switch (Sistema.scan.nextInt()){
                // TODO acho que da pra colocar isso em algum controller e chamar 1 vez só pelo facade
                case 1: // Login
                    System.out.println("Nome do usuario: ");
                    String nomeLogin = sistemaFacade.entradaString();
                    System.out.println("Senha do usuario: ");
                    String senhaLogin = sistemaFacade.entradaString();
                    sistemaFacade.fazerLogin(nomeLogin,senhaLogin);
                    if (sistemaFacade.getTipoUsuarioLogado() == CategoriaUsuario.CLIENTE){
                        while (Sistema.getLOGADO() != null){
                            TelaCliente.imprimeDadosCliente((Cliente) Sistema.getLOGADO());
                            switch (Sistema.scan.nextInt()){
                                case 1: // exibição
                                    sistemaFacade.verFilmesDisponiveis();
                                    sistemaFacade.adicionaProdutoCarrinhoCompras(null);
                                    break;
                                case 2: // lanchonete
                                    sistemaFacade.verProdutoLanchonete();
                                    sistemaFacade.adicionaProdutoCarrinhoCompras(null);
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
