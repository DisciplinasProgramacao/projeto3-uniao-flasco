package business.Plano;

import java.io.Serializable;

/**
 * Representa o plano de estacionamento mensalista.
 * Herda da classe abstrata Plano.
 */
public class Mensalista extends Plano implements Serializable {

    /**
     * Construtor que permite definir o tipo de plano.
     *
     * @param tipo O tipo de plano.
     */
    public Mensalista(String tipo) {
        super(tipo);
        
    }
    
}
