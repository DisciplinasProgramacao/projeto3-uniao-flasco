package business.Estacionamento;

import java.util.List;

import business.Cliente.Cliente;
import business.Veiculo.Veiculo;

//Classe para utilizar o padrão observer, possui uma lista de objetos que estão sendo observados, e atualiza o seu
//Método top5Clientes a cada vez que um veiculo sai do estacionamento
public class Relatorio {
    //LISTA PARA ARMAZENAR OS TOP 5 CLIENTES
    // Fazer m map<mes, lista clientes>
    private List<Cliente> top5Clientes;
    //LISTA DE OBJETOS OBSERVADOS
    private List<Veiculo> veiculosObservados;
    private Estacionamento estacionamento;

    public List<Cliente> getTop5Clientes() {
        return top5Clientes;
    }

    public void setTop5Clientes(List<Cliente> top5Clientes) {
        this.top5Clientes = top5Clientes;
    }

    public List<Veiculo> getVeiculosObservados() {
        return veiculosObservados;
    }

    public void setVeiculosObservados(List<Veiculo> veiculosObservados) {
        this.veiculosObservados = veiculosObservados;
    }

    public Relatorio(List<Cliente> top5Clientes, List<Veiculo> veiculosObservados) {
        setTop5Clientes(top5Clientes);
        setVeiculosObservados(veiculosObservados);
    }
    //ARRUMAR ISSO
    public void update(){
        top5Clientes = Estacionamento.top5Clientes(0);
    }
    
}
