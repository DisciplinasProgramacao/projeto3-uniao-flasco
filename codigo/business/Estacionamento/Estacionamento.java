package business.Estacionamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import business.Cliente.Cliente;
import business.Plano.Plano;
import business.Vaga.Vaga;
import business.Veiculo.Veiculo;

public class Estacionamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int id;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private Relatorio relatorio;

    /**
     * Construtor que inicializa um estacionamento com nome, quantidade de fileiras, quantidade de vagas por fileira e ID.
     *
     * @param nome            O nome do estacionamento.
     * @param fileiras        A quantidade de fileiras de vagas.
     * @param vagasPorFila    A quantidade de vagas por fileira.
     * @param id              O ID do estacionamento.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila, int id) {
        this.nome = nome;
        this.id = id;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        
        this.vagas = new ArrayList<>();
        gerarVagas();
       
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    /**
     * Retorna o nome do estacionamento.
     *
     * @return O nome do estacionamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do estacionamento.
     *
     * @param nome O nome do estacionamento.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o ID do estacionamento.
     *
     * @return O ID do estacionamento.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do estacionamento.
     *
     * @param id O ID do estacionamento.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gera as vagas do estacionamento com base na quantidade de fileiras e vagas por fileira.
     */
    private void gerarVagas() {
        for (int i = 1; i <= quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas.add(new Vaga(i, j));
            }
        }
    }

    /**
     * Adiciona um veículo a um cliente com base no ID do cliente.
     *
     * @param veiculo O veículo a ser adicionado.
     * @param idCli   O ID do cliente.
     */
    public void addVeiculo(Veiculo veiculo, String idCli) {

        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                return;
            }
        }
        relatorio.setObservavel(veiculo);
        System.out.println("Cliente não encontrado!");
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente O cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
        }
    }

    /**
     * Adiciona uma lista de clientes ao estacionamento.
     *
     * @param clientes A lista de clientes a serem adicionados.
     */
    public void addAllClientes(List<Cliente> clientes) {
        this.clientes.addAll(clientes);
    }


    /**
 * Realiza a operação de estacionar um veículo em uma vaga disponível no estacionamento.
 *
 * @param veiculo O veículo a ser estacionado.
 * @throws RuntimeException Se não houver vaga disponível para estacionar o veículo.
 */
public void estacionar (Veiculo veiculo){
    Vaga vaga = vagas.stream().filter(v -> v.isDisponivel())
                      .findFirst()
                      .orElseThrow(() -> new RuntimeException("Vaga não disponivel"));

                      veiculo.estacionar(vaga);
        
        
    }

   /**
 * Realiza a operação de saída de um veículo do estacionamento e calcula o valor a ser pago com base no plano associado ao cliente.
 *
 * @param veiculo O veículo a ser retirado do estacionamento.
 * @param plano   O plano associado ao cliente para cálculo do valor a ser pago.
 * @return O valor a ser pago pela saída do veículo.
 */
    public double sair(Veiculo veiculo,Plano plano) {
        double valor = veiculo.sair(plano);
        return valor;
    }

    /**
     * Calcula o valor total arrecadado pelo estacionamento com base nas operações dos clientes.
     *
     * @return O valor total arrecadado pelo estacionamento.
     */
    public double totalArrecadado() {
        return clientes.stream()
                .mapToDouble(Cliente::getValorTotalArrecadado)
                .sum();
    }

    /**
     * Calcula o valor total arrecadado pelo estacionamento em um mês específico.
     *
     * @param mes O mês a ser consultado.
     * @return O valor total arrecadado pelo estacionamento no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        return clientes.stream()
                .mapToDouble(cliente -> cliente.getValorArrecadadoNoMes(mes))
                .sum();
    }

    /**
     * Calcula o valor médio por uso de todos os clientes do estacionamento.
     *
     * @return O valor médio por uso dos clientes do estacionamento.
     */
    public double valorMedioPorUso() {
        
        return clientes.stream()
                .filter(c -> c.getValorTotalArrecadado() != 0)
                .mapToDouble(cliente -> cliente.getValorTotalArrecadado() / cliente.getTotalUsos())
                .average()
                .orElse(0.0);
    }

 /**
 * Retorna um conjunto dos top 5 clientes que mais arrecadaram no mês especificado.
 *
 * @param mes O mês a ser consultado.
 * @return Um conjunto dos top 5 clientes que mais arrecadaram no mês.
 */
public Set<Cliente> top5Clientes(MesEnum mes) {
    return clientes.stream()
            .sorted(Comparator.comparingDouble(c -> ((Cliente) c).getValorArrecadadoNoMes(mes.getMes())).reversed())
            .limit(5)
            .collect(Collectors.toSet());
}


    /**
 * Verifica se um veículo com a placa especificada existe em algum dos clientes do estacionamento.
 *
 * @param placa A placa do veículo a ser verificada.
 * @return true se o veículo com a placa fornecida existe em algum cliente, caso contrário, false.
 */
    public boolean VeiculoExiste(String placa){
        for (Cliente cliente : clientes) {
            if (cliente.possuiVeiculo(placa) == true) {
                return true;
            }
        }
        return false;
        
    }

    /**
     * Retorna a lista de todos os clientes do estacionamento.
     *
     * @return A lista de clientes do estacionamento.
     */
    public List<Cliente> getAllClientes() {
        return clientes;
    }
   
/**
 * Verifica se um cliente com o ID fornecido já existe no estacionamento.
 *
 * @param idCliente O ID do cliente a ser verificado.
 * @return true se o cliente com o ID fornecido existir, false caso contrário.
 */
public boolean clienteExiste(String idCliente) {
    return getAllClientes().stream().anyMatch(cliente -> cliente.getId().equals(idCliente));
}

}
