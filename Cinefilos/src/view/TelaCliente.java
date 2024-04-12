package view;

import model.sistema.usuario.Cliente;

public class TelaCliente {
    public void imprimeDadosCliente(Cliente cliente){
        System.out.println(cliente.getNome());
        System.out.println("Cliente "+cliente.getProgramaFidelidade().getFidelidade());
        System.out.println("Pontuação "+cliente.getProgramaFidelidade().getPontos());
    }

    public void imprimeOpcoesCompraCliente(){
        System.out.println("Ingressos - 0");
        System.out.println("Lanchonete - 1");
    }

    public void imprimeValoresPedido(Cliente cliente){
        System.out.println("Total R$ "+ cliente.getCarrinhoCompras().valorTotalCarrinhoCompras());
    }

    public void imprimeOpcoesFinalizarCompra(){
        System.out.println("Finalizar - 2");
        System.out.println("Limpar - 3");
    }
}
