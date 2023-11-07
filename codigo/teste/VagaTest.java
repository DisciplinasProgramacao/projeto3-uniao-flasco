package teste;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import business.Vaga;

import org.junit.jupiter.api.BeforeEach;

public class VagaTest {
    
    public static Vaga vaga;

    @BeforeEach
    public void setup() {
        char fila = 'A';
        int numero = 9;
        vaga = new Vaga(fila, numero);
    }

    @Test
    public void testEstacionar() {
        assertTrue(vaga.estacionar("AAA1111"));
        assertFalse(vaga.isDisponivel());
        assertEquals("AAA1111", vaga.getVeiculoEstacionado());
    }

    @Test
    public void testSair() {
        vaga.estacionar("AAA1111");
        assertTrue(vaga.sair());
        assertTrue(vaga.isDisponivel());
        assertNull(vaga.getVeiculoEstacionado());
    }

    @Test
    public void testIsDisponivel() {
        assertTrue(vaga.isDisponivel());
    }
}
