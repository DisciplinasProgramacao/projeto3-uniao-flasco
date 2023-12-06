package business;

import business.Cliente.Cliente;
import business.Estacionamento.Estacionamento;
import business.Plano.Horista;
import business.Plano.Mensalista;
import business.Plano.Plano;
import business.Plano.Turnista;
import business.Plano.Turnos;
import business.Veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*Classe para retornar Objetos pré-prontos do sistema, sendo eles:
    - 3 Estacionamentos
    - 9 Clientes (3 para cada plano, cada estacionamento possui 3 clientes)
    - 9 Veiculos (um para cada cliente)
*/
public class Populador {

    public static List<Estacionamento> popularEstacionamentos() {
        List<Estacionamento> estacionamentos = new ArrayList<>();

        
        Estacionamento estacionamento1 = new Estacionamento("Estacionamento A", 5, 10, 1);
        Estacionamento estacionamento2 = new Estacionamento("Estacionamento B", 4, 8, 2);
        Estacionamento estacionamento3 = new Estacionamento("Estacionamento C", 6, 12, 3);

        
        estacionamentos.add(estacionamento1);
        estacionamentos.add(estacionamento2);
        estacionamentos.add(estacionamento3);

        
        estacionamento1.addCliente(criarCliente("Cliente1A", "1A", new Mensalista("Mensalista")));
        estacionamento1.addCliente(criarCliente("Cliente2A", "2A", new Turnista("Turnista", Turnos.MANHA)));

        
        estacionamento2.addCliente(criarCliente("Cliente1B", "1B", new Horista("Horista")));
        estacionamento2.addCliente(criarCliente("Cliente2B", "2B", new Mensalista("Mensalista")));

        
        estacionamento3.addCliente(criarCliente("Cliente1C", "1C", new Turnista("Turnista", Turnos.TARDE)));
        estacionamento3.addCliente(criarCliente("Cliente2C", "2C", new Horista("Horista")));

        
        for (Estacionamento estacionamento : estacionamentos) {
            for (Cliente cliente : estacionamento.getAllClientes()) {
                Veiculo veiculo = new Veiculo("ABC123");
                cliente.addVeiculo(veiculo);
                estacionamento.estacionar(veiculo); // Estaciona o veículo ao criar
            }
        }

        return estacionamentos;
    }

    private static Cliente criarCliente(String nome, String id, Plano plano) {
        Cliente cliente = new Cliente(nome, id, plano);
        Veiculo veiculo = new Veiculo(gerarPlacaAleatoria());
        cliente.addVeiculo(veiculo);
        return cliente;
    }

    private static String gerarPlacaAleatoria() {
        Random random = new Random();
        StringBuilder placa = new StringBuilder();

        
        for (int i = 0; i < 3; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            placa.append(randomChar);
        }

       
        for (int i = 0; i < 4; i++) {
            int randomDigit = random.nextInt(10);
            placa.append(randomDigit);
        }

        return placa.toString();
    }
}
