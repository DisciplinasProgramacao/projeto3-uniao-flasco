import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UsoDeVaga {
	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private List<ServicoAdicional> servicosAdicionais = new ArrayList<>();

	public UsoDeVaga(Vaga vaga, LocalDateTime entrada) {
		this.vaga = vaga;
		this.entrada = entrada;
	}

	public void adicionarServico(ServicoAdicional servico) {
		this.servicosAdicionais.add(servico);
	}

	public double sair(LocalDateTime saida) {
		this.saida = saida;

		double valor = this.valorPago();
		for (ServicoAdicional servico : servicosAdicionais) {
			valor += servico.getValor();
		}

		this.vaga.sair();
		return valor;
	}

	public double valorPago() {
		long periodo = entrada.until(saida, ChronoUnit.MINUTES);
		int minutos = (int) (periodo / 15);
		double valor = (double) (minutos * VALOR_FRACAO);

		// Verificar tempo de permanência para os serviços adicionais
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

	enum ServicoAdicional {
		MANOBRA(5, 0),
		LAVAGEM(20, 60),
		POLIMENTO(45, 120); //ja Inclui a lavagem

		private double valor;
		private int tempoMinimo;

		ServicoAdicional(double valor, int tempoMinimo) {
			this.valor = valor;
			this.tempoMinimo = tempoMinimo;
		}

		public double getValor() {
			return valor;
		}

		public int getTempoMinimo() {
			return tempoMinimo;
		}
	}
}
