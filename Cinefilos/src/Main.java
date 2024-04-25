import java.util.Scanner;

import controller.CarrinhoCompras;
import controller.GerenciaCinema;
import controller.GerenciaLanchonete;
import model.cinema.Filme;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.Sistema;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.usuario.Cliente;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Cliente cliente = new Cliente("Carlos", "admin", 0, 100);
    private static GerenciaCinema gerenciaCinema = new GerenciaCinema();
    private static GerenciaLanchonete gerenciaLanchonete = new GerenciaLanchonete();

    public static void main(String[] args) {
        // Criando um novo cliente com 100 pontos iniciais
        Cliente cliente = new Cliente("João", "senha123", 1, 100);

        // Verificando os pontos de fidelidade do cliente
        int pontosAtuais = cliente.getPontos();
        System.out.println("Pontos de fidelidade do cliente: " + pontosAtuais);

        // Verificando o nível de fidelidade do cliente
        NivelFidelidade nivel = cliente.getNivelFidelidade();
        System.out.println("Nível de fidelidade do cliente: " + nivel);

        // Acumulando mais pontos de fidelidade para o cliente
        cliente.acumulaPontos(50);
        System.out.println("Pontos de fidelidade do cliente após acumular mais pontos: " + cliente.getPontos());
    }

    // Sistema.chamadaDeCriar(){
    // gerenciaSistema.checaSeGerente()
    // gerenciaSistema.checaSeCliente()
    // gerenciaSistema.checaSeVoltou();
    // }

    // gerenciaSistema.checaSeCliente(){
    // if(isCliente){
    // gerenciaSistema.atualizaDados(){
    // controller.gerenciaSistema.atualizaNomeCliente
    // controller.gerenciaSistema.atualizaPontos
    // controller.gerenciaSistema.atualizaNivelFidelidade
    // }
    // if(controle.clicouIngresso()){
    // //view.telaIngressoCliente(){
    // while(gerenciaSistema.botaoVoltar()){
    // voew.InstanciaTelaIngresso();{
    // // exibirFilmesEmCartaz();
    // // usuario.carrinhoDeCompras.recebeListaSusprensa()
    // // usuario.carrinhoDeCompras.recebeQuantidadeDeIngresso()
    // // usuario.carrinhoDeCompras.exibeTotal()
    // // usuario.carrinhoDeCompras.exibeDesconto()
    // // usuario.carrinhoDeCompras.recebeFilme();
    // // if(carromhpDeCpmpras.buttonAdicionaCarrinho()){
    // // usuario.carrinhoDeCompras.adicionaFilmeCarrinho()
    // // controle.voltaTelaCliente();
    // // }else(carrinhoDeCompras.buttonVoltar()){
    // // controle.voltaTelaCliente();
    // // }
    // // }
    // }
    // if(controle.clicouLanchonete){
    // //fluxoLanchonete()
    // //if(clicouBotaVoltar())
    // }
    // if(!usuario.carrinhoCompra.isEmpty){
    // //usuario.carrinhoDeCompras.exibeTotal()
    // //if(clicouButtonFinalizar()){
    // usuario.carrinhoDeCompras.finalizaCompra();
    //
    // }if(clicouButtonClear()){
    // usuario.carrinhoDeCompras.limpaCarrinho()
    // controle.voltaTelaCliente();
    // }
    // }else{
    //
    // }
    // }
    // }
    // if(controle.clicouLanchonete())

    // else if(isGerente){
    //
    // }else{
    // throw error("")
    // }

}