package business.Veiculo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import business.Interfaces.Observador;
import business.Estacionamento.Relatorio;
import business.Interfaces.Observavel;
import business.Plano.Plano;
import business.UsoDeVaga.UsoDeVaga;
import business.UsoDeVaga.UsoDeVaga.ServicoAdicional;
import business.Vaga.Vaga;

/**
 * A classe Veiculo representa um veículo com uma placa associada e um histórico
 * de usos de vagas. Cada veículo pertence a um cliente proprietário e pode
 * estacionar em diferentes vagas durante o tempo.
 * 
 * @author @RenatoMAP77
 */
public class Veiculo implements Serializable, Observavel {
    private String placa;
    private LinkedList<UsoDeVaga> usos;
    private List<Observador> relatorios;
   

    /**
     * Construtor da classe Veiculo. Inicializa uma instância de veículo com a placa
     * fornecida.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        setPlaca(placa);
        this.usos = new LinkedList<>();
        this.relatorios = new ArrayList<>();
    }

    /**
     * Define a placa do veículo.
     *
     * @param placa A placa do veículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a lista de usos de vagas associada ao veículo.
     *
     * @param usos A lista de usos de vagas do veículo.
     */
    public void setUsos(List<UsoDeVaga> usos) {
        this.usos = (LinkedList<UsoDeVaga>) usos;
    }

    /**
     * Obtém a lista de usos de vagas associada ao veículo.
     *
     * @return A lista de usos de vagas do veículo.
     */
    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    /**
     * Estaciona o veículo em uma vaga específica, registrando o uso da vaga e
     * marcando-a como indisponível.
     *
     * @param vaga A vaga em que o veículo será estacionado.
     * @throws IllegalArgumentException Se a vaga não existir ou não estiver disponível.
     */
    public void estacionar(Vaga vaga) {
        if (vaga == null || !vaga.isDisponivel())
            throw new IllegalArgumentException("A VAGA NÃO EXISTE OU NÃO ESTÁ DISPONÍVEL");

        if (vaga.isDisponivel()) {
            vaga.setDisponivel(false);
            LocalDateTime entrada = LocalDateTime.now();
            UsoDeVaga uso = new UsoDeVaga(vaga, entrada);
            usos.add(uso);
        }
    }

    /**
     * Registra a saída do veículo de uma vaga específica e calcula o valor a ser
     * pago.
     *
     * @param plano O plano do cliente que determina as tarifas de estacionamento.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(Plano plano) {
        double valor = 0.0;

        UsoDeVaga usoDeVaga = usos.getLast();
        if (usoDeVaga != null) {

            LocalDateTime saida = LocalDateTime.now();
            valor = usoDeVaga.sair(saida,plano);
        }
        notificarObservadores();
        return valor;
    }

    /**
     * Calcula o total arrecadado com os usos de vagas feitos pelo veículo.
     *
     * @return O total arrecadado com os usos de vagas.
     */
    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (UsoDeVaga usoDeVaga : usos) {
            totalArrecadado += usoDeVaga.getValorPago();
        }
        return totalArrecadado;
    }

    /**
     * Calcula o total arrecadado com os usos de vagas feitos pelo veículo em um mês
     * específico.
     *
     * @param mes O mês para o qual se deseja calcular a arrecadação (1 para
     *            janeiro, 2 para fevereiro, etc.).
     * @return O total arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0.0;
        for (UsoDeVaga usoDeVaga : usos) {
            LocalDateTime entrada = usoDeVaga.getEntrada();
            Month monthOfYear = Month.of(entrada.getMonthValue());
            int mesEntrada = monthOfYear.getValue();
            if (mesEntrada == mes) {
                arrecadadoNoMes += usoDeVaga.getValorPago();
            }
        }
        return arrecadadoNoMes;
    }

    /**
     * Obtém o número total de usos de vagas realizados pelo veículo.
     *
     * @return O número total de usos de vagas.
     */
    public int totalDeUsos() {
        return usos.size();
    }

    /**
     * Gera um relatório de uso de vagas para o veículo.
     */
  public void relatorioDeUsoDeVagasVeiculo() {
    int i = 0;
    System.out.println("Placa: " + placa);
    for (UsoDeVaga u : usos) {
        System.out.println("Uso de vaga " + i++);
        System.out.println("Vaga: " + u.getVaga().getId());

        LocalDateTime entrada = u.getEntrada();
        System.out.println("Entrada: " + formatarDataHora(entrada));

        LocalDateTime saida = u.getSaida();
        System.out.println("Saída: " + (saida != null ? formatarDataHora(saida) : "Não saiu ainda"));

        System.out.println("Valor pago: " + u.getValorPago());

        List<ServicoAdicional> servicos = u.getServicosAdicionais();
        if (servicos.size() > 0) {
            System.out.println("Serviços adicionais:");
            for (ServicoAdicional servico : servicos) {
                System.out.println(servico);
            }
        }
    }
}

    /**
     * Formata uma data e hora no formato específico para exibição.
     *
     * @param dateTime A data e hora a serem formatadas.
     * @return A string formatada representando a data e hora.
     */
private String formatarDataHora(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
}

    /**
     * Adiciona um observador para notificações sobre o veículo.
     *
     * @param observador O observador a ser adicionado.
     */
    @Override
    public void addObservador(Observador observador) {
        relatorios.add(observador);
    }

    /**
     * Remove um observador do veículo.
     *
     * @param observador O observador a ser removido.
     */
    @Override
    public void removeObservador(Observador observador) {
        relatorios.remove(observador);
    }

    /**
     * Notifica todos os observadores sobre alterações no veículo.
     */
    @Override
    public void notificarObservadores() {
        for (Observador observador : relatorios) {
            observador.update(LocalDateTime.now().getMonthValue());
        }
    }

    /**
     * Verifica se o veículo está atualmente estacionado em uma vaga.
     *
     * @return Verdadeiro se o veículo estiver estacionado, falso caso contrário.
     */
    public boolean isEstacionado() {
        if (usos.size() > 0) {
            UsoDeVaga usoDeVaga = usos.getLast();
            if (usoDeVaga.getSaida() == null) {
                return true;
            }
        }
        return false;
    }
}
