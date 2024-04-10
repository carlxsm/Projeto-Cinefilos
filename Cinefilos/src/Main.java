public class Main {
    public static void main(String[] args) {

    }

    private static void menuClient() {
        System.out.println("+--------------------------------------------+");
        System.out.println("| [1] - Exibição.                            |");
        System.out.println("| [2] - Lanchonete.                          |");
        System.out.println("| [3] - Fechar pedido.                       |");
        System.out.println("| [4] - Cancelar compra e sair do sistema.   |");
        System.out.println("+--------------------------------------------+");
    }

    public static void menuInicial(){
        System.out.println("+--------------------------------------------+");
        System.out.println("| [1] - Modo cliente.                        |");
        System.out.println("| [2] - Modo gerente.                        |");
        System.out.println("| [3] - Sair.                                |");
        System.out.println("+--------------------------------------------+");
    }

    public static void menuGerente(){
        System.out.println("+--------------------------------------------+");
        System.out.println("| [1] - Atualizar exibições.                 |");
        System.out.println("| [2] - Atualizar itens do cardápio          |\n" +
                "|       da lanchonete.                       |");
        System.out.println("| [3] - Gerar relatório de                   |\n" +
                "|       compras com data da compra,          |\n" +
                "|       tipo do cartão do cliente e          |\n" +
                "|       valor da compra.                     |");
        System.out.println("| [4] - Sair do sistema.                     |");
        System.out.println("+--------------------------------------------+");
    }
}