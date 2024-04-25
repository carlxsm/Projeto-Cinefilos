import controller.CarrinhoCompras;
import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import facades.SistemaFacade;
import model.cinema.Filme;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.usuario.CategoriaUsuario;
import model.sistema.usuario.Cliente;
import model.sistema.usuario.Usuario;
import view.TelaCliente;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Cliente cliente = new Cliente("Carlos","admin", CategoriaUsuario.CLIENTE);
    private static GerenciaCinema gerenciaCinema = new GerenciaCinema();
    private static GerenciaLanchonete gerenciaLanchonete = new GerenciaLanchonete();

    public static void main(String[] args) {

        SistemaFacade sistemaFacade = new SistemaFacade();

        sistemaFacade.iniciaAplicacao();
        sistemaFacade.compraProduto();


        gerenciaLanchonete.adicionaProduto(new ProdutoLanchonete("Refrigerante",9.99,99));
        gerenciaLanchonete.adicionaProduto(new ProdutoLanchonete("Pipoca",24.99,99));
        gerenciaLanchonete.adicionaProduto(new ProdutoLanchonete("Pipoca",24.99,99));
        cliente.getCarrinhoCompras().add(GerenciaLanchonete.getProdutosDisponiveis().get(0));
        cliente.getCarrinhoCompras().add(GerenciaLanchonete.getProdutosDisponiveis().get(1));

        do{
            // TODO aqui vai ser a tela inicial de Login / Cria conta / Sair
        telaCliente();
        }while (true);
    }

    public static void telaCliente(){
        TelaCliente telaCliente = new TelaCliente();
        while(cliente!= null) {
            if (cliente.getCarrinhoCompras().isEmpty()){
                telaCliente.imprimeDadosCliente(cliente);
                telaCliente.imprimeOpcoesCompraCliente();
                int entrada = Integer.parseInt(scanner.next());
                switch (entrada) {
                    case 0:
                        telaDeIngresso();
                        System.out.println("Tela de Ingresso");
                        break;
                    case 1:
                        telaDaLanchonete();
                        System.out.println("Tela de Lanchonete");
                        break;
                }
            }else{ // TODO poderiamos encapsular esses metodos abaixo em uma só chamada;
                telaCliente.imprimeDadosCliente(cliente);                           //
                telaCliente.imprimeOpcoesCompraCliente();                           //
                telaCliente.imprimeValoresPedido(cliente);                          //
                telaCliente.imprimeOpcoesFinalizarCompra();                         //
                CarrinhoCompras carrinhoCompras = new CarrinhoCompras();
                int entrada = Integer.parseInt(scanner.next());
                switch (entrada){
                    case 0:
                        telaDeIngresso();
                        System.out.println("Tela de Ingresso");
                        break;
                    case 1:
                        telaDaLanchonete();
                        System.out.println("Tela de Lanchonete");
                        break;
                    case 2:
                        // TODO calcular preco total e desconto
                        // TODO adicionar o valor as compras do dia (sistema.class)
                        // TODO metodo para adicionar os pontos da compra, esse mesmo método vai verificar se o usuario upou de nivel
                        System.out.println(cliente.getCarrinhoComprasObj().valorTotalCarrinhoCompras() + " Sem desconto");
                        System.out.println(calculaFaturaFinal() + " Com desconto");
                        System.out.println("Pedido finalizado");
                        break;
                    case 3:
                        carrinhoCompras.esvaziarCarrinho();
                        System.out.println("Carrinho de compras esvaziado!");
                        break;
                }
            }
        }
    }

    private static double calculaFaturaFinal() { // TODO não sei se esse método realmente pertence a essa classe;
        double totalSemDesconto = cliente.getCarrinhoComprasObj().valorTotalCarrinhoCompras();
        return cliente.getProgramaFidelidade().calculaDesconto(totalSemDesconto);
    }

    public static void telaDeIngresso(){
        for (Filme filme: GerenciaCinema.getFilmesEmCartaz()){
            System.out.println(filme.getNomeFilme());
        }
        // TODO adicionar ingressos ao carrinho ou voltar para tela cliente;
    }

    public static void telaDaLanchonete(){
        for (ProdutoLanchonete produtoLanchonete: GerenciaLanchonete.getProdutosDisponiveis()){
            System.out.println(produtoLanchonete.getNome()+": R$"+produtoLanchonete.getPreco());
        }
        // TODO adicionar produtos ao carrinho ou voltar para tela inicial;
    }

}