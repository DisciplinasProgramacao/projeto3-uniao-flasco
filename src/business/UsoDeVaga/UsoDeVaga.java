package business.UsoDeVaga;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import business.Plano.Mensalista;
import business.Plano.Plano;
import business.Plano.Turnista;
import business.Vaga.Vaga;

/**
 * Classe que representa o uso de uma vaga de estacionamento por um cliente.
 * Controla a entrada, saída, serviços adicionais e cálculo do valor a ser pago pelo uso da vaga.
 */
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
	 * @param plano O plano associado ao cliente.
	 * @return O valor total a ser pago.
	 */
	public double sair(LocalDateTime saida, Plano plano) {
		double valor= 0.0;
		this.saida = saida;
		if (plano instanceof Turnista) {
			Turnista turnista = (Turnista) plano;
			if (turnista.getTurno().getInicio().isAfter(this.entrada.toLocalTime())) {
				valor = this.valorPago();
			}
			else if (turnista.getTurno().getFim().isBefore(this.saida.toLocalTime())) {
				valor = this.valorPago();
			}
			
		}
		 else if (plano instanceof Mensalista)
		 			valor = 0;
		else {
			valor = this.valorPago();
		}
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
     * Obtém a vaga utilizada no uso.
     *
     * @return A vaga utilizada.
     */
    public Vaga getVaga() {
        return vaga;
    }

    /**
     * Define a vaga utilizada no uso.
     *
     * @param vaga A vaga a ser definida para o uso.
     */
    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return A data e hora de entrada.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }

    /**
     * Define a data e hora de entrada na vaga.
     *
     * @param entrada A data e hora de entrada a ser definida.
     */
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    /**
     * Obtém a data e hora de saída da vaga.
     *
     * @return A data e hora de saída.
     */
    public LocalDateTime getSaida() {
        return saida;
    }

    /**
     * Define a data e hora de saída da vaga.
     *
     * @param saida A data e hora de saída a ser definida.
     */
    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    /**
     * Obtém o valor pago pelo uso da vaga.
     *
     * @return O valor pago.
     */
    public double getValorPago() {
        return valorPago;
    }

    /**
     * Define o valor pago pelo uso da vaga.
     *
     * @param valorPago O valor pago a ser definido.
     */
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    /**
     * Obtém a lista de serviços adicionais associados ao uso da vaga.
     *
     * @return A lista de serviços adicionais.
     */
    public List<ServicoAdicional> getServicosAdicionais() {
        return servicosAdicionais;
    }

	/**
	 * Enumeração que representa os diferentes serviços adicionais oferecidos no estacionamento.
	 */
	public enum ServicoAdicional  {
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

		public double getValor() {
			return valor;
		}

		public int getTempoMinimo() {
			return tempoMinimo;
		}
	}
}
