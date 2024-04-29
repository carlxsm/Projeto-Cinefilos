package view;

import model.sistema.usuario.Cliente;

public class TelaCliente {
    public static void imprimeDadosCliente(Cliente cliente){
        System.out.println(cliente.getNome());
        System.out.print("Cliente "+cliente.getProgramaFidelidade().getFidelidade()+" | ");
        System.out.println("Pontuação "+cliente.getProgramaFidelidade().getPontos());
    }

    public static void imprimeMenuInicial(){
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [1] - Login        |");
        System.out.println("| [2] - Criar conta  |");
        System.out.println("| [3] - Sair         |");
        System.out.println("+====================+");
    }
    public static void imprimeOpcoesCompraCliente(){
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [0] - Ingressos    |");
        System.out.println("| [1] - Lanchonete   |");
        System.out.println("+====================+");
    }

    public void imprimeValoresPedido(Cliente cliente){
        //System.out.println("Total R$ "+ cliente.getCarrinhoComprasObj().valorTotalCarrinhoCompras());
    }

    public static void imprimeOpcoesFinalizarCompra(){
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [2] - Finalizar    |");
        System.out.println("| [3] - Limpar       |");
        System.out.println("+====================+");
    }

}
