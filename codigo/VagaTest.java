import java.beans.Transient;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;

public class VagaTest {
   public static Vaga vaga;
    @BeforeEach
    public void setup()
    {
        char fila = 'A';
        int numero = 9;
        vaga = new Vaga(fila,numero);
        vaga.setDisponivel(true);
    }
    @Test
    public void testEstacionar()
    {
        assertTrue(vaga.estacionar());
    }    
    @Test
    public void testSair()
    {
        
        assertFalse(vaga.sair());
    }
    @Test
    public void testDisponivel()
    {
        assertTrue(vaga.disponivel());
    }
}
