import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VeiculoTest {
    private Veiculo veiculo;
    private List<UsoDeVaga> usos;

    @BeforeEach
    public void setUp() {
        veiculo = new Veiculo("XYZ123"); 
        usos = new ArrayList<>();
        veiculo.setUsos(usos);
    }

    public Vaga criavaga(){
        Vaga vaga = new Vaga('A', 1);
        return vaga;
    }

    @Test
    public void testEstacionar() {
        Vaga vaga = new Vaga('A', 1);

        veiculo.estacionar(vaga);

        assertEquals(1, usos.size());
        assertFalse(vaga.isDisponivel());
    }

    @Test
    public void testEstacionarComVagaIndisponivel() {
        Vaga vaga = new Vaga('B', 1);
        vaga.setDisponivel(false);

        assertThrows(IllegalArgumentException.class, () -> veiculo.estacionar(vaga));
    }

    @Test
    public void testSair() {
        Vaga vaga = new Vaga('A', 1);
        UsoDeVaga uso = new UsoDeVaga(vaga, LocalDateTime.now());
        usos.add(uso);

        double valor = veiculo.sair(vaga);
        assertTrue(valor >= 0.0);
        assertTrue(usos.isEmpty());
        assertTrue(vaga.isDisponivel());
    }

    @Test
    public void testTotalArrecadado() {
        Vaga vaga1 = new Vaga('A', 1);
        Vaga vaga2 = new Vaga('B', 1);
        UsoDeVaga uso1 = new UsoDeVaga(vaga1, LocalDateTime.now());
        UsoDeVaga uso2 = new UsoDeVaga(vaga2, LocalDateTime.now());
        uso1.setValorPago(20.0);
        uso2.setValorPago(30.0);
        usos.add(uso1);
        usos.add(uso2);

        double totalArrecadado = veiculo.totalArrecadado();
        assertEquals(50.0, totalArrecadado);
    }

    @Test
    public void testArrecadadoNoMes() {
        Vaga vaga1 = new Vaga('A', 1);
        Vaga vaga2 = new Vaga('B', 1);
        UsoDeVaga uso1 = new UsoDeVaga(vaga1, LocalDateTime.now());
        UsoDeVaga uso2 = new UsoDeVaga(vaga2, LocalDateTime.now());
        uso1.setValorPago(20.0);
        uso2.setValorPago(30.0);
        usos.add(uso1);
        usos.add(uso2);

        int mes = LocalDateTime.now().getMonthValue(); 
        double arrecadadoNoMes = veiculo.arrecadadoNoMes(mes);
        assertEquals(50.0, arrecadadoNoMes);
    }

    @Test
    public void testTotalDeUsos() {
        Vaga vaga1 = new Vaga('A', 1);
        Vaga vaga2 = new Vaga('B', 1);
        UsoDeVaga uso1 = new UsoDeVaga(vaga1, LocalDateTime.now());
        UsoDeVaga uso2 = new UsoDeVaga(vaga2, LocalDateTime.now());
        usos.add(uso1);
        usos.add(uso2);

        int totalDeUsos = veiculo.totalDeUsos();
        assertEquals(2, totalDeUsos);
    }
}
