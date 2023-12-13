package business;

import business.Cliente.Cliente;
import business.Estacionamento.Estacionamento;
import business.Estacionamento.Relatorio;
import business.Plano.Horista;
import business.Plano.Mensalista;
import business.Plano.Plano;
import business.Plano.Turnista;
import business.Plano.Turnos;
import business.Veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Classe para retornar Objetos pré-prontos do sistema, sendo eles:
   * - 3 Estacionamentos
   * - 9 Clientes (3 para cada plano, cada estacionamento possui 3 clientes)
   * - 9 Veiculos (um para cada cliente)
*/
public class Populador {

     /**
     * Método para popular a lista de estacionamentos com dados pré-definidos.
     *
     * @return Uma lista contendo estacionamentos pré-definidos.
     */
    public static List<Estacionamento> popularEstacionamentos() {
        List<Estacionamento> estacionamentos = new ArrayList<>();

        // Criação dos estacionamentos pré-definidos
        Estacionamento estacionamento1 = new Estacionamento("Estacionamento A", 5, 10, 1);
        Estacionamento estacionamento2 = new Estacionamento("Estacionamento B", 4, 8, 2);
        Estacionamento estacionamento3 = new Estacionamento("Estacionamento C", 6, 12, 3);

        // Adição dos estacionamentos à lista
        estacionamentos.add(estacionamento1);
        estacionamentos.add(estacionamento2);
        estacionamentos.add(estacionamento3);

        for (Estacionamento estacionamento : estacionamentos) {
            for (int i = 1; i <= 3; i++) {
                Plano plano = null;
                Veiculo veiculo = null; // Inicializa a variável veiculo
        
                switch (i) {
                    case 1:
                        plano = new Mensalista("Mensalista");
                        break;
                    case 2:
                        plano = new Turnista("Turnista", Turnos.MANHA);
                        break;
                    case 3:
                        plano = new Horista("Horista");
                        break;
                }
        
                Cliente cliente = new Cliente("Cliente" + i + estacionamento.getNome(), i + estacionamento.getNome(), plano);
        
                // Cria o veículo antes de associá-lo ao cliente
                veiculo = criarVeiculo();
                cliente.addVeiculo(veiculo);
        
                // Cria o relatório após a criação do veículo
                Relatorio relatorioEstacionamento = new Relatorio(estacionamento, veiculo);
                estacionamento.setRelatorio(relatorioEstacionamento);
        
                estacionamento.addCliente(cliente);
                estacionamento.estacionar(veiculo);
            }
        }

        return estacionamentos;
    }

    private static Veiculo criarVeiculo() {
        return new Veiculo(gerarPlacaAleatoria());
    }

    /**
     * Método privado para gerar uma placa de veículo aleatória.
     *
     * @return Uma placa de veículo aleatória gerada.
     */
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
