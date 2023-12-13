package teste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VagaTest {

    private Vaga vaga;

    @BeforeEach
    void setUp() {
        vaga = new Vaga(1, 1); 
    }

    @Test
    void testConstrutor() {
        assertEquals("A1", vaga.getId(), "ID da vaga deve ser A1");
        assertTrue(vaga.isDisponivel(), "Vaga deve estar disponível após a criação");
    }

    @Test
    void testSetDisponivel() {
        vaga.setDisponivel(false);
        assertFalse(vaga.isDisponivel(), "Vaga deve estar indisponível");

        vaga.setDisponivel(true);
        assertTrue(vaga.isDisponivel(), "Vaga deve estar disponível");
    }

    @Test
    void testEstacionar() {
        assertTrue(vaga.estacionar("ABC1234"), "Deve ser possível estacionar se a vaga estiver disponível");
        assertFalse(vaga.isDisponivel(), "Vaga deve estar ocupada após estacionar");

        assertFalse(vaga.estacionar("XYZ5678"), "Não deve ser possível estacionar se a vaga já estiver ocupada");
    }

    @Test
    void testSair() {
        vaga.estacionar("ABC1234");
        assertTrue(vaga.sair(), "Deve ser possível sair se a vaga estiver ocupada");
        assertTrue(vaga.isDisponivel(), "Vaga deve estar disponível após sair");
        assertFalse(vaga.sair(), "Não deve ser possível sair se a vaga já estiver vazia");
    }
}

