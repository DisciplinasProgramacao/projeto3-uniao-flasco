package business.Vaga;

import java.io.Serializable;

public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private boolean disponivel;
    

    public enum LetraFila {
        A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10), K(11), L(12), M(13), N(14), O(15), P(16), Q(17),
        R(18), S(19), T(20), U(21), V(22), W(23), X(24), Y(25), Z(26), Aa(27), Bb(28), Cc(29), Dd(30);

        private final int valor;

        LetraFila(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    /**
     * Obtém o ID da vaga.
     * 
     * @return O ID da vaga.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Verifica se a vaga está disponível.
     * 
     * @return true se disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Construtor que cria uma vaga com base na fila e número.
     * 
     * @param fila   Letra da fila onde a vaga está localizada.
     * @param numero Número da vaga na fila especificada.
     */
    public Vaga(int fila, int numero) {
        this.id = "" + LetraFila.values()[fila -1]+ numero;
        this.disponivel = true; // Por padrão a vaga está disponível quando criada
    }

    /**
     * Estaciona um veículo na vaga.
     *
     * @param placa A placa do veículo a ser estacionado.
     * @return true se o veículo foi estacionado com sucesso, false caso contrário.
     */
    public boolean estacionar(String placa) {
        if (isDisponivel()) {
            setDisponivel(false);
            
            return true;
        }
        return false;
    }

    /**
     * Remove um veículo da vaga.
     *
     * @return true se o veículo foi removido com sucesso, false caso contrário.
     */
    public boolean sair() {
        if (!isDisponivel()) {
            setDisponivel(true);
            
            return true;
        }
        return false;
    }
}
