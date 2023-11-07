package teste;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import business.UsoDeVaga.UsoDeVaga;
import business.UsoDeVaga.UsoDeVaga.ServicoAdicional;
import business.Vaga.Vaga;

public class UsoDeVagaTest {

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private UsoDeVaga usoDeVaga;

    @Before
    public void setUp() {
        int fila = 1;
        int numero = 5;
        vaga = new Vaga(fila, numero);
        entrada = LocalDateTime.of(2023, 10, 11, 9, 0);
        saida = LocalDateTime.of(2023, 10, 11, 11, 30);
        usoDeVaga = new UsoDeVaga(vaga, entrada);
    }

    @Test
    public void testValorPago() {
        double valorEsperado = 10.0;
        usoDeVaga.sair(saida);
        double valorCalculado = usoDeVaga.valorPago();
        assertEquals(valorEsperado, valorCalculado, 0.001);
    }

    @Test
    public void testAdicionarServicoLavagem() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.LAVAGEM);
        usoDeVaga.sair(saida);
        double valorEsperado = 10.0 + 20.0; 
        double valorCalculado = usoDeVaga.valorPago();
        assertEquals(valorEsperado, valorCalculado, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdicionarServicoLavagemComTempoInsuficiente() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.LAVAGEM);
        LocalDateTime saidaTemp = LocalDateTime.of(2023, 10, 11, 9, 30);
        usoDeVaga.sair(saidaTemp);
        usoDeVaga.valorPago();
    }

    @Test
    public void testAdicionarServicoPolimento() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.POLIMENTO);
        usoDeVaga.sair(saida);
        double valorEsperado = 10.0 + 45.0; 
        double valorCalculado = usoDeVaga.valorPago();
        assertEquals(valorEsperado, valorCalculado, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdicionarServicoPolimentoComTempoInsuficiente() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.POLIMENTO);
        LocalDateTime saidaTemp = LocalDateTime.of(2023, 10, 11, 10, 0);
        usoDeVaga.sair(saidaTemp);
        usoDeVaga.valorPago();
    }
}
