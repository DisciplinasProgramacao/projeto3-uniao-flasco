package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import business.Vaga.Vaga;

public class VagaTest {

    private Vaga vaga;

    @Before
    public void setUp() {
        vaga = new Vaga(1, 5);
    }

    @Test
    public void testConstrutor() {
        assertEquals("A5", vaga.getId());
        assertTrue(vaga.isDisponivel());
    }

    @Test
    public void testSetDisponivel() {
        vaga.setDisponivel(false);
        assertFalse(vaga.isDisponivel());
    }

    @Test
    public void testEstacionarVeiculoQuandoDisponivel() {
        assertTrue(vaga.estacionar("ABC1234"));
        assertFalse(vaga.isDisponivel());
    }

    @Test
    public void testEstacionarVeiculoQuandoIndisponivel() {
        vaga.estacionar("ABC1234");
        assertFalse(vaga.estacionar("XYZ5678"));
    }

    @Test
    public void testSairQuandoVagaOcupada() {
        vaga.estacionar("ABC1234");
        assertTrue(vaga.sair());
        assertTrue(vaga.isDisponivel());
    }

    @Test
    public void testSairQuandoVagaJaEstaDisponivel() {
        assertFalse(vaga.sair());
    }
}
