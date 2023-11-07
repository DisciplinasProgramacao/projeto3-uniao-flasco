package teste;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import business.Vaga;

import org.junit.jupiter.api.BeforeEach;

public class VagaTest {
    
    public static Vaga vaga;

    @BeforeEach
    public void setup() {
        char fila = 24;
        int numero = 9;
        vaga = new Vaga(fila, numero);
    }
    @Test
    public void testGetID() {
        assertEquals("X9", vaga.getId());
    }

    @Test
    public void testEstacionar() {
        assertTrue(vaga.estacionar());
    }

    @Test
    public void testSair() {
        vaga.estacionar();
        assertTrue(vaga.sair());
    }

    @Test
    public void testIsDisponivel() {
        assertTrue(vaga.isDisponivel());
    }
}
