package business.Veiculo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import business.Exceptions.CodigoErroNaoHaVagas;
import business.Exceptions.CodigoErroVagaFinalizada;
import business.UsoDeVaga.UsoDeVaga;
import business.UsoDeVaga.UsoDeVaga.ServicoAdicional;
import business.Vaga.Vaga;

public class Veiculo implements Serializable {
    private String placa;
    private List<UsoDeVaga> usos;
   
    public Veiculo(String placa) {
        setPlaca(placa);
        this.usos = new ArrayList<>();
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setUsos(List<UsoDeVaga> usos) {
        this.usos = usos;
    }

    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    public void estacionar(Vaga vaga) throws CodigoErroNaoHaVagas{
        if (vaga == null || !vaga.isDisponivel())
            throw new CodigoErroNaoHaVagas();

        if (vaga.isDisponivel()) {
            vaga.setDisponivel(false);
            LocalDateTime entrada = LocalDateTime.now();
            UsoDeVaga uso = new UsoDeVaga(vaga, entrada);
            usos.add(uso);
        }
    }

    public double sair(Vaga vaga) throws CodigoErroVagaFinalizada {
        double valor = 0.0;

            for (UsoDeVaga usoDeVaga : usos) {
                if (vaga.equals(usoDeVaga.getVaga())) {
                    LocalDateTime saida = LocalDateTime.now();
                    valor = usoDeVaga.sair(saida);
                    break;
                }   
            
                else {
                    throw new CodigoErroVagaFinalizada();
                }
            }
            
        return valor;
    }

    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (UsoDeVaga usoDeVaga : usos) {
            totalArrecadado += usoDeVaga.getValorPago();
        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0.0;
        for (UsoDeVaga usoDeVaga : usos) {
            LocalDateTime entrada = usoDeVaga.getEntrada();
            Month monthOfYear = Month.of(entrada.getMonthValue());
            int mesEntrada = monthOfYear.getValue();
            if (mesEntrada == mes) {
                arrecadadoNoMes += usoDeVaga.getValorPago();
            }
        }
        return arrecadadoNoMes;
    }

    public int totalDeUsos() {
        return usos.size();
    }


    public void relatorioDeUsoDeVagasVeiculo(){
       
        System.out.println("Placa: " + placa);
        for (UsoDeVaga u : usos) {
            System.out.println("Vaga: " + u.getVaga().getId());
            System.out.println("Entrada: " + u.getEntrada());
            System.out.println("Saída: " + u.getSaida());
            System.out.println("Valor pago: " + u.getValorPago());
            
            List<ServicoAdicional> servicos = u.getServicosAdicionais();
            if (servicos.size() > 0) {
                System.out.println("Serviços adicionais:");
                for (ServicoAdicional servico : servicos) {
                    System.out.println(servico);
                }
            }
            
            
        }

    }


    public boolean contains(Veiculo veiculo) {
        return false;
    }

}
