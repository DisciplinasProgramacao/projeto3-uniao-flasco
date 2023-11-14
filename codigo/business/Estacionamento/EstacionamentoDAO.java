package business.Estacionamento;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import business.Interfaces.DAO;

public class EstacionamentoDAO implements DAO<Estacionamento, Integer> {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public EstacionamentoDAO(String filename) throws IOException {
        file = new File(filename);
      
        fos = new FileOutputStream(file, true);
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Estacionamento estacionamento) {
        try {
            outputFile.writeObject(estacionamento);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o estacionamento com ID '" + estacionamento.getId() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Estacionamento get(Integer id) {
        Estacionamento estacionamento = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();

                if (id.equals(estacionamento.getId())) {
                    return estacionamento;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o estacionamento com ID '" + id + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Estacionamento> getAll() {
        List<Estacionamento> estacionamentos = new ArrayList<Estacionamento>();
        Estacionamento estacionamento = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                estacionamento = (Estacionamento) inputFile.readObject();
                estacionamentos.add(estacionamento);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao dar getALL!");
            e.printStackTrace();
        }
        return estacionamentos;
    }

    public void update(Estacionamento estacionamento) {
        List<Estacionamento> estacionamentos = getAll();
        int index = -1;
        for (int i = 0; i < estacionamentos.size(); i++) {
            if (estacionamentos.get(i).getId() == estacionamento.getId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            estacionamentos.set(index, estacionamento);
        }
        saveToFile(estacionamentos);
    }

    public void delete(Estacionamento estacionamento) {
        List<Estacionamento> estacionamentos = getAll();
        int index = -1;
        for (int i = 0; i < estacionamentos.size(); i++) {
            if (estacionamentos.get(i).getId() == estacionamento.getId()) {
                index = i;
                break;
            }
        }

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

            for (Estacionamento est : estacionamentos) {
                outputFile.writeObject(est);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar estacionamento no disco!");
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}
