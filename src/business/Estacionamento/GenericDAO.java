package business.Estacionamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> {

    private final String arquivo = "Estacionamento.dat";
    private List<T> objetos;

    public GenericDAO() {
        this.objetos = new ArrayList<>();
        carregarDados();
    }

    public void add(T objeto) {
        objetos.add(objeto);
        salvarDados();
    }

    public List<T> getAll() {
        return new ArrayList<>(objetos);
    }

    public void atualizarDados(List<T> novosObjetos) {
        objetos = novosObjetos;
        salvarDados();
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(objetos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                objetos = (List<T>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar exceções na leitura
        }
    }
}
