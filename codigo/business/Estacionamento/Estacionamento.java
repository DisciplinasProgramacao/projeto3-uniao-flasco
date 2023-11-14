package business.Estacionamento;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import business.Cliente.Cliente;
import business.Exceptions.CodigoErroClienteJaCadastrado;
import business.Exceptions.CodigoErroNaoHaVagas;
import business.Exceptions.CodigoErroTempoMinimo;
import business.Exceptions.CodigoErroVagaFinalizada;
import business.Exceptions.CodigoErroVeiculoJaCadastrado;
import business.Exceptions.CodigoErroVeiculoNaoEncontrado;
import business.UsoDeVaga.UsoDeVaga;
import business.Vaga.Vaga;
import business.Veiculo.Veiculo;

public class Estacionamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int id;
    private int quantFileiras;
    private int vagasPorFileira;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private List<UsoDeVaga> usos;
    private List<Veiculo> veiculos;

    public Estacionamento(String nome, int fileiras, int vagasPorFila, int id) {
        this.nome = nome;
        this.id = id;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.usos = new ArrayList<>();
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

    public void addVeiculo(Veiculo veiculo, String idCli) throws CodigoErroVeiculoJaCadastrado {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                cliente.addVeiculo(veiculo);
                return;
            }

            if(veiculo.contains(veiculo)) {
                throw new CodigoErroVeiculoJaCadastrado();
            }
        }
        
    }

    public void addCliente(Cliente cliente) throws CodigoErroClienteJaCadastrado{
        if (cliente != null) {
            clientes.add(cliente);
        }

        if (cliente == null) {
            throw new CodigoErroClienteJaCadastrado();
        }
    }

    private void gerarVagas() {
        for (int i = 1; i <= quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                vagas.add(new Vaga(i, j));
            }
        }
    }

    public List<Cliente> getAllClientes() {
        return clientes;
    }

    public void estacionar(String placa) throws CodigoErroNaoHaVagas {
        for (Vaga vaga : vagas) {
            if (vaga.estacionar(placa)) {
                usos.add(new UsoDeVaga(vaga, LocalDateTime.now()));
                return;
            }

            if (vaga == null) {
                throw new CodigoErroNaoHaVagas();
            }
        }
    }

    public double sair(String placa) {
        double valor = 0.0;

            for (UsoDeVaga usoDeVaga : usos) {
                if (placa.equals(usoDeVaga.getVaga().getVeiculoEstacionado().getPlaca())) {
                    LocalDateTime saida = LocalDateTime.now();
                    valor = usoDeVaga.sair(saida);   
                }

                if (usoDeVaga.getVaga().isDisponivel() == false) {
                    throw new CodigoErroVagaFinalizada();
                }
            
            return valor;
        }
    }


    public double totalArrecadado() {
        return usos.stream().mapToDouble(UsoDeVaga::valorPago).sum();
    }

    public double arrecadadoNoMes(int mes) {
        return usos.stream()
                .filter(u -> u.getEntrada().getMonthValue() == mes)
                .mapToDouble(UsoDeVaga::valorPago)
                .sum();
    }

    public double valorMedioPorUso() {
        return totalArrecadado() / usos.size();
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
}


