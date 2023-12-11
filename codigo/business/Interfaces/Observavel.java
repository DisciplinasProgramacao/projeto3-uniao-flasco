package business.Interfaces;

/**
 * Observavel
 */
public interface Observavel {
    

    public void addObservador (Observador observador);
    public void removeObservador (Observador observador);
    public void notificarObservadores ();
    
    
}