package business.Estacionamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import business.Cliente.Cliente;

import business.Interfaces.Observador;
import business.Interfaces.Observavel;

public class Relatorio implements Serializable, Observador {
    private Map<MesEnum, Set<Cliente>> top5Clientes;
    private List<Observavel> veiculosObservados;
    private Estacionamento estacionamento;

    public Relatorio(Estacionamento estacionamento, Observavel observavel) {
        this.estacionamento = estacionamento;
        veiculosObservados = new ArrayList<>();
        this.veiculosObservados.add(observavel);
        observavel.addObservador(this);
        top5Clientes = new HashMap<>();
    }

    public Map<MesEnum, Set<Cliente>> getTop5Clientes() {
        return top5Clientes;
    }

    public void setTop5Clientes(Map<MesEnum, Set<Cliente>> top5Clientes) {
        this.top5Clientes = top5Clientes;
    }

    public List<Observavel> getVeiculosObservados() {
        return veiculosObservados;
    }

    public void setVeiculosObservados(List<Observavel> veiculosObservados) {
        this.veiculosObservados = veiculosObservados;
    }

    @Override
    public void update(int mes) {
        MesEnum mesEnum = MesEnum.fromInt(mes); 
        
        Set<Cliente> topClientes = estacionamento.top5Clientes(mesEnum);
        top5Clientes.put(mesEnum, topClientes);
    }

    public Set<Cliente> getTop5ClientesNoMes(int mes){
        MesEnum mesEnum = MesEnum.fromInt(mes); 
        Set<Cliente> topClientes = top5Clientes.get(mesEnum);
        return topClientes;
    }
}
