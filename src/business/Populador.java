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

/**
 * Classe Populador para gerar dados iniciais do sistema de estacionamento.
 * Gera estacionamentos, clientes e veículos com uso pré-definido.
 */
public class Populador {

    /**
     * Gera e retorna uma lista de estacionamentos com clientes e veículos pré-definidos.
     * Inclui a lógica para relatórios de cada estacionamento.
     *
     * @return Uma lista de estacionamentos pré-definidos.
     */
    public static List<Estacionamento> popularEstacionamentos() {
        List<Estacionamento> estacionamentos = new ArrayList<>();

        // Criação dos estacionamentos pré-definidos
        Estacionamento estacionamento1 = new Estacionamento("Estacionamento A", 5, 10, 1);
        Estacionamento estacionamento2 = new Estacionamento("Estacionamento B", 4, 8, 2);
        Estacionamento estacionamento3 = new Estacionamento("Estacionamento C", 6, 12, 3);

        estacionamentos.add(estacionamento1);
        estacionamentos.add(estacionamento2);
        estacionamentos.add(estacionamento3);

        for (Estacionamento estacionamento : estacionamentos) {
            Veiculo veiculoInicial = new Veiculo("STARTER");
            Relatorio relatorioEstacionamento = new Relatorio(estacionamento, veiculoInicial);

            for (int i = 1; i <= 3; i++) {
                Plano plano = selecionarPlano(i);
                Cliente cliente = new Cliente("Cliente" + i + estacionamento.getNome(), i + estacionamento.getNome(), plano);
                
                Veiculo veiculo = criarVeiculo();
                cliente.addVeiculo(veiculo);

                relatorioEstacionamento.setObservavel(veiculo);

                estacionamento.estacionar(veiculo);
                estacionamento.sair(veiculo, plano);

                estacionamento.addCliente(cliente);
            }

            estacionamento.setRelatorio(relatorioEstacionamento);
        }

        return estacionamentos;
    }

    /**
     * Seleciona um plano com base em um índice.
     *
     * @param index Índice para selecionar o plano.
     * @return O plano selecionado.
     */
    private static Plano selecionarPlano(int index) {
        switch (index) {
            case 1:
                return new Mensalista("Mensalista");
            case 2:
                return new Turnista("Turnista", Turnos.MANHA);
            case 3:
                return new Horista("Horista");
            default:
                throw new IllegalArgumentException("Índice de plano inválido.");
        }
    }

    /**
     * Cria um veículo com uma placa aleatória.
     *
     * @return Um novo veículo com placa aleatória.
     */
    private static Veiculo criarVeiculo() {
        return new Veiculo(gerarPlacaAleatoria());
    }

    /**
     * Gera uma placa de veículo aleatória.
     *
     * @return Uma placa de veículo aleatória.
     */
    private static String gerarPlacaAleatoria() {
        Random random = new Random();
        StringBuilder placa = new StringBuilder();

        // Gera três letras aleatórias
        for (int i = 0; i < 3; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            placa.append(randomChar);
        }

        // Gera quatro dígitos aleatórios
        for (int i = 0; i < 4; i++) {
            int randomDigit = random.nextInt(10);
            placa.append(randomDigit);
        }

        return placa.toString();
    }
}
