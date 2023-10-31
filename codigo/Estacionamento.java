import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estacionamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int id;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private List<UsoDeVaga> usos;

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

    private void gerarVagas() {
        for (char i = 'A'; i < 'A' + quantFileiras; i++) {
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
        clientes.add(cliente);
    }

    public void estacionar(String placa) {
        for (Vaga vaga : vagas) {
            if (vaga.estacionar(placa)) {
                usos.add(new UsoDeVaga(vaga, LocalDateTime.now()));
                return;
            }
        }
        System.out.println("Não há vagas disponíveis");
    }

    public double sair(String placa) {
        for (UsoDeVaga uso : usos) {
            if (uso.getVaga().getVeiculoEstacionado().equals(placa) && uso.getSaida() == null) {
                uso.setSaida(LocalDateTime.now());
                double valor = uso.valorPago();
                uso.setValorPago(valor);
                uso.getVaga().sair();
                return valor;
            }
        }
        return 0.0;
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
}
