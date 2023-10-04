
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;

	public UsoDeVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida, double valorPago) {
		this.vaga = vaga;
		this.entrada = entrada;
		this.saida = saida;
		this.valorPago = valorPago;
		
	}

	public double sair() {//Não entendi o que esse método é para fazer
		double valor = this.valorPago();

		this.vaga.sair();

		return valor;


		
	}

	public double valorPago() {
		long periodo = entrada.until(saida, ChronoUnit.MINUTES);
		int minutos = (int)periodo/15;
		double valor = (double)(minutos * VALOR_FRACAO);

		if(valor>VALOR_MAXIMO){
			valor = VALOR_MAXIMO;
		}

		return valor;

	}

}
