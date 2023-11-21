package teste;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import business.Cliente.Cliente;
import business.Plano.Plano;
import business.Veiculo.Veiculo;

public class ClienteTest {

    private Cliente cliente;
    private Plano plano;
    private Veiculo veiculo;

    @Before
    public void setUp() {
        cliente = new Cliente("João", "123", plano);
        veiculo = new Veiculo("ABC1234");
    }

    @Test
    public void testClienteCreation() {
        assertEquals("João", cliente.getNome());
        assertEquals("123", cliente.getId());
        assertEquals(plano, cliente.getPlano());
    }

    @Test
    public void testSetNome() {
        cliente.setNome("Maria");
        assertEquals("Maria", cliente.getNome());
    }

    @Test
    public void testAddVeiculo() {
        cliente.addVeiculo(veiculo);
        assertTrue(cliente.getVeiculos().contains(veiculo));
    }

    @Test
    public void testPossuiVeiculo() {
        cliente.addVeiculo(veiculo);
        assertTrue(cliente.possuiVeiculo("ABC1234"));
        assertFalse(cliente.possuiVeiculo("XYZ5678"));
    }
}
