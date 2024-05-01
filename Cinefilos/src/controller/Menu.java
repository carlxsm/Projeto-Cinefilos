package controller;

import facades.SistemaFacade;

import java.util.NoSuchElementException;

public class Menu {
    public static void menuPrincipal() {
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [1] - Login        |");
        System.out.println("| [2] - Criar conta  |");
        System.out.println("| [3] - Sair         |");
        System.out.println("+====================+");
    }
    public static void menuSegundario() {
        System.out.println("Escolha uma das opções");
        System.out.println("+========================+");
        System.out.println("| [1] - Filmes           |");
        System.out.println("| [2] - Lanchonete       |");
        System.out.println("| [3] - Fechar pedido    |");
        System.out.println("| [4] - Deslogar         |");
        System.out.println("+========================+");
    }
    public static void menuComprarVoltar(){
        System.out.println("Escolha uma das opções");
        System.out.println("+========================+");
        System.out.println("| [1] - Comprar          |");
        System.out.println("| [2] - Voltar           |");
        System.out.println("+========================+");
    }
    public static void imprimeOpcoesCompraCliente(){
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [1] - Ingressos    |");
        System.out.println("| [2] - Lanchonete   |");
        System.out.println("+====================+");
    }
    public static void imprimeOpcoesFinalizarCompra(){
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [1] - Finalizar    |");
        System.out.println("| [2] - Limpar       |");
        System.out.println("+====================+");
    }
    public static void imprimeMenuAtualizalanchonete(){
        System.out.println("       Escolha uma das opções       ");
        System.out.println("+==================================+");
        System.out.println("| [1] - Adiciona novo produto      |");
        System.out.println("| [2] - Edita produto              |");
        System.out.println("| [3] - Remove produto             |");
        System.out.println("| [4] - Sair                       |");
        System.out.println("+==================================+");
    }
    public static void imprimeMenuEditarLanchonete(){
        System.out.println("        Escolha uma das opções       ");
        System.out.println("+====================================+");
        System.out.println("| [1] - Editar nome do produto       |");
        System.out.println("| [2] - Edita preço do produto       |");
        System.out.println("| [3] - Editar quantidade do produto |");
        System.out.println("| [4] - Sair                         |");
        System.out.println("+====================================+");
    }



}