package business.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import business.Plano.Plano;
import business.Veiculo.Veiculo;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private Plano plano;
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
     * Define o plano associado ao cliente.
     *
     * @param plano O plano associado ao cliente.
     */
    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    /**
     * Retorna o plano associado ao cliente.
     *
     * @return O plano associado ao cliente.
     */
    public Plano getPlano() {
        return plano;
    }

    /**
     * Construtor que inicializa um cliente com nome, ID e plano especificados.
     *
     * @param nome  O nome do cliente.
     * @param id    O ID do cliente.
     * @param plano O plano associado ao cliente.
     */
    public Cliente(String nome, String id, Plano plano) {
        this.nome = nome;
        this.id = id;
        this.plano = plano;
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
     * @return true se o cliente possui o veículo com a placa fornecida, caso contrário, false.
     */
    public boolean possuiVeiculo(String placa) {
        for(Veiculo veiculo : veiculos){
            if (veiculo.getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula o total de usos de todos os veículos associados ao cliente.
     *
     * @return O total de usos de todos os veículos do cliente.
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
        possuiVeiculo(placa);
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v.totalArrecadado();
            }
            
        }
        return 0.0;
    }

    /**
     * Calcula o valor total arrecadado por todos os veículos do cliente.
     *
     * @return Valor total arrecadado.
     */
    public double getValorTotalArrecadado() {
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
