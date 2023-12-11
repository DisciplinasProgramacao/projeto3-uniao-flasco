package business.Plano;

import java.io.Serializable;

/**
 * Classe abstrata que representa um plano de estacionamento genérico.
 * Serve como base para implementar diferentes tipos de planos de estacionamento.
 */
public abstract class Plano implements Serializable {
   private String tipo;
  
  /**
     * Construtor que define o tipo de plano ao ser instanciado.
     *
     * @param tipo O tipo de plano.
     */
   public Plano(String tipo){
     setTipo(tipo);
   }

  /**
     * Define o tipo de plano.
     *
     * @param tipo O tipo de plano a ser definido.
     */
   public void setTipo(String tipo){
     this.tipo = tipo;
   }

  /**
     * Obtém o tipo de plano.
     *
     * @return O tipo de plano.
     */
   public String getTipo(){
    return this.tipo;
   }
  
}
  

