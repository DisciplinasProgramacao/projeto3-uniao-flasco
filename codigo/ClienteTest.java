import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("João", "123");
    }

    @Test
    void testAddVeiculo() {
        Veiculo veiculo = new Veiculo("XYZ-1234");
        cliente.addVeiculo(veiculo);
        assertNotNull(cliente.possuiVeiculo("XYZ-1234"));
    }

    @Test
    void testPossuiVeiculo() {
        Veiculo veiculo = new Veiculo("XYZ-1234");
        cliente.addVeiculo(veiculo);
        assertEquals(veiculo, cliente.possuiVeiculo("XYZ-1234"));
        assertNull(cliente.possuiVeiculo("ABC-1234"));
    }

    @Test
    void testTotalDeUsos() {
        Veiculo veiculo1 = new Veiculo("XYZ-1234");
        Veiculo veiculo2 = new Veiculo("ABC-5678");
        //TODO totalDeUsos, Por enquanto o método retorna 0.
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(0, cliente.totalDeUsos());
    }
}
