package business;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;



public class VeiculoDAO implements DAO<Veiculo, String> {
    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public VeiculoDAO(String filename) throws IOException {
        file = new File(filename);
       
        fos = new FileOutputStream(file, false);
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Veiculo veiculo) {
        try {
            outputFile.writeObject(veiculo);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o veículo com placa '" + veiculo.getPlaca() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Veiculo get(String placa) {
        Veiculo veiculo = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                veiculo = (Veiculo) inputFile.readObject();

                if (placa.equals(veiculo.getPlaca())) {
                    return veiculo;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o veículo com placa '" + placa + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Veiculo> getAll() {
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        Veiculo veiculo = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                veiculo = (Veiculo) inputFile.readObject();
                veiculos.add(veiculo);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar veículo no disco!");
            e.printStackTrace();
        }
        return veiculos;
    }

    public void update(Veiculo veiculo) {
        List<Veiculo> veiculos = getAll();
        int index = -1;
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            veiculos.set(index, veiculo);
        }
        saveToFile(veiculos);
    }

    public void delete(Veiculo veiculo) {
        List<Veiculo> veiculos = getAll();
        int index = -1;
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            veiculos.remove(index);
        }
        saveToFile(veiculos);
    }

    public void saveToFile(List<Veiculo> veiculos) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Veiculo veiculo : veiculos) {
                outputFile.writeObject(veiculo);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar veículo no disco!");
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
