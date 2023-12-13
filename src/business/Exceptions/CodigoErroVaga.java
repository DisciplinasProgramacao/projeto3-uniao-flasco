package business.Exceptions;

/**
 * Enumeração que define o código de erro relacionado à vaga não finalizada.
 * Implementa a interface CodigoErro para fornecer métodos de acesso ao código de erro e seu número associado.
 */
public enum CodigoErroVaga implements CodigoErro {

    /**
     * Código de erro indicando que a vaga não foi finalizada corretamente.
     */
    VAGA_NAO_FINALIZADA(401) {
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
    private CodigoErroVaga(int numero) {
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

