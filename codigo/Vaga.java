import java.io.Serializable;

public class Vaga implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private boolean disponivel;
	

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
		String numeroVaga = "" + fila + numero;
		
		setId(numeroVaga);
	}

	/**
     * Tenta estacionar um veículo na vaga.
     * 
     * @return true se o estacionamento foi bem-sucedido, false caso contrário.
     */
	public boolean estacionar() {
		if(disponivel() == true)
		{
			return true;
		}
		else return false;
	}

	/**
     * Tenta retirar um veículo da vaga.
     * 
     * @return true se a saída foi bem-sucedida, false caso contrário.
     */
	public boolean sair() {
		if(disponivel()== true)
		{
			return false;
		}
		else return true;
	}

	/**
     * Verifica se a vaga está disponível.
     * 
     * @return true se a vaga está disponível, false caso contrário.
     */
	public boolean disponivel() {
		if(isDisponivel() == true)
		{
			return true;
		}
		else return false;
	}

}
