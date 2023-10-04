

public class Vaga {

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

	public Vaga(int fila, int numero) {
		int valor = fila + numero;
		setId("" + valor);
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
