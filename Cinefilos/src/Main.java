import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.cinema.Filme;
import model.cinema.Sala;

public class Main {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("inception");
        meuFilme.setHoraFilme(LocalTime.of(18, 30));
        meuFilme.setDataFilme(LocalDate.of(2024, 4, 15));

        Sala salaA = new Sala(20, null, "A", "Inception", null);

        meuFilme.adicionarSalaExibicao(salaA);

        System.out.println(
                " Filme: " + meuFilme.getNomeFilme() + "\n Horario de exibição: " + meuFilme.getHoraFilme() + " "
                        + meuFilme.getDataFilme());

        System.out.println();
    }
}