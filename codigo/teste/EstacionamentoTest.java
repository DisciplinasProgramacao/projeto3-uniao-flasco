package teste;

import business.Plano.Horista;
import business.Plano.Mensalista;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import business.Estacionamento.Estacionamento;
import business.Cliente.Cliente;
import business.Plano.Plano;
import business.Veiculo.Veiculo;
import business.Plano.Mensalista;;

class EstacionamentoTest {

    private Estacionamento estacionamento;

    @BeforeEach
    void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 5, 10, 1);
    }

    @Test
    void testAddVeiculo(){
        Veiculo veic = new Veiculo("ABC1234");
        Cliente cliente = new Cliente("Teste", "1", null);
        estacionamento.addCliente(cliente);

        estacionamento.addVeiculo(veic, "1");

        assertEquals(cliente.getVeiculos().size(), 1);
    }

    @Test 
    void testEstacionar(){
        Veiculo veiculo = new Veiculo("ABC1234");
        estacionamento.estacionar(veiculo);
        assertTrue(veiculo.isEstacionado());
    }

    @Test
    void sair(){
        Veiculo veiculo = new Veiculo("ABC1234");
        Mensalista mensalista = new Mensalista("Mensalista");
        estacionamento.estacionar(veiculo);
        
        assertEquals(estacionamento.sair(veiculo, mensalista),0);
    }
    @Test
    void testTotalArrecadado(){
        Cliente cliente = new Cliente(null, null, null);
        estacionamento.addCliente(cliente);
        assertEquals(estacionamento.totalArrecadado(),0);
    }
    @Test
    void testArrecadadoNoMes(){
        Cliente cliente = new Cliente(null, null, null);
        estacionamento.addCliente(cliente);
        assertEquals(estacionamento.arrecadadoNoMes(1),0);
    }
}
