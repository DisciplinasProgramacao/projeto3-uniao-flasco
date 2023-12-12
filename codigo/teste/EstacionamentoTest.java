package teste;

import business.Cliente.Cliente;
import business.Plano.Plano;
import business.Vaga.Vaga;
import business.Veiculo.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente clienteMock;
    private Veiculo veiculoMock;
    private Plano planoMock;
    private Vaga vagaMock;

    @BeforeEach
    void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 3, 10, 1);
        clienteMock = Mockito.mock(Cliente.class);
        veiculoMock = Mockito.mock(Veiculo.class);
        planoMock = Mockito.mock(Plano.class);
        vagaMock = Mockito.mock(Vaga.class);
    }

    @Test
    void testAdicionarEObterClientes() {
        estacionamento.addCliente(clienteMock);
        assertEquals(1, estacionamento.getAllClientes().size(), "Deve ter 1 cliente adicionado");
    }

    @Test
    void testClienteExiste() {
        Mockito.when(clienteMock.getId()).thenReturn("123");
        estacionamento.addCliente(clienteMock);
        assertTrue(estacionamento.clienteExiste("123"), "O cliente com ID '123' deve existir");
        assertFalse(estacionamento.clienteExiste("999"), "O cliente com ID '999' não deve existir");
    }

    @Test
    void testAdicionarVeiculoACliente() {
        Mockito.when(clienteMock.getId()).thenReturn("123");
        estacionamento.addCliente(clienteMock);
        estacionamento.addVeiculo(veiculoMock, "123");
        Mockito.verify(clienteMock).addVeiculo(veiculoMock);
    }

    @Test
    void testEstacionarVeiculo() {
        Mockito.when(vagaMock.isDisponivel()).thenReturn(true);
        estacionamento.estacionar(veiculoMock);
        Mockito.verify(veiculoMock).estacionar(Mockito.any(Vaga.class));
    }

    @Test
    void testSairDoEstacionamento() {
        Mockito.when(veiculoMock.sair(planoMock)).thenReturn(100.0);
        double valorPago = estacionamento.sair(veiculoMock, planoMock);
        assertEquals(100.0, valorPago, "O valor pago deve ser 100.0");
    }

    @Test
    void testTotalArrecadado() {
        Mockito.when(clienteMock.getValorTotalArrecadado()).thenReturn(200.0);
        estacionamento.addCliente(clienteMock);
        assertEquals(200.0, estacionamento.totalArrecadado(), "O total arrecadado deve ser 200.0");
    }

    @Test
    void testArrecadadoNoMes() {
        Mockito.when(clienteMock.getValorArrecadadoNoMes(5)).thenReturn(150.0);
        estacionamento.addCliente(clienteMock);
        assertEquals(150.0, estacionamento.arrecadadoNoMes(5), "O valor arrecadado no mês 5 deve ser 150.0");
    }

    @Test
    void testVeiculoExiste() {
        Mockito.when(clienteMock.possuiVeiculo("ABC123")).thenReturn(true);
        estacionamento.addCliente(clienteMock);
        assertTrue(estacionamento.VeiculoExiste("ABC123"), "O veículo com placa 'ABC123' deve existir");
        assertFalse(estacionamento.VeiculoExiste("XYZ789"), "O veículo com placa 'XYZ789' não deve existir");
    }
}
