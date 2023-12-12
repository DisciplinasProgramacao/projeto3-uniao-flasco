package business.Exceptions;

/**
 * Interface que define métodos para obtenção de códigos de erro.
 * Implementada por diferentes enumerações de códigos de erro para padronizar o acesso aos códigos.
 */
public interface CodigoErro {

	/**
     * Obtém o código de erro associado.
     *
     * @return Código de erro.
     */
	public int getCodigo();

	/**
     * Obtém o número associado ao código de erro.
     *
     * @return Número associado ao código de erro.
     */
	public int getNumero();
}
