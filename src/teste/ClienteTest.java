package teste;

import business.Plano.Plano;
import business.Veiculo.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;
    private Veiculo veiculoMock;
    private Plano planoMock;

    @BeforeEach
    void setUp() {
        planoMock = Mockito.mock(Plano.class);
        veiculoMock = Mockito.mock(Veiculo.class);
        cliente = new Cliente("Cliente Teste", "123", planoMock);
    }

    @Test
    void testAdicionarEObterVeiculos() {
        cliente.addVeiculo(veiculoMock);
        assertEquals(1, cliente.getVeiculos().size());
        assertTrue(cliente.getVeiculos().contains(veiculoMock));
    }

    @Test
    void testPossuiVeiculo() {
        Mockito.when(veiculoMock.getPlaca()).thenReturn("ABC1234");
        cliente.addVeiculo(veiculoMock);
        assertTrue(cliente.possuiVeiculo("ABC1234"));
        assertFalse(cliente.possuiVeiculo("XYZ9876"));
    }

    @Test
    void testGetTotalUsos() {
        Mockito.when(veiculoMock.totalDeUsos()).thenReturn(5);
        cliente.addVeiculo(veiculoMock);
        assertEquals(5, cliente.getTotalUsos());
    }

    @Test
    void testGetValorArrecadado() {
        Mockito.when(veiculoMock.getPlaca()).thenReturn("ABC1234");
        Mockito.when(veiculoMock.totalArrecadado()).thenReturn(1000.0);
        cliente.addVeiculo(veiculoMock);
        assertEquals(1000.0, cliente.getValorArrecadado("ABC1234"));
        assertEquals(0.0, cliente.getValorArrecadado("XYZ9876"));
    }

    @Test
    void testGetValorTotalArrecadado() {
        Mockito.when(veiculoMock.totalArrecadado()).thenReturn(500.0);
        cliente.addVeiculo(veiculoMock);
        cliente.addVeiculo(veiculoMock);
        assertEquals(1000.0, cliente.getValorTotalArrecadado());
    }

    @Test
    void testGetValorArrecadadoNoMes() {
        Mockito.when(veiculoMock.arrecadadoNoMes(1)).thenReturn(200.0);
        cliente.addVeiculo(veiculoMock);
        assertEquals(200.0, cliente.getValorArrecadadoNoMes(1));
    }

    @Test
    void testSettersAndGetters() {
        cliente.setNome("Novo Nome");
        assertEquals("Novo Nome", cliente.getNome());

        cliente.setId("456");
        assertEquals("456", cliente.getId());

        Plano outroPlano = Mockito.mock(Plano.class);
        cliente.setPlano(outroPlano);
        assertEquals(outroPlano, cliente.getPlano());
    }
}
