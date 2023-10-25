import java.util.List;
import java.util.Scanner;


public class Aplicacao {

    private static List<Veiculo> veiculos;
    private static List <Cliente> clientes;
    private static List <Estacionamento> estacionamentos;
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

    public static void  menu( Scanner scanner){
        System.out.println("\nMENU:");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Adicionar novo veículo");
        System.out.println("3. Estacionar");
        System.out.println("4. Escolher serviços adicionais");
        System.out.println("5. Gerar relatório do cliente");
        System.out.println("6. Gerar relatório do veículo");
        System.out.println("7. Gerar relatório de arrecadação");
        System.out.println("8. Sair/voltar");
        
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        
        // Implemente os métodos correspondentes a cada opção do menu
        switch (opcao) {
            case 1:
            cadastrarCliente();
            break;

            case 2:
            adicionarVeiculo();
            break;

            case 3:
            estacionar();
            break;

            case 4:
            servicosAdicionais();
            break;

            case 5:
            // Gerar relatório do cliente
            break;

            case 6:
            // Gerar relatório do veículo
            break;

            case 7:
            // Gerar relatório de arrecadação
            break;

            case 8:
            System.out.println("Saindo do programa.");
            System.exit(0);
            
            default:
            System.out.println("Opção inválida. Tente novamente.");
        }
                
    }
            
            // case 1: Cadastrar cliente
            public static void cadastrarCliente(Cliente cliente, ClienteDAO clienteDAO, Scanner scanner) {
                System.out.println("Informe o nome do cliente: ");
                String nome = scanner.nextLine();

                try {
                    Cliente cliente = new Cliente(nome);
                    clienteDAO.add(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                } catch (NumberFormatException | IOException e) { 
                    System.out.println("Erro ao cadastar o cliente: " + e.getMessage());
                }
            }
            
            // case 2: Adicionar veículo
            public static void adicionarVeiculo(Veiculo veiculo, VeiculoDAO veiculoDAO, Scanner scanner) {
                System.out.println("Digite a placa do veiculo que deseja adicionar.");
                String placaVeiculos = scanner.nextLine();
                Veiculo veiculo = new Veiculo(placaVeiculos);
                System.out.println("Digite o seu id de cliente.");
                int idClientes = scanner.nextInt();
               // Estacionamento e = new Estacionamento();
               // e.addVeiculo(veiculo,idClientes);
            }
            
            // case 3: Estacionar
            public static void estacionar() {
                System.out.println("Informa a placa do carro que você deseja estacionar: ");
                String placaVeiculos = scanner.nextLine();
                
                for(Vaga vaga : vagas) {
                    if (vaga.estacionar()) {
                        usos.add(new UsoDeVaga(vaga, LocalDateTime.now()));
                        return;
                    }
                }
            }

            //case 4: Escolher serviços adicionais
            public static void servicosAdicionais() {
                System.out.println("Escolha os serviços desejados: ");
                if (indiceServico >= 0 && indiceServico < servicosAdicionais.size()) {
                    ServicoAdicional servicoSelecionado = servicosAdicionais.get(indiceServico);
                    uso.adicionarServico(servicoSelecionado);
                }
            }
            
            public static void main(String[] args) {
                System.out.println("BEM VINDO AO SISTEMA DE ESTACIONAMENTO \n");
                int escolhaEstacionamento = 0;
                Scanner scanner = new Scanner(System.in);
                EstacionamentoDAO DAOe = new EstacionamentoDAO("arquivo");
                Estacionamento estacionamento1 = new Estacionamento("Renato Vagas",30,30,1);
                DAOe.add(estacionamento1);
                Estacionamento estacionamento2 = new Estacionamento("Pedro Vagas",30,30,2);
                DAOe.add(estacionamento2);
                Estacionamento estacionamento3 = new Estacionamento("Duda Vagas",30,30,3);
                DAOe.add(estacionamento3);
            while(escolhaEstacionamento<1 || escolhaEstacionamento>3){
            System.out.println("ESCOLHA O ESTACIONAMENTO");
            System.out.println("1 - Estacionamento 1");
            System.out.println("2 - Estacionamento 2");
            System.out.println("3 - Estacionamento 3");
            
            escolhaEstacionamento = scanner.nextInt();
            setEstacionamentos(EstacionamentoDAO.getAll());
            for (Estacionamento estacionamento : estacionamentos) {
                if (escolhaEstacionamento == estacionamento.getId())
                {
                    setEstacionamentoUsado(estacionamento);
                }
            }
        }
        menu(scanner);
        }
    }
