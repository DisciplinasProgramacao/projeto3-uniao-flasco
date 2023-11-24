package app;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;



import javax.swing.JOptionPane;

import business.Cliente.*;
import business.Estacionamento.*;
import business.Veiculo.*;
import business.UsoDeVaga.*;
import business.Exceptions.*;
import business.Plano.Horista;
import business.Plano.Plano;
import business.Plano.Turnista;
import business.Plano.Mensalista;
import business.Plano.Turnos;

public class Aplicacao {

    private static List<Estacionamento> estacionamentos = new ArrayList<Estacionamento>();
    private static int estacionamentoUsado;
    

    public static List<Estacionamento> getEstacionamentos() {
        return estacionamentos;
    }

    public static void setEstacionamentos(List<Estacionamento> estacionamentos) {
        Aplicacao.estacionamentos = estacionamentos;
    }
    
    public static void menu(Scanner scanner, GenericDAO DAOe) {
        int opcaoMenuPrincipal=0; 
        
        do{
        System.out.println("\nMENU:");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Adicionar novo veículo");
        System.out.println("3. Estacionar");
        System.out.println("4. Sair com veiculo");
        System.out.println("5. Escolher serviços adicionais");
        System.out.println("6. Gerar relatório do cliente");
        System.out.println("7. Gerar relatório de uso de vagas do veículo");
        System.out.println("8. Gerar relatório de arrecadação de um veiculo ");
        System.out.println("9. Gerar relatorio de arrecadacao dos estacionamentos");
        System.out.println("10. Gerar relatorio de arrecadacao média dos horistas");
        System.out.println("11. Gerar relatorio de usos médios de mensalistas");
        System.out.println("12. Voltar");

        System.out.print("Escolha uma opção: ");
        opcaoMenuPrincipal = scanner.nextInt();
       
        switch (opcaoMenuPrincipal) {
            case 1:
                cadastrarCliente( scanner);
                break;

            case 2:
                adicionarVeiculo( scanner);
                break;

            case 3:
                estacionar(scanner);
                break;
                case 4:
            sairDoEstacionamento(scanner);
                break;

            case 5:
                servicosAdicionais(scanner);
                break;

            case 6:
                gerarRelatorioDoCliente(scanner);
                break;


           case 7:
                try {
                    scanner.nextLine();
                    System.out.println("Digite a placa do veículo que deseja gerar o relatório: ");
                    String placaVeiculo = scanner.nextLine();
                    gerarRelatorioUsoDeVaga(placaVeiculo);
                    break;
                    
                } catch (ExcecaoGeral e) {
                  System.out.println(e.getCodigoErro());
                }

            case 8:
                arrecadacao(scanner);
                break;
            case 9:
               arrecadacaoEstacionamento(scanner);

                break;
            case 10:
                arrecadacaoMediaHoristas(scanner);
                break;
            case 11:
                mediaUsoMensalistas(scanner);
                break;
            case 12:
            opcaoMenuPrincipal = 12;
            break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }while(opcaoMenuPrincipal!=12);

     System.out.println("Saindo do programa.");
                DAOe.saveToFile(estacionamentos);
                System.exit(0);
    }

    // case 1: Cadastrar cliente
     public static void cadastrarCliente( Scanner scanner) {
        try {
        System.out.println("Informe o nome do cliente: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.println("Informe o ID do cliente: ");
        String id = scanner.nextLine();
        int op;
        
        Plano plano = null;
        do{
        System.out.println("Escolha o tipo do plano do cliente \n 1-Horista\n2-Turnista\n3-Mensalista");
        op = scanner.nextInt();
        switch (op) {
            case 1:
            plano = new Horista();   
                break;
            case 2:
                int opcao;
            do{
            System.out.println("Escolha um turno:");
            System.out.println("1 - Manha");
            System.out.println("2 - Tarde");
            System.out.println("3 - Noite");
            opcao = scanner.nextInt();
            if(opcao == 1)
            {
             plano = new Turnista("Turnista",Turnos.MANHA);  
            }
            else if(opcao == 2)
            {
                plano = new Turnista("Turnista",Turnos.TARDE);
            }
            else if(opcao ==3)
            {
                plano = new Turnista("Turnista",Turnos.NOITE);
            }
            }
            while(opcao>3 || opcao<1);
            
                break;
            case 3:
            plano = new Mensalista("Mensalista");
                break;
            default:
                break;
        }
        
    }
    while (op > 3 || op < 1);
        
            int i =0, j=0;
            Cliente cliente = new Cliente(nome, id, plano);
            for (Estacionamento estacionamento : estacionamentos) {
                if (estacionamento.getId() == estacionamentoUsado) {
                    
                    estacionamento.addCliente(cliente);
                    
                    
                    System.out.println(i++);
                }
                System.out.println(j++);
            }

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao cadastrar o cliente: " + e.getMessage());
        }
    }

    // case 2: Adicionar veículo
    public static void adicionarVeiculo( Scanner scanner) {
        try {
            System.out.println("Digite a placa do veículo que deseja adicionar.");
            scanner.nextLine();
            String placaVeiculo = scanner.nextLine();
            
            for (Estacionamento e : estacionamentos) {
                if (e.getId() == estacionamentoUsado) {
                    
                    if (e.VeiculoExiste(placaVeiculo)==true)
                            {  throw new ExcecaoGeral()
                                      .setCodigoErro(CodigoVeiculo.VEICULO_JA_EXISTE)
                                      .set("nome", "data inicial")
                                      .set("valor", "12/13/2015");
                            }
                     
                            Veiculo veiculo = new Veiculo(placaVeiculo);
                            System.out.println("Digite o seu ID de cliente.");
                            String idCliente = scanner.nextLine();
                     
                            for (Cliente cliente : e.getAllClientes()) {
                                if (idCliente.equals(cliente.getId())) {
                                    cliente.addVeiculo(veiculo);
                                    e.addVeiculo(veiculo, idCliente);
                                    System.out.println("Veículo adicionado com sucesso!");
                                    return;
                                }
                                else {
                                    throw new ExcecaoGeral()
                                            .setCodigoErro(CodigoCliente.CLIENTE_NAO_ENCONTRADO)
                                            .set("nome", "id")
                                            .set("valor", idCliente);
                                }
                            }
                     
                        }
            }
                
        } catch (ExcecaoGeral e) {
            JOptionPane.showMessageDialog(null, e, e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
// case 3: Estacionar
public static void estacionar(Scanner scanner) {
    try {
        System.out.println("Informe a placa do carro que você deseja estacionar: ");
        String placaVeiculo = scanner.nextLine();
        scanner.nextLine();

        for (Estacionamento estacionamento : estacionamentos) {
            if (estacionamento.getId() == estacionamentoUsado) {
                
                if (estacionamento.VeiculoExiste(placaVeiculo)) {
                    Veiculo veiculo = estacionamento.getAllClientes().stream()
                            .flatMap(cliente -> cliente.getVeiculos().stream())
                            .filter(v -> v.getPlaca().equals(placaVeiculo))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Erro ao obter veículo"));
        
                    estacionamento.estacionar(veiculo);
                    System.out.println("Veículo estacionado com sucesso.");
                }
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
// case 4: Sair Do Estacionamento com Carro
public static void sairDoEstacionamento(Scanner scanner) {
    try {
        System.out.println("Informe a placa do carro que você deseja retirar do estacionamento: ");
        String placaVeiculo = scanner.nextLine();
        scanner.nextLine();

        for (Estacionamento estacionamento : estacionamentos) {
            if (estacionamento.getId()==(estacionamentoUsado)) {
                
                if (estacionamento.VeiculoExiste(placaVeiculo)) {
                    Veiculo veiculo = estacionamento.getAllClientes().stream()
                            .flatMap(cliente -> cliente.getVeiculos().stream())
                            .filter(v -> v.getPlaca().equals(placaVeiculo))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Erro ao obter veículo"));
                            Plano plano = estacionamento.getAllClientes().stream()
                            .filter(cliente -> cliente.getVeiculos().stream().anyMatch(v -> v.getPlaca().equals(placaVeiculo)))
                            .findFirst()
                            .map(Cliente::getPlano)
                            .orElse(null);
                            
                    double valorPago = estacionamento.sair(veiculo, plano);
                    System.out.println("Veículo retirado do estacionamento. Valor pago: " + valorPago);
                }
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

  // case 5: Escolher serviços adicionais
  public static void servicosAdicionais(Scanner scanner) {
    System.out.println("Informe a placa do carro ao qual você deseja adicionar serviços: ");
    String placa = scanner.nextLine();
    scanner.nextLine();

    Veiculo veiculoDesejado = estacionamentos.stream()
            .filter(e -> e.getId() == estacionamentoUsado)
            .flatMap(e -> e.getAllClientes().stream())
            .flatMap(c -> c.getVeiculos().stream())
            .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
            .findFirst()
            .orElseThrow(() -> {
                throw new ExcecaoGeral()
                        .setCodigoErro(CodigoVeiculo.VEICULO_NAO_ENCONTRADO)
                        .set("nome", "placa")
                        .set("valor", placa);
            });

    System.out.println("Escolha os serviços desejados: ");
    int index = 1;
    for (UsoDeVaga.ServicoAdicional servico : UsoDeVaga.ServicoAdicional.values()) {
        System.out.println(index + ". " + servico.name() + " - R$ " + servico.getValor() +
                " (Tempo mínimo: " + servico.getTempoMinimo() + " minutos)");
        index++;
    }

    System.out.println("Digite o número do serviço desejado (ou 0 para voltar):");
    int escolha = scanner.nextInt();
    scanner.nextLine();

    if (escolha > 0 && escolha <= UsoDeVaga.ServicoAdicional.values().length) {
        UsoDeVaga.ServicoAdicional servicoEscolhido = UsoDeVaga.ServicoAdicional.values()[escolha - 1];
        LinkedList<UsoDeVaga> usos = (LinkedList<UsoDeVaga>) veiculoDesejado.getUsos();
        UsoDeVaga usoAtual = usos.getLast();
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

// case 6: Gerar relatório do cliente
public static void gerarRelatorioDoCliente(Scanner scanner) {
    System.out.println("Digite o nome do cliente que deseja gerar o relatório: ");
    String nomeCliente = scanner.nextLine();
    scanner.nextLine();

    Cliente clienteProcurado = estacionamentos.stream()
            .filter(e -> e.getId() == estacionamentoUsado)
            .flatMap(e -> e.getAllClientes().stream())
            .filter(cliente -> nomeCliente.equals(cliente.getNome()))
            .findFirst()
            .orElse(null);

    if (clienteProcurado == null) {
        System.out.println("Cliente não encontrado.");
        return;
    }

    System.out.println("Relatório do Cliente: " + clienteProcurado.getNome());
    System.out.println("ID: " + clienteProcurado.getId());
    System.out.println("Número de Veículos: " + clienteProcurado.getVeiculos().size());
    System.out.println("Total de Usos dos Veículos: " + clienteProcurado.getTotalUsos());
}
  
// case 7: Gerar Relatorio de Uso de Vaga de um veiculo
public static void gerarRelatorioUsoDeVaga(String placa) throws ExcecaoGeral {
    estacionamentos.stream()
            .filter(e -> e.getId() == estacionamentoUsado)
            .findFirst()
            .ifPresentOrElse(
                    estacionamento -> {
                        if (estacionamento.VeiculoExiste(placa)) {
                            estacionamento.getAllClientes().stream()
                                    .flatMap(c -> c.getVeiculos().stream())
                                    .filter(v -> v.getPlaca().equals(placa))
                                    .findFirst()
                                    .ifPresent(Veiculo::relatorioDeUsoDeVagasVeiculo);
                        } else {
                            throw new ExcecaoGeral()
                                    .setCodigoErro(CodigoVeiculo.VEICULO_NAO_ENCONTRADO)
                                    .set("nome", "placa")
                                    .set("valor", placa);
                        }
                    },
                    () -> {
                        throw new ExcecaoGeral()
                                .setCodigoErro(CodigoEstacionamento.ESTACIONAMENTO_NAO_ENCONTRADO)
                                .set("valor", estacionamentoUsado);
                    }
            );
}
  
    // case 8: Gerar relatório de arrecadação
    public static void arrecadacao(Scanner scanner) {
        Veiculo carro;
        System.out.println("PLACA DO VEÍCULO:");
        String placaVeiculo = scanner.nextLine();
        scanner.nextLine();
        for (Estacionamento e : estacionamentos) {
            if (e.getId() == estacionamentoUsado) {
                if (e.VeiculoExiste(placaVeiculo)) {
                    for (Cliente c : e.getAllClientes()) {
                        for (Veiculo v : c.getVeiculos()) {
                            if (v.getPlaca() == placaVeiculo) {
                                carro = v;
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
                } else {
                    System.out.println("Veículo não encontrado.");
                }
            }
        }
        
    }
    // case 9: Gerar relatório de arrecadação dos estacionamentos
    public static void arrecadacaoEstacionamento(Scanner scanner) {
        System.out.println("Escolha um relatório para ser gerado: ");
        System.out.println("1- Gerar relatório de arrecadação no mês.");
        System.out.println("2- Gerar relatório de arrecadação total.");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                System.out.println("Digite o número do mês desejado: ");
                int mes = scanner.nextInt();
                estacionamentos.stream()
                .sorted(Comparator.comparingDouble(e -> ((Estacionamento) e).arrecadadoNoMes(mes)).reversed())
                .forEach(estacionamento -> {
                    double arrecadadoNoMes = estacionamento.arrecadadoNoMes(mes);
                    System.out.println("Estacionamento " + estacionamento.getId() + ": " +
                            "Arrecadação no mês " + mes + ": " + arrecadadoNoMes); });
                break;

                case 2:
                estacionamentos.stream()
                        .sorted(Comparator.comparingDouble(Estacionamento::totalArrecadado).reversed())
                        .forEach(estacionamento -> {
                            System.out.println("Estacionamento " + estacionamento.getId() + ": " +
                                    "Total arrecadado: " + estacionamento.totalArrecadado());
                        });
                break;

            default:
                System.out.println("Opção inválida. Digite outra opção!");
                break;
        }
    }

    //case 10: Arrecadação média gerada pelos clientes horistas no mes
    public static void arrecadacaoMediaHoristas(Scanner scanner){
        int numHorista = 0;
        double arrecadacao = 0;
        for (Estacionamento estacionamento: estacionamentos){
            if(estacionamento.getId() == estacionamentoUsado){
            for(Cliente cliente : estacionamento.getAllClientes()){
                    if(cliente.getPlano() instanceof Horista){
                numHorista++;}}}}
        

        System.out.println("Digite o número do mês desejado: ");
        int mes = scanner.nextInt();
        for(Estacionamento estacionamento: estacionamentos){
            arrecadacao += estacionamento.arrecadadoNoMes(mes);
        }

        try{
        double resultado = arrecadacao/numHorista;

        System.out.println("A média gerada pelos horistas no mes " + mes + " foi " + resultado);
        }catch(Exception e){System.out.println("Não há horistas no estacionamento");}

    }

    //case 11: Uso médio dos clientes mensalistas
    public static void mediaUsoMensalistas(Scanner scanner){
        int numMensalista = 0;
        int numUsos = 0;
        System.out.println("Digite o número do mês desejado: ");
        int mes = scanner.nextInt();

        for (Estacionamento estacionamento: estacionamentos){
            System.out.println("Aa");
            if(estacionamento.getId() == estacionamentoUsado){
            for(Cliente cliente : estacionamento.getAllClientes()){
                    if(cliente.getPlano() instanceof Mensalista){
                        numMensalista++;
                    for(Veiculo veiculo: cliente.getVeiculos()){
                        for (UsoDeVaga usoDeVaga: veiculo.getUsos()){
                            if(usoDeVaga.getEntrada().getMonthValue() == mes){
                                numUsos++;
                            }
                        }
                    }}}}}

        try{
        double resultado = numUsos/numMensalista;
        System.out.println("A quantidade média de usos por clientes mensalistas no mes " + mes + " foi " + resultado);}
        catch(Exception e){
            System.out.println("Não há mensalistas no estacionamento");
        }
        
    }


    public static void main(String[] args) throws IOException {
        GenericDAO DAOe = new GenericDAO("Estacionamento.dat");
        if (DAOe.getAll() !=null) {
            
            setEstacionamentos(DAOe.getAll()); 
        }
        
        System.out.println("BEM-VINDO AO SISTEMA DE ESTACIONAMENTO\n");
        int escolhaEstacionamento = 0;
        Scanner scanner = new Scanner(System.in);
        if (estacionamentos == null || estacionamentos.isEmpty()) {
            estacionamentos = new ArrayList<>();  
            Estacionamento estacionamento1 = new Estacionamento("Renato Vagas", 30, 30, 1);
            estacionamentos.add(estacionamento1);
        
            Estacionamento estacionamento2 = new Estacionamento("Pedro Vagas", 30, 30, 2);
            estacionamentos.add(estacionamento2);
        
            Estacionamento estacionamento3 = new Estacionamento("Duda Vagas", 30, 30, 3);
            estacionamentos.add(estacionamento3);
        
            DAOe.saveToFile(estacionamentos);
        }
       
        while (escolhaEstacionamento < 1 || escolhaEstacionamento > 3) {
            System.out.println("ESCOLHA O ESTACIONAMENTO");
            System.out.println("1 - Estacionamento 1 - " + estacionamentos.get(0).getNome());
            System.out.println("2 - Estacionamento 2 - "+ estacionamentos.get(1).getNome());
            System.out.println("3 - Estacionamento 3 - "+ estacionamentos.get(2).getNome());

            escolhaEstacionamento = scanner.nextInt();
            //setEstacionamentos(DAOe.getAll());
           
            for (Estacionamento estacionamento : estacionamentos) {
                if (escolhaEstacionamento == estacionamento.getId()) {
                    estacionamentoUsado = estacionamento.getId();
                }
            }
        }
        menu(scanner, DAOe);
    }
}
