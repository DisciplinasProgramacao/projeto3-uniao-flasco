package teste;

import business.Estacionamento.Estacionamento;
import business.Cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AplicacaoTest {

    private List<Estacionamento> estacionamentos;
    private Scanner scannerMock;
    private GenericDAO daoMock;

    @BeforeEach
    void setUp() {
        estacionamentos = new ArrayList<>();
        scannerMock = Mockito.mock(Scanner.class);
        daoMock = Mockito.mock(GenericDAO.class);
        Aplicacao.setEstacionamentos(estacionamentos);
    }

    @Test
    void testCadastrarCliente() {
        Mockito.when(scannerMock.nextLine()).thenReturn("Nome do Cliente", "ID123");
        Mockito.when(scannerMock.nextInt()).thenReturn(1); 
        Aplicacao.cadastrarCliente(scannerMock);
        assertFalse(estacionamentos.isEmpty());
        Estacionamento estacionamento = estacionamentos.get(0);
        Cliente cliente = estacionamento.getAllClientes().get(0);
        assertEquals("Nome do Cliente", cliente.getNome());
        assertEquals("ID123", cliente.getId());
    }

   @Test
void testAdicionarVeiculo() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Cliente clienteMock = Mockito.mock(Cliente.class);
    estacionamentos.add(estacionamentoMock);
    Mockito.when(scannerMock.nextLine()).thenReturn("PLACA123", "ID123");
    Mockito.when(estacionamentoMock.VeiculoExiste("PLACA123")).thenReturn(false);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(List.of(clienteMock));
    Mockito.when(clienteMock.getId()).thenReturn("ID123");
    Aplicacao.adicionarVeiculo(scannerMock);
    Mockito.verify(clienteMock).addVeiculo(Mockito.any(Veiculo.class));
  }

  @Test
void testEstacionar() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Veiculo veiculoMock = Mockito.mock(Veiculo.class);
    estacionamentos.add(estacionamentoMock);
    Mockito.when(scannerMock.nextLine()).thenReturn("PLACA123");
    Mockito.when(estacionamentoMock.VeiculoExiste("PLACA123")).thenReturn(true);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(new ArrayList<>()); 
    Mockito.when(veiculoMock.isEstacionado()).thenReturn(false);
    Aplicacao.estacionar(scannerMock);
    Mockito.verify(veiculoMock).estacionar(Mockito.any(Vaga.class));
  }

@Test
void testSairDoEstacionamento() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Veiculo veiculoMock = Mockito.mock(Veiculo.class);
    estacionamentos.add(estacionamentoMock);
    Mockito.when(scannerMock.nextLine()).thenReturn("PLACA123");
    Mockito.when(estacionamentoMock.VeiculoExiste("PLACA123")).thenReturn(true);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(new ArrayList<>()); 
    Mockito.when(veiculoMock.isEstacionado()).thenReturn(true);
    Aplicacao.sairDoEstacionamento(scannerMock);
    Mockito.verify(estacionamentoMock).sair(veiculoMock, Mockito.any(Plano.class));
  }

  @Test
void testServicosAdicionais() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Veiculo veiculoMock = Mockito.mock(Veiculo.class);
    UsoDeVaga usoDeVagaMock = Mockito.mock(UsoDeVaga.class);
    LinkedList<UsoDeVaga> usosMock = new LinkedList<>();
    usosMock.add(usoDeVagaMock);
    estacionamentos.add(estacionamentoMock);
    Mockito.when(scannerMock.nextLine()).thenReturn("PLACA123");
    Mockito.when(estacionamentoMock.VeiculoExiste("PLACA123")).thenReturn(true);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(new ArrayList<>()); 
    Mockito.when(veiculoMock.getUsos()).thenReturn(usosMock);
    Mockito.when(scannerMock.nextInt()).thenReturn(1); 
    Aplicacao.servicosAdicionais(scannerMock);
    Mockito.verify(usoDeVagaMock).adicionarServico(Mockito.any(UsoDeVaga.ServicoAdicional.class));
  }

@Test
void testGerarRelatorioDoCliente() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Cliente clienteMock = Mockito.mock(Cliente.class);
    estacionamentos.add(estacionamentoMock);
    Mockito.when(scannerMock.nextLine()).thenReturn("ID123");
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(List.of(clienteMock));
    Mockito.when(clienteMock.getId()).thenReturn("ID123");
    Aplicacao.gerarRelatorioDoCliente(scannerMock);
    Mockito.verify(clienteMock).getNome();
    Mockito.verify(clienteMock).getVeiculos();
  }

@Test
void testGerarRelatorioUsoDeVaga() throws ExcecaoGeral {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Veiculo veiculoMock = Mockito.mock(Veiculo.class);
    estacionamentos.add(estacionamentoMock);
    Mockito.when(estacionamentoMock.VeiculoExiste("PLACA123")).thenReturn(true);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(new ArrayList<>()); 
    Aplicacao.gerarRelatorioUsoDeVaga("PLACA123");
    Mockito.verify(veiculoMock).relatorioDeUsoDeVagasVeiculo();
  }

@Test
void testArrecadacaoMediaHoristas() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Cliente clienteMock = Mockito.mock(Cliente.class);
    Mockito.when(clienteMock.getPlano()).thenReturn(new Horista());
    estacionamentos.add(estacionamentoMock);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(List.of(clienteMock));
    Mockito.when(scannerMock.nextInt()).thenReturn(1); 
    Aplicacao.arrecadacaoMediaHoristas(scannerMock);
  }

@Test
void testMediaUsoMensalistas() {
    Estacionamento estacionamentoMock = Mockito.mock(Estacionamento.class);
    Cliente clienteMock = Mockito.mock(Cliente.class);
    Mockito.when(clienteMock.getPlano()).thenReturn(new Mensalista());
    estacionamentos.add(estacionamentoMock);
    Mockito.when(estacionamentoMock.getAllClientes()).thenReturn(List.of(clienteMock));
    Mockito.when(scannerMock.nextInt()).thenReturn(1);
    Aplicacao.mediaUsoMensalistas(scannerMock);
  }
}
