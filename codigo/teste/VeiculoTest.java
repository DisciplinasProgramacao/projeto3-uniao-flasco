package teste;

import business.Plano.Plano;
import business.Vaga.Vaga;
import business.Interfaces.Observador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    private Veiculo veiculo;
    private Plano planoMock;
    private Vaga vagaMock;
    private Observador observadorMock;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo("ABC1234");
        planoMock = Mockito.mock(Plano.class);
        vagaMock = Mockito.mock(Vaga.class);
        observadorMock = Mockito.mock(Observador.class);
        Mockito.when(vagaMock.isDisponivel()).thenReturn(true);
    }

    @Test
    void testEstacionar() {
        veiculo.estacionar(vagaMock);
        assertFalse(vagaMock.isDisponivel());
        assertEquals(1, veiculo.getUsos().size());
    }

    @Test
    void testEstacionarVagaIndisponivel() {
        Mockito.when(vagaMock.isDisponivel()).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> veiculo.estacionar(vagaMock));
    }

    @Test
    void testSair() {
        veiculo.estacionar(vagaMock);
        double valor = veiculo.sair(planoMock);
    }

    @Test
    void testTotalArrecadadoEArrecadadoNoMes() {
        veiculo.estacionar(vagaMock);
        veiculo.sair(planoMock);
        double totalArrecadado = veiculo.totalArrecadado();
        double arrecadadoNoMes = veiculo.arrecadadoNoMes(LocalDateTime.now().getMonthValue());
    }

    @Test
    void testAddERemoveObservador() {
        veiculo.addObservador(observadorMock);
        assertTrue(veiculo.getObservadores().contains(observadorMock));
        veiculo.removeObservador(observadorMock);
        assertFalse(veiculo.getObservadores().contains(observadorMock));
    }

    @Test
    void testIsEstacionado() {
        assertFalse(veiculo.isEstacionado());
        veiculo.estacionar(vagaMock);
        assertTrue(veiculo.isEstacionado());
    }
}
