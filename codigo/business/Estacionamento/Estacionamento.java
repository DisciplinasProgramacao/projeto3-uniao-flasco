package business.Estacionamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import business.Cliente.Cliente;

import business.Vaga.Vaga;
import business.Veiculo.Veiculo;

public class Estacionamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int id;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    

    public Estacionamento(String nome, int fileiras, int vagasPorFila, int id) {
        this.nome = nome;
        this.id = id;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        
        this.vagas = new ArrayList<>();
        gerarVagas();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private void gerarVagas() {
        for (int i = 1; i <= quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas.add(new Vaga(i, j));
            }
        }
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {

        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    public void addCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
        }
    }

    public void addAllClientes(List<Cliente> clientes) {
        this.clientes.addAll(clientes);
    }


public void estacionar (Veiculo veiculo){
    Vaga vaga = vagas.stream().filter(v -> v.isDisponivel())
                      .findFirst()
                      .orElseThrow(() -> new RuntimeException("Vaga não disponivel"));

                      veiculo.estacionar(vaga);
        
        
    }

    public double sair(Veiculo veiculo) {
        double valor = veiculo.sair();
        return valor;
    }

    public double totalArrecadado() {
        return clientes.stream()
                .mapToDouble(Cliente::getValorArrecadado)
                .sum();
    }

    public double arrecadadoNoMes(int mes) {
        return clientes.stream()
                .mapToDouble(cliente -> cliente.getValorArrecadadoNoMes(mes))
                .sum();
    }

    public double valorMedioPorUso() {
        
        return clientes.stream()
                .mapToDouble(cliente -> cliente.getValorArrecadado() / cliente.getTotalUsos())
                .average()
                .orElse(0.0);
    }

    public List<String> top5Clientes(int mes) {
        Map<Cliente, Double> mapClientes = clientes.stream()
                .collect(Collectors.toMap(c -> c, c -> c.getValorArrecadadoNoMes(mes)));

        return mapClientes.entrySet().stream()
                .sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed())
                .limit(5)
                .map(e -> e.getKey().getNome())
                .collect(Collectors.toList());
    }


    public boolean VeiculoExiste(String placa){
        for (Cliente cliente : clientes) {
            if (cliente.possuiVeiculo(placa) == true) {
                return true;
            }
        }
        return false;
        
    }


    public List<Cliente> getAllClientes() {
        return clientes;
    }
}


