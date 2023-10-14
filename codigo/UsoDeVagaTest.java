import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class UsoDeVagaTest {

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private UsoDeVaga usoDeVaga;

    @Before
    public void setUp() {
        int fila = 1;
        int numero = 5;
        vaga = new Vaga(fila, numero);
        entrada = LocalDateTime.of(2023, 10, 11, 9, 0);
        saida = LocalDateTime.of(2023, 10, 11, 11, 30);
        valorPago = 0.0;
        usoDeVaga = new UsoDeVaga(vaga, entrada, saida, valorPago);
    }

    @Test
    public void testValorPago() {
        double valorEsperado = 10.0;
        double valorCalculado = usoDeVaga.valorPago();
        assertEquals(valorEsperado, valorCalculado, 0.001);
    }

}
