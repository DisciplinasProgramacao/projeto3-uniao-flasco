import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String id;
	private List<Veiculo> veiculos;

    /**
     * Constrói um novo cliente com um nome e um ID especificados.
     *
     * @param nome Nome do cliente.
     * @param id ID do cliente.
     */
	public Cliente(String nome, String id) {
		this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
	}

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     *
     * @param veiculo O veículo a ser adicionado.
     */
	public void addVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}

    /**
     * Verifica se o cliente possui um veículo com uma placa específica.
     *
     * @param placa A placa do veículo a ser procurado.
     * @return O veículo com a placa especificada ou null se não for encontrado.
     */
	public Veiculo possuiVeiculo(String placa) {
		for(Veiculo veiculo : veiculos){
			if (veiculo.getPlaca().equals(placa)){
				return veiculo;
			}
		}
		return null;
	}

    /**
     * Calcula o total de usos de todos os veículos do cliente.
     *
     * @return O total de usos.
     */
	public int totalDeUsos() {
		int totalUsos = 0;
        for (Veiculo veiculo : veiculos) {
            totalUsos += veiculo.getTotalUsos();
        }
        return totalUsos;
	}

    /**
     * Calcula o valor arrecadado por um veículo específico.
     *
     * @param placa A placa do veículo a ser consultado.
     * @return O valor arrecadado pelo veículo ou 0.0 se o veículo não for encontrado.
     */
	public double arrecadadoPorVeiculo(String placa) {
		Veiculo veiculo = possuiVeiculo(placa);
            return veiculo.getValorArrecadado();
	}

    /**
     * Calcula o total arrecadado por todos os veículos do cliente.
     *
     * @return O valor total arrecadado.
     */
	public double arrecadadoTotal() {
		double totalArrecadado = 0.0;
		for (Veiculo veiculo : veiculos) {
            totalArrecadado += veiculo.getValorArrecadado();
        }
        return totalArrecadado;
	}

    /**
     * Calcula o valor arrecadado por todos os veículos do cliente em um mês específico.
     *
     * @param mes O mês a ser consultado.
     * @return O valor arrecadado no mês.
     */
	public double arrecadadoNoMes(int mes) {
		double arrecadadoMes = 0.0;
        for (Veiculo veiculo : veiculos) {
            arrecadadoMes += veiculo.getValorArrecadadoNoMes(mes);
        }
        return arrecadadoMes;
	}
}
