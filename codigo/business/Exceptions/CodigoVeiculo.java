package business.Exceptions;

/**
 * Enumeração que define códigos de erro relacionados a operações com veículos.
 * Implementa a interface CodigoErro para fornecer métodos de acesso aos códigos de erro e seus números associados.
 */
public enum CodigoVeiculo implements CodigoErro {

    /**
     * Código de erro indicando que o veículo já existe.
     */
    VEICULO_JA_EXISTE(301) {
        @Override
        public int getCodigo() {
            return 1;
        }
    },

    /**
     * Código de erro indicando que o veículo não foi encontrado.
     */
    VEICULO_NAO_ENCONTRADO(302) {
        @Override
        public int getCodigo() {
            return 2;
        }
    };

    private final int numero;

    /**
     * Construtor privado para associar um número a cada código de erro.
     *
     * @param numero Número associado ao código de erro.
     */
    private CodigoVeiculo(int numero) {
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

    
