import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UsoDeVaga implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private List<ServicoAdicional> servicosAdicionais = new ArrayList<>();

	/**
	 * Construtor da classe UsoDeVaga.
	 *
	 * @param vaga    A vaga utilizada.
	 * @param entrada A data e hora de entrada na vaga.
	 */
	public UsoDeVaga(Vaga vaga, LocalDateTime entrada) {
		this.vaga = vaga;
		this.entrada = entrada;
	}

	/**
	 * Método para adicionar um serviço adicional ao uso da vaga.
	 *
	 * @param servico O serviço adicional a ser adicionado.
	 */
	public void adicionarServico(ServicoAdicional servico) {
		this.servicosAdicionais.add(servico);
	}

	/**
	 * Método para calcular o valor total a ser pago ao sair do estacionamento.
	 *
	 * @param saida A data e hora de saída da vaga.
	 * @return O valor total a ser pago.
	 */
	public double sair(LocalDateTime saida) {
		this.saida = saida;
		double valor = this.valorPago();
		for (ServicoAdicional servico : servicosAdicionais) {
			valor += servico.getValor();
		}
		this.vaga.sair();
		return valor;
	}

	/**
	 * Método que calcula o valor a ser pago com base no tempo de uso da vaga.
	 *
	 * @return O valor a ser pago.
	 * @throws IllegalArgumentException Se um serviço adicional requer um tempo mínimo que não foi atendido.
	 */
	public double valorPago() {
		long periodo = entrada.until(saida, ChronoUnit.MINUTES);
		int minutos = (int) (periodo / 15);
		double valor = (double) (minutos * VALOR_FRACAO);
		for (ServicoAdicional servico : servicosAdicionais) {
			if (servico == ServicoAdicional.LAVAGEM && periodo < 60) {
				throw new IllegalArgumentException("O tempo mínimo para lavagem é de 1h.");
			}
			if (servico == ServicoAdicional.POLIMENTO && periodo < 120) {
				throw new IllegalArgumentException("O tempo mínimo para polimento é de 2h.");
			}
		}
		if (valor > VALOR_MAXIMO) {
			valor = VALOR_MAXIMO;
		}
		return valor;
	}

	/**
	 * Enumeração que representa os diferentes serviços adicionais oferecidos no estacionamento.
	 */
	enum ServicoAdicional implements Serializable {
		MANOBRA(5, 0),
		LAVAGEM(20, 60),
		POLIMENTO(45, 120);

		private double valor;
		private int tempoMinimo;

		/**
		 * Construtor para o enum ServicoAdicional.
		 *
		 * @param valor       O valor do serviço.
		 * @param tempoMinimo O tempo mínimo requerido para este serviço.
		 */
		ServicoAdicional(double valor, int tempoMinimo) {
			this.valor = valor;
			this.tempoMinimo = tempoMinimo;
		}

		/**
		 * Método para obter o valor do serviço adicional.
		 *
		 * @return O valor do serviço.
		 */
		public double getValor() {
			return valor;
		}

		/**
		 * Método para obter o tempo mínimo requerido para o serviço.
		 *
		 * @return O tempo mínimo em minutos.
		 */
		public int getTempoMinimo() {
			return tempoMinimo;
		}
	}
}
