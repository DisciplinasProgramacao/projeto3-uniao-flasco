package business.Interfaces;

/**
 * Interface que define um observador para notificação de eventos.
 */
public interface Observador {

    /**
     * Método chamado para notificar o observador sobre uma atualização ocorrida em um determinado mês.
     *
     * @param mes O mês associado à atualização.
     */
    public void update(int mes);
    
    public void setObservavel(Observavel v);

    public void removeObservavel(Observavel v);
}

