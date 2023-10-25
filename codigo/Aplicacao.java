import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


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

    public static void  menu( Scanner scanner, ClienteDAO DAOc, EstacionamentoDAO DAOe, VeiculoDAO DAOv){
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
                cadastrarCliente(DAOc,scanner);
            break;

            case 2:
                adicionarVeiculo(getEstacionamentoUsado(),scanner);
            break;

            case 3:
                estacionar();
            break;

            case 4:
                servicosAdicionais();
            break;

            case 5:
                leitor.nextLine();
                System.out.println("Digite o nome do cliente que deseja gerar o relatório: ");
                String cliente = leitor.nextLine();
                gerarRelatorio(cliente);
            break;

            case 6:
                leitor.nextLine();
                System.out.println("Digite a placa do veículo que deseja gerar o relatório: ");
                String placaVeiculos = leitor.nextLine();
                gerarRelatorio(veiculos);
            break;

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
            public static void cadastrarCliente( ClienteDAO clienteDAO, Scanner scanner) {
                System.out.println("Informe o nome do cliente: ");
                String nome = scanner.nextLine();
                System.out.println("Informe o ID do cliente: ");
                String id = scanner.nextLine();

                try {
                    Cliente cliente = new Cliente(nome, id);
                    clienteDAO.add(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                } catch (NumberFormatException | IOException e) { 
                    System.out.println("Erro ao cadastar o cliente: " + e.getMessage());
                }
            }
            
            // case 2: Adicionar veículo
            public static void adicionarVeiculo(Estacionamento estacionamento, Scanner scanner) {
                try{
                System.out.println("Digite a placa do veiculo que deseja adicionar.");
                String placaVeiculos = scanner.nextLine();
                 for (Veiculo veiculo2 : veiculos) {
                if (placaVeiculos == veiculo2.getPlaca() ){
                    throw new ExcecaoGeral()
                    .setCodigoErro(CodigoVeiculo.VEICULO_JA_EXISTE)
			        .set("nome", "data inicial")
			        .set("valor", "12/13/2015");
                }
                
            }
                Veiculo veiculo = new Veiculo(placaVeiculos);
                System.out.println("Digite o seu id de cliente.");
                String idCliente = scanner.nextLine();
                for (Cliente cliente : clientes) {
                    if (idCliente == cliente.getId())
                    {
                        estacionamento.addVeiculo(veiculo,idCliente);
                        break;
                        
                    }
                }
               
           
        }catch (ExcecaoGeral e) {
               JOptionPane.showMessageDialog(null, e, e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
            }
            }
               
            }
            
            // case 3: Estacionar
            public static void estacionar(Scanner scanner) {
                Vaga vaga1 = new Vaga('A', 1);
                System.out.println("Informa a placa do carro que você deseja estacionar: ");

                String placaVeiculos = scanner.nextLine();
                for (Veiculo veiculo : veiculos) {
                    if (placaVeiculos == veiculo.getPlaca()) {
                        veiculo.estacionar(vaga1);
                    }
                }
                System.out.println("Não há vagas disponíveis.");
            }
                
            

            //case 4: Escolher serviços adicionais
            public static void servicosAdicionais() {
                System.out.println("Escolha os serviços desejados: ");
      
            }

            //case 7: Gerar relatório de arrecadação
            public static void arrecadacao(Scanner scanner) {
                Veiculo carro;
                System.out.println("PLACA DO VEICULO:");
                String placaVeiculo = scanner.nextLine();
                for (Veiculo veiculo : veiculos) {
                    if (placaVeiculo == veiculo.getPlaca()) {
                        carro = veiculo;
                    }
                }
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
                        System.out.println("Opção invalida. Digite outra opção!");
                    break;
                }
            }
            
            
            
            public static void main(String[] args) {
            EstacionamentoDAO DAOe = new EstacionamentoDAO("Equipamento.dat");
            ClienteDAO DAOc = new ClienteDAO("Cliente.dat");
            VeiculoDAO DAOv = new VeiculoDAO("Veiculo.dat");
                

                System.out.println("BEM VINDO AO SISTEMA DE ESTACIONAMENTO \n");
                int escolhaEstacionamento = 0;
                Scanner scanner = new Scanner(System.in);
                
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
            setEstacionamentos(DAOe.getAll());
            setClientes(DAOc.getAll());
            setVeiculos(DAOv.getAll());
            for (Estacionamento estacionamento : estacionamentos) {
                if (escolhaEstacionamento == estacionamento.getId())
                {
                    setEstacionamentoUsado(estacionamento);
                }
            }
        }
        menu(scanner, DAOc,DAOe,DAOv);
        }
    