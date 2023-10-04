import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Estacionamento {

    	private String nome;
    	private List<Cliente> id = new ArrayList<>();
    	private List<Vaga> vagas = new ArrayList<>();
    	private int quantFileiras;
    	private int vagasPorFileira;

	/**
     	* Constrói um estacionamento com nome, número de fileiras e vagas por fileira especificados.
     	*
     	* @param nome          O nome do estacionamento.
     	* @param fileiras      Número de fileiras de vagas.
     	* @param vagasPorFila  Número de vagas por fileira.
     	*/
	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
	this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        gerarVagas();
	}

	/**
     	* Adiciona um veículo ao cliente especificado por seu ID.
     	*
     	* @param veiculo  O veículo a ser adicionado.
     	* @param idCli    O ID do cliente ao qual o veículo será adicionado.
     	*/
	 public void addVeiculo(Veiculo veiculo, String idCli) {
        for (Cliente cliente : id) {
            if (cliente.getId().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                return;
            }
        }
         // TODO: Adicionar tratamento caso cliente não seja encontrado.
    }

	/**
     	* Adiciona um cliente à lista de clientes do estacionamento.
     	*
     	* @param cliente  O cliente a ser adicionado.
     	*/
	public void addCliente(Cliente cliente) {
	id.add(cliente);
	}

	/**
     	* Gera vagas para o estacionamento com base nas fileiras e vagas por fileira especificadas.
     	*/
	private void gerarVagas() {
	char fila = 'A';
        for (int i = 0; i < quantFileiras; i++, fila++) {
            for (int j = 1; j <= vagasPorFileira; j++) {
                vagas.add(new Vaga(fila + String.format("%02d", j)));
            }
          }
	}

	public void estacionar(String placa) {
	// TODO: Implementar lógica.	
	}

	public double sair(String placa) {
	// TODO: Implementar lógica.
	}

	public double totalArrecadado() {
	// TODO: Implementar lógica.
	}

	public double arrecadacaoNoMes(int mes) {
	// TODO: Implementar lógica.
	}

	public double valorMedioPorUso() {
	// TODO: Implementar lógica.
	}

	public String top5Clientes(int mes) {
	// TODO: Implementar lógica.
	}
}
