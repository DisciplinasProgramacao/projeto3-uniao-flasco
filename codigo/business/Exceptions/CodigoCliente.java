package business.Exceptions;

/**
 * Enumeração que define códigos de erro relacionados aos clientes.
 * Implementa a interface CodigoErro para padronizar o acesso aos códigos de erro.
 */
public enum CodigoCliente implements CodigoErro {
    
     /**
     * Código de erro para cliente não encontrado.
     * Número associado: 201.
     */
    CLIENTE_NAO_ENCONTRADO(201) {
        @Override
        public int getCodigo() {
            return 1;
        }
    },

    /**
     * Código de erro para cliente já existente.
     * Número associado: 202.
     */
    CLIENTE_JA_EXISTE(202) {
        @Override
        public int getCodigo() {
            return 2;
        }
    };

    private final int numero;

    /**
     * Construtor do CodigoCliente, recebe um número associado ao erro.
     *
     * @param numero Número associado ao código de erro.
     */
    private CodigoCliente(int numero) {
        this.numero = numero;
    }

    /**
     * Obtém o número associado ao código de erro.
     *
     * @return Número associado ao código de erro.
     */
    public int getNumero() {
        return numero;
    }

}
