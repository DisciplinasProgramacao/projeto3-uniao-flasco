package business.Interfaces;

/**
 * Interface que define um objeto observável, permitindo adição, remoção e notificação de observadores.
 */
public interface Observavel {

    /**
     * Adiciona um observador à lista de observadores deste objeto observável.
     *
     * @param observador O observador a ser adicionado.
     */
    public void addObservador(Observador observador);

    /**
     * Remove um observador da lista de observadores deste objeto observável.
     *
     * @param observador O observador a ser removido.
     */
    public void removeObservador(Observador observador);

    /**
     * Notifica todos os observadores registrados sobre uma mudança ou evento neste objeto observável.
     */
    public void notificarObservadores();
}
