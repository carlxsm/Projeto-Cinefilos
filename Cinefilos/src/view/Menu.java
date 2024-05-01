package view;

import model.sistema.usuario.Cliente;

public class Menu {
    public static void menuPrincipal() {
        System.out.println("+====================+");
        System.out.println("Escolha uma das opções");
        System.out.println("+====================+");
        System.out.println("| [1] - Login        |");
        System.out.println("| [2] - Criar conta  |");
        System.out.println("| [3] - Sair         |");
        System.out.println("+====================+");
    }
    public static void menuSegundario() {
        System.out.println("+========================+");
        System.out.println("Escolha uma das opções");
        System.out.println("+========================+");
        System.out.println("| [1] - Filmes           |");
        System.out.println("| [2] - Lanchonete       |");
        System.out.println("| [3] - Fechar pedido    |");
        System.out.println("| [4] - Deslogar         |");
        System.out.println("+========================+");
    }
    public static void menuComprarVoltar(){
        System.out.println("+========================+");
        System.out.println("Escolha uma das opções");
        System.out.println("+========================+");
        System.out.println("| [1] - Comprar          |");
        System.out.println("| [2] - Voltar           |");
        System.out.println("+========================+");
    }

    public static void imprimeMenuAtualizalanchonete(){
        System.out.println("+==================================+");
        System.out.println("       Escolha uma das opções       ");
        System.out.println("+==================================+");
        System.out.println("| [1] - Adiciona novo produto      |");
        System.out.println("| [2] - Edita produto              |");
        System.out.println("| [3] - Remove produto             |");
        System.out.println("| [4] - Sair                       |");
        System.out.println("+==================================+");
    }
    public static void imprimeMenuEditarLanchonete(){
        System.out.println("+====================================+");
        System.out.println("        Escolha uma das opções       ");
        System.out.println("+====================================+");
        System.out.println("| [1] - Editar nome do produto       |");
        System.out.println("| [2] - Edita preço do produto       |");
        System.out.println("| [3] - Editar quantidade do produto |");
        System.out.println("| [4] - Sair                         |");
        System.out.println("+====================================+");
    }
    public static void imprimeMenuGerente(){
        System.out.println("+====================================+");
        System.out.println("        Escolha uma das opções       ");
        System.out.println("+====================================+");
        System.out.println("| [1] - Atualizar exibições          |");
        System.out.println("| [2] - Atualiza Lanchonete          |");
        System.out.println("| [3] - Relatório                    |");
        System.out.println("| [4] - Sair                         |");
        System.out.println("+====================================+");
    }
    public static void imprimeMenuEditarCinema(){
        System.out.println("+=======================+");
        System.out.println("Escolha uma das opções");
        System.out.println("+=======================+");
        System.out.println("| [1] - Editar cinema   |");
        System.out.println("| [2] - Sair            |");
        System.out.println("+=======================+");
    }
    public static void imprimeMenuAdicionarRemoverFilme(){
        System.out.println("+====================================+");
        System.out.println("        Escolha uma das opções       ");
        System.out.println("+====================================+");
        System.out.println("| [1] - Cria Filme                   |");
        System.out.println("| [2] - Remover filme                |");
        System.out.println("| [3] - Cria Ingresso                |");
        System.out.println("| [4] - Volta                        |");
        System.out.println("+====================================+");
    }
    public static void imprimeDadosCliente(Cliente cliente){
        System.out.println(cliente.getNome());
        System.out.print("Cliente "+cliente.getProgramaFidelidade().getFidelidade()+" | ");
        System.out.println("Pontuação "+cliente.getProgramaFidelidade().getPontos());
    }
}