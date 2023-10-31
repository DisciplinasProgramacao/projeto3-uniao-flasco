package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import business.Estacionamento;
import junit.framework.*;


public class EstacionamentoTest {

@Test
public void gerarVagasTeste(){
    Estacionamento estacionamento = new Estacionamento(null, 10, 10);
    assertEquals(estacionamento.getVagas(), 100);

}

@Test
public void addClienteTeste(){
    Estacionamento estacionamento = new Estacionamento(null, 10, 10);
    int antes = estacionamento.getClientes().size();
    estacionamento.addCliente(null);
    assertEquals(antes+=1, estacionamento.getClientes().size());
}

@Test
public void estacionarTest(){
    Estacionamento estacionamento = new Estacionamento(null, 10, 10);
    int antes = estacionamento.getUsos().size();
    estacionamento.estacionar(null);
    assertEquals(antes+=1, estacionamento.getUsos().size());
}

@Test
public void sairTest(){
    Estacionamento estacionamento = new Estacionamento(null, 10, 10);
    estacionamento.sair("1111-1111");
    assertEquals(antes+=1, estacionamento.getUsos().size());
}


}
