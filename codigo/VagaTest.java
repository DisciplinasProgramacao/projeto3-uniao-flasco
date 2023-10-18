import java.beans.Transient;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class VagaTest {
   public static Vaga vaga;
    @BeforeEach
    public void setup()
    {
        int fila =3;
        int numero = 9;
       vaga = new Vaga(fila,numero);
    }
    @Test
    public void testEstacionar()
    {
        vaga.setDisponivel(true);
        assertTrue(vaga.estacionar());
    }    
    @Test
    public void testSair()
    {
        vaga.setDisponivel(false);
        assertTrue(vaga.sair());
    }
    @Test
    public void testDisponivel()
    {
        vaga.setDisponivel(true);
        assertTrue(vaga.disponivel());
    }
}
