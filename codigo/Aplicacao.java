import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;


import javax.swing.JOptionPane;

public class Aplicacao {

    private static List<Veiculo> veiculos;
    private static List<Cliente> clientes;
    private static List<Estacionamento> estacionamentos;
    private static Estacionamento estacionamentoUsado;

    public static Estacionamento getEstacionamentoUsado() {
        return estacionamentoUsado;
    }

    public static void setEstacionamentoUsado(Estacionamento estacionamentoUsado) {
        Aplicacao.estacionamentoUsado = estacionamentoUsado;
    }

    public static List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public static void setVeiculos(List<Veiculo> veiculos) {
        Aplicacao.veiculos = veiculos;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(List<Cliente> clientes) {
        Aplicacao.clientes = clientes;
    }

    public static List<Estacionamento> getEstacionamentos() {
        return estacionamentos;
    }

    public static void setEstacionamentos(List<Estacionamento> estacionamentos) {
        Aplicacao.estacionamentos = estacionamentos;
    }

    public static void menu(Scanner scanner, ClienteDAO DAOc, EstacionamentoDAO DAOe, VeiculoDAO DAOv) {
        System.out.println("\nMENU:");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Adicionar novo veículo");
        System.out.println("3. Estacionar");
        System.out.println("4. Escolher serviços adicionais");
        System.out.println("5. Gerar relatório do cliente");
       // System.out.println("6. Gerar relatório do veículo");
        System.out.println("7. Gerar relatório de arrecadação");
        System.out.println("8. Sair/voltar");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

       
        switch (opcao) {
            case 1:
                cadastrarCliente(DAOc, scanner);
                break;

            case 2:
                adicionarVeiculo(getEstacionamentoUsado(), scanner);
                break;

            case 3:
                estacionar(scanner);
                break;

            case 4:
                servicosAdicionais(scanner);
                break;

            case 5:
                gerarRelatorioDoCliente(scanner);
                break;


           // case 6:
             //   scanner.nextLine();
               // System.out.println("Digite a placa do veículo que deseja gerar o relatório: ");
               // String placaVeiculos = scanner.nextLine();
               // gerarRelatorio(placaVeiculos);
               // break;

            case 7:
                arrecadacao(scanner);
                break;

            case 8:
                System.out.println("Saindo do programa.");
                DAOc.saveToFile(getClientes());
                DAOv.saveToFile(veiculos);
                DAOe.saveToFile(estacionamentos);

                System.exit(0);

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    // case 1: Cadastrar cliente
    public static void cadastrarCliente(ClienteDAO clienteDAO, Scanner scanner) {
        System.out.println("Informe o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.println("Informe o ID do cliente: ");
        String id = scanner.nextLine();

        try {
            Cliente cliente = new Cliente(nome, id);
            clienteDAO.add(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao cadastrar o cliente: " + e.getMessage());
        }
    }

    // case 2: Adicionar veículo
    public static void adicionarVeiculo(Estacionamento estacionamento, Scanner scanner) {
        try {
            System.out.println("Digite a placa do veículo que deseja adicionar.");
            String placaVeiculo = scanner.nextLine();
            for (Veiculo veiculo : veiculos) {
                if (placaVeiculo.equals(veiculo.getPlaca())) {
                    throw new ExcecaoGeral()
                            .setCodigoErro(CodigoVeiculo.VEICULO_JA_EXISTE)
                            .set("nome", "data inicial")
                            .set("valor", "12/13/2015");
                }
            }
            Veiculo veiculo = new Veiculo(placaVeiculo);
            System.out.println("Digite o seu ID de cliente.");
            String idCliente = scanner.nextLine();
            for (Cliente cliente : clientes) {
                if (idCliente.equals(cliente.getId())) {
                    estacionamento.addVeiculo(veiculo, idCliente);
                    break;
                }
            }

        } catch (ExcecaoGeral e) {
            JOptionPane.showMessageDialog(null, e, e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // case 3: Estacionar
    public static void estacionar(Scanner scanner) {
        Vaga vaga1 = new Vaga('A', 1);
        System.out.println("Informe a placa do carro que você deseja estacionar: ");

        String placaVeiculo = scanner.nextLine();
        for (Veiculo veiculo : veiculos) {
            if (placaVeiculo.equals(veiculo.getPlaca())) {
                veiculo.estacionar(vaga1);
                System.out.println("Veículo estacionado na vaga " + vaga1);
                return;
            }
        }
        System.out.println("Não há vagas disponíveis.");
    }

  // case 4: Escolher serviços adicionais
    public static void servicosAdicionais(Scanner scanner) {
        System.out.println("Informe a placa do carro ao qual você deseja adicionar serviços: ");
        String placa = scanner.nextLine();
        Veiculo veiculoDesejado = null;

        for (Veiculo veiculo : veiculos) {
            if (placa.equals(veiculo.getPlaca())) {
                veiculoDesejado = veiculo;
                break;
            }
        }

        if (veiculoDesejado == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }


        System.out.println("Escolha os serviços desejados: ");
        int index = 1;
        for (UsoDeVaga.ServicoAdicional servico : UsoDeVaga.ServicoAdicional.values()) {
            System.out.println(index + ". " + servico.name() + " - R$ " + servico.getValor() + " (Tempo mínimo: " + servico.getTempoMinimo() + " minutos)");
            index++;
        }

        System.out.println("Digite o número do serviço desejado (ou 0 para voltar):");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= UsoDeVaga.ServicoAdicional.values().length) {
            UsoDeVaga.ServicoAdicional servicoEscolhido = UsoDeVaga.ServicoAdicional.values()[escolha - 1];

              UsoDeVaga usoAtual = veiculoDesejado.getUsos().get(veiculoDesejado.getUsos().size() - 1);
            long periodo = usoAtual.getEntrada().until(LocalDateTime.now(), ChronoUnit.MINUTES);

              if (periodo < servicoEscolhido.getTempoMinimo()) {
                System.out.println("Tempo de uso atual do veículo é insuficiente para este serviço.");
                return;
            }

            usoAtual.adicionarServico(servicoEscolhido);
            System.out.println("Serviço " + servicoEscolhido.name() + " adicionado com sucesso!");
        } else if (escolha != 0) {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

// case 5: Gerar relatório do cliente
public static void gerarRelatorioDoCliente(Scanner scanner) {
    System.out.println("Digite o nome do cliente que deseja gerar o relatório: ");
    String nomeCliente = scanner.nextLine();

    Cliente clienteProcurado = null;
    for (Cliente cliente : getClientes()) {
        if (nomeCliente.equals(cliente.getNome())) {
            clienteProcurado = cliente;
            break;
        }
    }

    if (clienteProcurado == null) {
        System.out.println("Cliente não encontrado.");
        return;
    }

    System.out.println("Relatório do Cliente: " + clienteProcurado.getNome());
    System.out.println("ID: " + clienteProcurado.getId());
    System.out.println("Número de Veículos: " + clienteProcurado.getVeiculos().size());
    System.out.println("Total de Usos dos Veículos: " + clienteProcurado.getTotalUsos());
  }
  
    // case 7: Gerar relatório de arrecadação
    public static void arrecadacao(Scanner scanner) {
        Veiculo carro;
        System.out.println("PLACA DO VEÍCULO:");
        String placaVeiculo = scanner.nextLine();
        for (Veiculo veiculo : veiculos) {
            if (placaVeiculo.equals(veiculo.getPlaca())) {
                carro = veiculo;
                scanner.nextLine();

                System.out.println("Escolha um relatório para ser gerado: ");
                System.out.println("1- Gerar relatório de arrecadação no mês.");
                System.out.println("2- Gerar relatório de arrecadação total.");

                int escolha = scanner.nextInt();

                switch (escolha) {
                    case 1:
                        System.out.println("Digite o número do mês desejado: ");
                        int mes = scanner.nextInt();
                        double arrecadadoNoMes = carro.arrecadadoNoMes(mes);
                        System.out.println("A arrecadação no mês " + mes + ": " + arrecadadoNoMes);
                        break;

                    case 2:
                        double totalArrecadado = carro.totalArrecadado();
                        System.out.println("Total arrecadado: " + totalArrecadado);
                        break;

                    default:
                        System.out.println("Opção inválida. Digite outra opção!");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EstacionamentoDAO DAOe = new EstacionamentoDAO("Equipamento.dat");
        ClienteDAO DAOc = new ClienteDAO("Cliente.dat");
        VeiculoDAO DAOv = new VeiculoDAO("Veiculo.dat");

        System.out.println("BEM-VINDO AO SISTEMA DE ESTACIONAMENTO\n");
        int escolhaEstacionamento = 0;
        Scanner scanner = new Scanner(System.in);

        Estacionamento estacionamento1 = new Estacionamento("Renato Vagas", 30, 30, 1);
        DAOe.add(estacionamento1);
        Estacionamento estacionamento2 = new Estacionamento("Pedro Vagas", 30, 30, 2);
        DAOe.add(estacionamento2);
        Estacionamento estacionamento3 = new Estacionamento("Duda Vagas", 30, 30, 3);
        DAOe.add(estacionamento3);

        while (escolhaEstacionamento < 1 || escolhaEstacionamento > 3) {
            System.out.println("ESCOLHA O ESTACIONAMENTO");
            System.out.println("1 - Estacionamento 1");
            System.out.println("2 - Estacionamento 2");
            System.out.println("3 - Estacionamento 3");

            escolhaEstacionamento = scanner.nextInt();
            setEstacionamentos(DAOe.getAll());
            setClientes(DAOc.getAll());
            setVeiculos(DAOv.getAll());
            for (Estacionamento estacionamento : estacionamentos) {
                if (escolhaEstacionamento == estacionamento.getId()) {
                    setEstacionamentoUsado(estacionamento);
                }
            }
        }
        menu(scanner, DAOc, DAOe, DAOv);
    }
}
