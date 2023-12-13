package business.Plano;


/**
 * Representa o plano de estacionamento horista, onde o cliente paga por horas utilizadas.
 * Herda da classe abstrata Plano.
 */
public class Horista extends Plano {

  /**
    * Construtor padrão que define o tipo como "Horista".
    */
  public Horista(){
    super("Horista");
  }

    /**
     * Construtor que permite definir o tipo de plano.
     *
     * @param tipo O tipo de plano.
     */
    public Horista(String tipo){
      super(tipo);
    }

 }