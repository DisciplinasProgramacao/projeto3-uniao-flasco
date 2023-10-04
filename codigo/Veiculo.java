

public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos;

	

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public UsoDeVaga[] getUsos() {
		return usos;
	}

	public void setUsos(UsoDeVaga[] usos) {
		this.usos = usos;
	}

	public Veiculo(String placa) {
		setPlaca(placa);
	}

	public void estacionar(Vaga vaga) {
				if (vaga == null || !vaga.isDisponivel()) throw new IllegalArgumentException("A VAGA NÃO EXISTE OU NÃO ESTÁ DISPONÍVEL");
				if (vaga.isDisponivel()) {
					vaga.setDisponivel(false);
					UsoDeVaga uso = new UsoDeVaga(vaga);
					uso.setEntrada(System.currentTimeMillis());
					uso.setVeiculo(this);
					uso.setVaga(vaga);
					uso.setDisponivel(false);
					uso.setSaida(0);
					uso.setValor(0);
					this.usos.add(uso);
		
	}
	
}

	public double sair() {
		double sair = 0.0;
		//ver custo hora depois
		return sair;
		
	}

	public double totalArrecadado() {
		double totalArrecadado = 0.0;
		return totalArrecadado;
	}

	public double arrecadadoNoMes(int mes) {
		double arrecadadoNoMes = 0.0;
		return arrecadadoNoMes;
		
	}

	public int totalDeUsos() {
		int totalDeUsos = 0;
		return totalDeUsos;
		
	}

}
