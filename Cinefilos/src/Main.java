import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Usuario;
import view.TelaCliente;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Cliente cliente = new Cliente("Carlos","admin", CategoriaUsuario.CLIENTE);
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        ProdutoLanchonete refrigerante = new ProdutoLanchonete("Refrigerante",9.99,1);
        ProdutoLanchonete pipoca = new ProdutoLanchonete("Pipoa",24.99,1);
        cliente.getCarrinhoCompras().adicionarProduto(refrigerante);
        cliente.getCarrinhoCompras().adicionarProduto(pipoca);
        // tela1View tela1 = new tela1View();
        // tela1.exibiTela();
        do{
            //checa se clicou em criar
                // chamdaDeCriar();
            // checa se clicou em login
                // chamadaDeLogar();
            // checa se vai sair da aplicação
                // chamadaDeSair();
        }while (sistema.isLogado());
        telaCliente();
    }

    //Sistema de login vai retornar um usuario
    // if(usuario.CategoriaUsuario == Cliente)
    public static void telaCliente(){
        TelaCliente telaCliente = new TelaCliente();
        while(cliente!= null) {
            if (cliente.getCarrinhoCompras().getCarrinhoDeCompras().isEmpty()){ //TODO refatorar isso depois
                telaCliente.imprimeDadosCliente(cliente);
                telaCliente.imprimeOpcoesCompraCliente();
                int entrada = Integer.parseInt(scanner.next());
                switch (entrada) {
                    case 0:
                        System.out.println("Tela de Ingresso");
                        break;
                    case 1:
                        System.out.println("Tela de Lanchonete");
                        break;
                }
            }else{
                telaCliente.imprimeDadosCliente(cliente);
                telaCliente.imprimeOpcoesCompraCliente();
                telaCliente.imprimeValoresPedido(cliente);
                telaCliente.imprimeOpcoesFinalizarCompra();
                int entrada = Integer.parseInt(scanner.next());
                switch (entrada){
                    case 0:
                        System.out.println("Tela de Ingresso");
                        break;
                    case 1:
                        System.out.println("Tela de Lanchonete");
                        break;
                    case 2:
                        System.out.println("Pedido finalizado");
                        break;
                    case 3:
                        System.out.println("Carrinho de compras esvaziado!");
                        break;
                }
            }
        }
    }




































    // Sistema.chamadaDeCriar(){
        //gerenciaSistema.checaSeGerente()
        //gerenciaSistema.checaSeCliente()
        //gerenciaSistema.checaSeVoltou();
    // }

    //gerenciaSistema.checaSeCliente(){
        //if(isCliente){
            //gerenciaSistema.atualizaDados(){
                // controller.gerenciaSistema.atualizaNomeCliente
                // controller.gerenciaSistema.atualizaPontos
                // controller.gerenciaSistema.atualizaNivelFidelidade
    //      }
            // if(controle.clicouIngresso()){
    //          //view.telaIngressoCliente(){
    //             while(gerenciaSistema.botaoVoltar()){
    //              voew.InstanciaTelaIngresso();{
    //    //                  exibirFilmesEmCartaz();
    //    //                  usuario.carrinhoDeCompras.recebeListaSusprensa()
    //    //                  usuario.carrinhoDeCompras.recebeQuantidadeDeIngresso()
    //    //                  usuario.carrinhoDeCompras.exibeTotal()
    //    //                  usuario.carrinhoDeCompras.exibeDesconto()
    //    //                  usuario.carrinhoDeCompras.recebeFilme();
    //    //                  if(carromhpDeCpmpras.buttonAdicionaCarrinho()){
    //    //                      usuario.carrinhoDeCompras.adicionaFilmeCarrinho()
    //    //                      controle.voltaTelaCliente();
    //    //                  }else(carrinhoDeCompras.buttonVoltar()){
    //    //                      controle.voltaTelaCliente();
    //    //                  }
    //    //              }
    //              }
    //          if(controle.clicouLanchonete){
    //              //fluxoLanchonete()
    //              //if(clicouBotaVoltar())
    //          }
    //          if(!usuario.carrinhoCompra.isEmpty){
    //              //usuario.carrinhoDeCompras.exibeTotal()
    //              //if(clicouButtonFinalizar()){
    //                  usuario.carrinhoDeCompras.finalizaCompra();
    //
    //              }if(clicouButtonClear()){
    //                  usuario.carrinhoDeCompras.limpaCarrinho()
    //                  controle.voltaTelaCliente();
    //              }
    //          }else{
    //
    //          }
    //          }
    // }
            // if(controle.clicouLanchonete())

        //else if(isGerente){
                //
    // }else{
        // throw error("")
    // }


    // }
}