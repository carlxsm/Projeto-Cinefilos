import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.cinema.Filme;
import model.cinema.Sala;
import model.lanchonete.ProdutoLanchonete;
import model.sistema.fidelidade.NivelFidelidade;
import model.sistema.usuario.Cliente;

public class Main {
    public static void main(String[] args) {
        // Criando um novo cliente com 100 pontos iniciais
        Cliente cliente = new Cliente("João", "senha123", 0, 100);

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
}