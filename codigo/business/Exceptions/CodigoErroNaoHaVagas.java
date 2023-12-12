package business.Exceptions;

/**
 * Enumeração que define o código de erro relacionado à falta de vagas.
 * Implementa a interface CodigoErro para fornecer métodos de acesso ao código de erro e seu número associado.
 */
public enum CodigoErroNaoHaVagas implements CodigoErro {

    /**
     * Código de erro indicando que não há vagas disponíveis.
     */
    NAO_HA_VAGAS(501) {
        @Override
        public int getCodigo() {
            return 1;
        }
    };

    private final int numero;

    /**
     * Construtor privado para associar um número ao código de erro.
     *
     * @param numero Número associado ao código de erro.
     */
    private CodigoErroNaoHaVagas(int numero) {
        this.numero = numero;
    }

    /**
     * Obtém o número associado ao código de erro.
     *
     * @return Número associado ao código de erro.
     */
    @Override
    public int getNumero() {
        return numero;
    }
}

    
