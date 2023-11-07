package business.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.Veiculo.Veiculo;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String id;
    private List<Veiculo> veiculos;

    /**
     * Retorna o nome do cliente.
     *
     * @return nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o ID do cliente.
     *
     * @return ID do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     *
     * @param id ID do cliente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Construtor que inicializa um cliente com nome e ID especificados.
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
     * @param placa A placa do veículo a ser verificada.
     * @return O veículo com a placa fornecida ou null se não for encontrado.
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
     * Calcula o total de usos de todos os veículos associados ao cliente.
     *
     * @return Total de usos.
     */
    public int getTotalUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculos) {
            totalUsos += veiculo.totalDeUsos();
        }
        return totalUsos;
    }

    /**
     * Calcula o valor arrecadado por um veículo específico do cliente.
     *
     * @param placa A placa do veículo a ser consultado.
     * @return Valor arrecadado pelo veículo ou 0.0 se o veículo não for encontrado.
     */
    public double getValorArrecadado(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.totalArrecadado();
        }
        return 0.0;
    }

    /**
     * Calcula o valor total arrecadado por todos os veículos do cliente.
     *
     * @return Valor total arrecadado.
     */
    public double getValorArrecadado() {
        double totalArrecadado = 0.0;
        for (Veiculo veiculo : veiculos) {
            totalArrecadado += veiculo.totalArrecadado();
        }
        return totalArrecadado;
    }

    /**
     * Calcula o valor arrecadado por todos os veículos do cliente em um mês específico.
     *
     * @param mes O mês a ser consultado.
     * @return Valor arrecadado no mês.
     */
    public double getValorArrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (Veiculo veiculo : veiculos) {
            arrecadadoMes += veiculo.arrecadadoNoMes(mes);
        }
        return arrecadadoMes;
    }

    /**
     * Retorna a lista de veículos associados ao cliente.
     *
     * @return Lista de veículos do cliente.
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
