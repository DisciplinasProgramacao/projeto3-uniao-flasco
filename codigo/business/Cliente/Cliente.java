package business.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.Exceptions.CodigoErroVeiculoJaCadastrado;
import business.Veiculo.Veiculo;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String id;
    private List<Veiculo> veiculos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
    }

    public void addVeiculo(Veiculo veiculo) throws CodigoErroVeiculoJaCadastrado {
        if (veiculos.contains(veiculo)) {
            throw new CodigoErroVeiculoJaCadastrado();
        }
        veiculos.add(veiculo);
    }

    public boolean possuiVeiculo(String placa) {
        for(Veiculo veiculo : veiculos){
            if (veiculo.getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    public int getTotalUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculos) {
            totalUsos += veiculo.totalDeUsos();
        }
        return totalUsos;
    }

    public double getValorArrecadadoVeiculo(String placa) {
        double valorArrecadadoVeiculo = 0.0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                valorArrecadadoVeiculo += veiculo.totalArrecadado();
                break;
            }
        }
        return valorArrecadadoVeiculo;
    }

    public double getTotalArrecadadoCliente() {
        double totalArrecadadoCliente = 0.0;
        for (Veiculo veiculo : veiculos) {
            totalArrecadadoCliente += veiculo.totalArrecadado();
        }
        return totalArrecadadoCliente;
    }

    public double getTotalArrecadadoClienteMes(int mes) {
        double totalArrecadadoClienteMes = 0.0;
        for (Veiculo veiculo : veiculos) {
            totalArrecadadoClienteMes += veiculo.arrecadadoNoMes(mes);
        }
        return totalArrecadadoClienteMes;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
