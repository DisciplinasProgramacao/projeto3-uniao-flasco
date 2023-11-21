package teste;

import business.Vaga.Vaga;
import business.Veiculo.Veiculo;
import business.UsoDeVaga.UsoDeVaga;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VeiculoTest {

    private Veiculo veiculo;
    private Vaga vaga;


    @Before
    public void setUp() {
        veiculo = new Veiculo("ABC1234");
        vaga = new Vaga(1, 1);
    }

    @Test
    public void testEstacionar() {
        veiculo.estacionar(vaga);
        assertEquals(1, veiculo.getUsos().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEstacionarComVagaIndisponivel() {
        vaga.setDisponivel(false);
        veiculo.estacionar(vaga);
    }

    @Test
    public void testTotalArrecadado() {
        Vaga vaga1 = new Vaga(1, 1);
        Vaga vaga2 = new Vaga(1, 2);
        UsoDeVaga uso1 = new UsoDeVaga(vaga1, LocalDateTime.now().minusHours(2));
        uso1.setValorPago(10.0);
        veiculo.getUsos().add(uso1);

        UsoDeVaga uso2 = new UsoDeVaga(vaga2, LocalDateTime.now().minusDays(1).minusHours(4));
        uso2.setValorPago(15.0);
        veiculo.getUsos().add(uso2);

        double totalArrecadado = veiculo.totalArrecadado();
        double totalEsperado = 25.0;
        assertEquals(totalEsperado, totalArrecadado, 0.01);
    }

    @Test
    public void testArrecadadoNoMes() {
        Vaga vaga1 = new Vaga(2, 3);
        Vaga vaga2 = new Vaga(2, 4);
        UsoDeVaga usoMaio = new UsoDeVaga(vaga1, LocalDateTime.of(2023, Month.MAY, 10, 8, 30));
        usoMaio.setValorPago(20.0);
        veiculo.getUsos().add(usoMaio);
        UsoDeVaga usoJunho = new UsoDeVaga(vaga2, LocalDateTime.of(2023, Month.JUNE, 15, 9, 0));
        usoJunho.setValorPago(30.0);
        veiculo.getUsos().add(usoJunho);
        double arrecadadoEmMaio = veiculo.arrecadadoNoMes(Month.MAY.getValue());
        double valorEsperadoEmMaio = 20.0;
        assertEquals(valorEsperadoEmMaio, arrecadadoEmMaio, 0.01);
    }
}
