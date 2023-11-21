package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.time.LocalDateTime;
import business.Vaga.Vaga;
import business.UsoDeVaga.UsoDeVaga;
import org.junit.Before;
import org.junit.Test;

public class UsoDeVagaTest {

    private Vaga vaga;
    private LocalDateTime entrada;
    private UsoDeVaga usoDeVaga;

    @Before
    public void setUp() {
        vaga = new Vaga( 1 ,2);
        entrada = LocalDateTime.now();
        usoDeVaga = new UsoDeVaga(vaga, entrada);
    }

    @Test
    public void testAdicionarServico() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.LAVAGEM);
        assertEquals(1, usoDeVaga.getServicosAdicionais().size());
    }

    @Test
    public void testSairSemPlano() {
        LocalDateTime saida = entrada.plusHours(2);
        double valor = usoDeVaga.sair(saida, null);
        double valorEsperado = 32.0;
        assertEquals(valorEsperado, valor, 0.01);
    }

    @Test
    public void testValorPagoComExcecaoParaLavagem() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.LAVAGEM);
        LocalDateTime saida = entrada.plusMinutes(30);
        usoDeVaga.setSaida(saida);
        assertThrows(IllegalArgumentException.class, () -> usoDeVaga.valorPago());
    }
}
