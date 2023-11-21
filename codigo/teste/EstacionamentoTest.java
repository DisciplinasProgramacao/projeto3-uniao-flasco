package teste;

import business.Plano.Horista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import business.Estacionamento.Estacionamento;
import business.Cliente.Cliente;
import business.Plano.Plano;

class EstacionamentoTest {

    private Estacionamento estacionamento;

    @BeforeEach
    void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 5, 10, 1);
    }

    /* @todo */
}
