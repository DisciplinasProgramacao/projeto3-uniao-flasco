package business.Estacionamento;



import java.io.*;
import java.util.ArrayList;
import java.util.List;


import business.Interfaces.DAO;

/**
 * Classe EstacionamentoDAO
 * 
 * @author RenatoMAP77
 *
 */
public class GenericDAO implements DAO<Estacionamento, Integer> {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public GenericDAO(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            
            file.createNewFile();
        }
        fos = new FileOutputStream(file, false); 
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Estacionamento estacionamento) {
        try {
            outputFile.writeObject(estacionamento);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o estacionamento '" + estacionamento.getNome() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Estacionamento get(Integer chave) {
        Estacionamento estacionamento = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();

                if (chave.equals(estacionamento.getId())) {
                    return estacionamento;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o estacionamento '" + chave + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Estacionamento> getAll() {
        List<Estacionamento> estacionamentos = new ArrayList<>();
        Estacionamento estacionamento = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();
                estacionamentos.add(estacionamento);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar estacionamento no disco!");
            e.printStackTrace();
        }
        return estacionamentos;
    }

    public void update(Estacionamento e) {
        List<Estacionamento> estacionamentos = getAll();
        int index = estacionamentos.indexOf(e);
        if (index != -1) {
            estacionamentos.set(index, e);
        }
        saveToFile(estacionamentos);
    }

    public void delete(Estacionamento e) {
        List<Estacionamento> estacionamentos = getAll();
        int index = estacionamentos.indexOf(e);
        if (index != -1) {
            estacionamentos.remove(index);
        }
        saveToFile(estacionamentos);
    }

    public void saveToFile(List<Estacionamento> estacionamentos) {
        try {
            close();
            fos = new FileOutputStream(file, false); 
            outputFile = new ObjectOutputStream(fos);

            for (Estacionamento estacionamento : estacionamentos) {
                outputFile.writeObject(estacionamento);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar estacionamento no disco!");
            e.printStackTrace();
        }
    }
    
    private void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}
