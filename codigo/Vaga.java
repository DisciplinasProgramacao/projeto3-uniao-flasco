
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Vaga(char fila, int numero) {
		String numeroVaga = "" + fila + numero;
		
		setId(numeroVaga);
	}

	public boolean estacionar() {
		if(disponivel() == true)
		{
			return true;
		}
		else return false;
	}

	public boolean sair() {
		if(disponivel()== true)
		{
			return false;
		}
		else return true;
	}

	public boolean disponivel() {
		if(isDisponivel() == true)
		{
			return true;
		}
		else return false;
	}

}
