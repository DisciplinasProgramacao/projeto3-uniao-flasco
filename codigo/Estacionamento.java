import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe que representa um estacionamento.
 */
public class Estacionamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private List<UsoDeVaga> usos;

    /**
     * Construtor para criar uma instância de Estacionamento.
     *
     * @param nome          nome do estacionamento
     * @param fileiras      número total de fileiras no estacionamento
     * @param vagasPorFila  número de vagas por fileira
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.usos = new ArrayList<>();
        this.vagas = new ArrayList<>();
        gerarVagas();
    }

    /**
     * Método para gerar vagas no estacionamento.
     */
    private void gerarVagas() {
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas.add(new Vaga(i, j));
            }
        }
    }

    /**
     * Método para adicionar um veículo a um cliente.
     *
     * @param veiculo  veículo a ser adicionado
     * @param idCli    identificação do cliente
     */
    public void addVeiculo(Veiculo veiculo, String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    /**
     * Método para adicionar um cliente ao estacionamento.
     *
     * @param cliente  cliente a ser adicionado
     */
    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Método para estacionar um veículo.
     *
     * @param placa  placa do veículo a ser estacionado
     */
    public void estacionar(String placa) {
        for (Vaga vaga : vagas) {
            if (vaga.estacionar()) {
                usos.add(new UsoDeVaga(vaga, LocalDateTime.now(), null, 0.0));
                return;
            }
        }
        System.out.println("Não há vagas disponíveis");
    }

    /**
     * Método para registrar a saída de um veículo do estacionamento.
     *
     * @param placa  placa do veículo que está saindo
     * @return       valor a ser pago
     */
    public double sair(String placa) {
        for (UsoDeVaga uso : usos) {
            if (uso.getVaga().getVeiculoEstacionado().equals(placa) && uso.getSaida() == null) {
                uso.setSaida(LocalDateTime.now());
                double valor = uso.valorPago();
                uso.setValorPago(valor);
                uso.getVaga().sair();
                return valor;
            }
        }
        return 0.0;
    }

    /**
     * Método para calcular o total arrecadado pelo estacionamento.
     *
     * @return  valor total arrecadado
     */
    public double totalArrecadado() {
        return usos.stream().mapToDouble(UsoDeVaga::valorPago).sum();
    }

    /**
     * Método para calcular o total arrecadado em um determinado mês.
     *
     * @param mes  mês desejado
     * @return     valor total arrecadado no mês
     */
    public double arrecadadoNoMes(int mes) {
        return usos.stream()
                .filter(u -> u.getEntrada().getMonthValue() == mes)
                .mapToDouble(UsoDeVaga::valorPago)
                .sum();
    }

    /**
     * Método para calcular o valor médio pago por uso do estacionamento.
     *
     * @return  valor médio por uso
     */
    public double valorMedioPorUso() {
        return totalArrecadado() / usos.size();
    }

    /**
     * Método para listar os 5 clientes que mais geraram receita em um determinado mês.
     *
     * @param mes  mês desejado
     * @return      lista com o nome dos 5 principais clientes
     */
    public List<String> top5Clientes(int mes) {
        Map<Cliente, Double> mapClientes = new HashMap<>();

        for (Cliente cliente : clientes) {
            mapClientes.put(cliente, cliente.arrecadadoNoMes(mes));
        }

        return mapClientes.entrySet().stream()
                .sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed())
                .limit(5)
                .map(e -> e.getKey().getNome())
                .collect(Collectors.toList());
    }

}
