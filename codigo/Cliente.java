

public class Cliente {

	private String nome;
	private String id;
	private Veiculo[] veiculos;

	/**
     * Construtor da classe Cliente.
     * @param nome O nome do cliente.
     * @param id O identificador do cliente.
     */
	public Cliente(String nome, String id) {
		this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
	}

	/**
     * Método para adicionar um veículo à lista de veículos do cliente.
     * @param veiculo O veículo a ser adicionado.
     */
	public void addVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}

	/**
     * Método para verificar se o cliente possui um veículo com a placa fornecida.
     * @param placa A placa do veículo a ser verificado.
     * @return O veículo com a placa fornecida, ou null se não encontrado.
     */
	public Veiculo possuiVeiculo(String placa) {
		for(Veiculo veiculo : veiculos){
			if (veiculo.getPlaca.equals(placa)){
				return veiculo;
			}
		}
		return null;
	}

	/**
     * Método para calcular o total de usos de todos os veículos do cliente.
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
     * Método para retornar o valor arrecadado por um veículo específico.
     * @param placa A placa do veículo.
     * @return O valor arrecadado pelo veículo.
     */
	public double arrecadadoPorVeiculo(String placa) {
		Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.getValorArrecadado();
        }
        return 0.0;
	}

	/**
     * Método para retornar o valor total arrecadado por todos os veículos do cliente.
     * @return O valor total arrecadado.
     */
	public double arrecadadoTotal() {
		for (Veiculo veiculo : veiculos) {
            totalArrecadado += veiculo.getValorArrecadado();
        }
        return totalArrecadado;
	}

	/**
     * Método para retornar o valor arrecadado pelo cliente em um determinado mês.
     * @param mes O número do mês.
     * @return O valor arrecadado no mês especificado.
     */
	public double arrecadadoNoMes(int mes) {
		double arrecadadoMes = 0.0;
        for (Veiculo veiculo : veiculos) {
            arrecadadoMes += veiculo.getValorArrecadadoNoMes(mes);
        }
        return arrecadadoMes;
	}

}
