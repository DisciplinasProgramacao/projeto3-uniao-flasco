package business.Exceptions;

/**
 * Enumeração que define o código de erro relacionado ao tempo mínimo.
 * Implementa a interface CodigoErro para fornecer métodos de acesso ao código de erro e seu número associado.
 */
public enum CodigoErroTempoMinimo implements CodigoErro {

    /**
     * Código de erro indicando que o tempo mínimo não foi atendido.
     */
    TEMPO_MINIMO(701) {
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
    private CodigoErroTempoMinimo(int numero) {
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

    
