import java.io.Serializable;

public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private boolean disponivel;
    private String veiculoEstacionado;


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
     * @param fila    Letra da fila onde a vaga está localizada.
     * @param numero  Número da vaga na fila especificada.
     */
    public Vaga(char fila, int numero) {
        this.id = "" + fila + numero;
        this.disponivel = true; // Por padrão a vaga está disponível quando criada
    }

    public String getVeiculoEstacionado() {
        return veiculoEstacionado;
    }

    public void setVeiculoEstacionado(String veiculoEstacionado) {
        this.veiculoEstacionado = veiculoEstacionado;
    }

    public boolean estacionar(String placa) {
        if(isDisponivel()) {
            setDisponivel(false);
            setVeiculoEstacionado(placa);
            return true;
        }
        return false;
    }

    public boolean sair() {
        if(!isDisponivel()) {
            setDisponivel(true);
            setVeiculoEstacionado(null);
            return true;
        }
        return false;
    }

}
