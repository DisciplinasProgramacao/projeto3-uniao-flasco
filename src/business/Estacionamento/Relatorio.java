package business.Estacionamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import business.Cliente.Cliente;

import business.Interfaces.Observador;
import business.Interfaces.Observavel;


/**
 * Classe Relatório para gerenciamento e armazenamento de dados estatísticos do estacionamento.
 */
public class Relatorio implements Serializable, Observador {
    private Map<MesEnum, Set<Cliente>> top5Clientes;
    private List<Observavel> veiculosObservados;
    private Estacionamento estacionamento;

    /**
     * Construtor da classe Relatório.
     *
     * @param estacionamento O estacionamento associado ao relatório.
     * @param observavel     O objeto observável relacionado aos veículos.
     */
    public Relatorio(Estacionamento estacionamento, Observavel observavel) {
        this.estacionamento = estacionamento;
        veiculosObservados = new ArrayList<>();
        this.veiculosObservados.add(observavel);
        observavel.addObservador(this);
        top5Clientes = new HashMap<>();
    }

    /**
     * Obtém o mapeamento dos top 5 clientes por mês.
     *
     * @return O mapeamento dos top 5 clientes por mês.
     */
    public Map<MesEnum, Set<Cliente>> getTop5Clientes() {
        return top5Clientes;
    }

    /**
     * Define o mapeamento dos top 5 clientes por mês.
     *
     * @param top5Clientes O mapeamento dos top 5 clientes por mês.
     */
    public void setTop5Clientes(Map<MesEnum, Set<Cliente>> top5Clientes) {
        this.top5Clientes = top5Clientes;
    }
    @Override
    public void setObservavel(Observavel v){
        this.veiculosObservados.add(v);
        v.addObservador(this);
    }
    @Override
    public void removeObservavel(Observavel v){
        this.veiculosObservados.remove(v);
        v.removeObservador(this);
    }

    /**
     * Obtém a lista de objetos observáveis (veículos).
     *
     * @return A lista de objetos observáveis (veículos).
     */
    public List<Observavel> getVeiculosObservados() {
        return veiculosObservados;
    }

    /**
     * Define a lista de objetos observáveis (veículos).
     *
     * @param veiculosObservados A lista de objetos observáveis (veículos).
     */
    public void setObservaveis(List<Observavel> veiculosObservados) {
        this.veiculosObservados = veiculosObservados;
    }

    /**
     * Método de atualização chamado pelo observável para notificar mudanças.
     *
     * @param mes O mês que foi atualizado.
     */
    @Override
    public void update(int mes) {
        MesEnum mesEnum = MesEnum.fromInt(mes); 
        
        Set<Cliente> topClientes = estacionamento.top5Clientes(mesEnum);
        top5Clientes.put(mesEnum, topClientes);
    }

    /**
     * Obtém os top 5 clientes do mês especificado.
     *
     * @param mes O mês para o qual deseja-se obter os top 5 clientes.
     * @return Os top 5 clientes do mês especificado.
     */
    public Set<Cliente> getTop5ClientesNoMes(int mes){
        MesEnum mesEnum = MesEnum.fromInt(mes); 
        Set<Cliente> topClientes = top5Clientes.get(mesEnum);
        return topClientes;
    }
}
