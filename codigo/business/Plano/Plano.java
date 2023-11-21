package business.Plano;

public class Plano {
   private String tipo;
  
   public Plano(String tipo)
   {
     settipo(tipo);
   }
   public void settipo(String tipo)
   {
     this.tipo = tipo;
   }
   public String getTipo()
   {
    return this.tipo;
   }
}