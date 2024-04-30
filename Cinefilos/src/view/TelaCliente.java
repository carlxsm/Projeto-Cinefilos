package view;

import java.util.Set;

import model.cinema.Filme;
import model.cinema.Sala;
import model.sistema.usuario.Cliente;

public class TelaCliente {
    public static void imprimeDadosCliente(Cliente cliente) {
        System.out.println(cliente.getNome());
        System.out.print("Cliente " + cliente.getProgramaFidelidade().getFidelidade() + " | ");
        System.out.println("Pontuação " + cliente.getProgramaFidelidade().getPontos());
    }

    public static void imprimeMenuInicial() {
        System.out.println("Login - 1");
        System.out.println("Criar conta - 2");
        System.out.println("Sair - 3");
    }

    public static void imprimeOpcoesCompraCliente() {
        System.out.println("Ingressos - 0");
        System.out.println("Lanchonete - 1");
    }

    public void exibirProgramacao(Sala sala) {
        System.out.println("Programação da Sala: " + sala.getNomeSala());
        Set<Filme> programacaoFilmes = sala.getProgramacaoFilmes();
        for (Filme filme : programacaoFilmes) {
            System.out.println("Filme: " + filme.getNomeFilme());
            System.out.println("Tipo de Sala: " + sala.getTipoSala());
            System.out.println("Horário da Sessão: " + filme.getHorario());
            System.out.println("-----------------------------------------");
        }
    }

    public void imprimeValoresPedido(Cliente cliente) {
        // System.out.println("Total R$ "+
        // cliente.getCarrinhoComprasObj().valorTotalCarrinhoCompras());
    }

    public static void imprimeOpcoesFinalizarCompra() {
        System.out.println("Finalizar - 2");
        System.out.println("Limpar - 3");
    }

}
