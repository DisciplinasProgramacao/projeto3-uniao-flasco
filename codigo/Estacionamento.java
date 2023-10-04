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
	
	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
	this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        gerarVagas();
	}

	 public void addVeiculo(Veiculo veiculo, String idCli) {
        for (Cliente cliente : id) {
            if (cliente.getId().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                return;
            }
        }
        //add tratamento caso cliente não seja encontrado.
    }

	public void addCliente(Cliente cliente) {
	id.add(cliente);
	}

	private void gerarVagas() {
	char fila = 'A';
        for (int i = 0; i < quantFileiras; i++, fila++) {
            for (int j = 1; j <= vagasPorFileira; j++) {
                vagas.add(new Vaga(fila + String.format("%02d", j)));
            }
          }
	}

	public void estacionar(String placa) {
		
	}

	public double sair(String placa) {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

}
