package teste;

import business.Plano.Mensalista;
import business.Plano.Turnista;
import business.Vaga.Vaga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UsoDeVagaTest {

    private UsoDeVaga usoDeVaga;
    private Vaga vagaMock;
    private LocalDateTime entrada;
    private Turnista planoTurnista;
    private Mensalista planoMensalista;

    @BeforeEach
    void setUp() {
        vagaMock = new Vaga(1, 1);
        entrada = LocalDateTime.now();
        usoDeVaga = new UsoDeVaga(vagaMock, entrada);
        planoTurnista = new Turnista();
        planoMensalista = new Mensalista();
    }

    @Test
    void testAdicionarServico() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.LAVAGEM);
        assertEquals(1, usoDeVaga.getServicosAdicionais().size());
    }

    @Test
    void testSairComPlanoMensalista() {
        double valor = usoDeVaga.sair(entrada.plusHours(2), planoMensalista);
        assertEquals(0, valor, "O valor deve ser 0 para mensalistas");
    }

    @Test
    void testSairComPlanoTurnista() {
        double valor = usoDeVaga.sair(entrada.plusHours(1), planoTurnista);
    }

    @Test
    void testValorPagoMaximo() {
        usoDeVaga.setSaida(entrada.plusHours(15)); 
        double valor = usoDeVaga.valorPago();
        assertEquals(UsoDeVaga.VALOR_MAXIMO, valor, "O valor deve ser igual ao valor máximo");
    }

    @Test
    void testValorPagoComServicoAdicionalInvalido() {
        usoDeVaga.adicionarServico(UsoDeVaga.ServicoAdicional.LAVAGEM);
        usoDeVaga.setSaida(entrada.plusMinutes(30)); 
        assertThrows(IllegalArgumentException.class, usoDeVaga::valorPago);
    }
}
