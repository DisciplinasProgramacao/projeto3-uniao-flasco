import java.util.Scanner;

    public class Aplicacao {
        public static void  menu(int escolhaEstacionamento, Scanner scanner){
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
                    // Cadastrar cliente
                    break;
                case 2:
                     System.out.println("Digite a placa do veiculo que deseja adicionar.");
                    String placaVeiculos = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placaVeiculos);
                    System.out.println("Digite o seu id de cliente.");
                    int idClientes = scanner.nextInt();
                    Estacionamento e = new Estacionamento();
                    e.addVeiculo(veiculo,idClientes);
                    break;
                case 3:
                    // Estacionar
                    break;
                case 4:
                    // Escolher serviços adicionais
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

        public static void main(String[] args) {
           System.out.println("BEM VINDO AO SISTEMA DE ESTACIONAMENTO \n");
            int escolhaEstacionamento = 0;
            Scanner scanner = new Scanner(System.in);
            while(escolhaEstacionamento<1 || escolhaEstacionamento>3){
            System.out.println("ESCOLHA O ESTACIONAMENTO");
            System.out.println("1 - Estacionamento 1");
            System.out.println("2 - Estacionamento 2");
            System.out.println("3 - Estacionamento 3");
            
            escolhaEstacionamento = scanner.nextInt();
        }
        menu(escolhaEstacionamento, scanner);
        }
    }
