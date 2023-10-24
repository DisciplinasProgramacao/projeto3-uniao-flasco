import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements DAO<Veiculo, String> {

    private static final String ARQUIVO = "Veiculo.txt";

    @Override
    public void add(Veiculo veiculo) {
        try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String separadorDeLinha = System.getProperty("line.separator");
            buffer_saida.write(veiculo.getPlaca() + ";");
            // Adicione mais atributos do Veiculo aqui, se necessário.
            buffer_saida.newLine();
        } catch (IOException e) {
            System.out.println("ERRO ao gravar o Veiculo no disco!");
            e.printStackTrace();
        }
    }

    @Override
    public Veiculo get(String chave) {
        Veiculo veiculo = null;
        String linha = null;

        try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO))) {
            while ((linha = buffer_entrada.readLine()) != null) {
                String[] partes = linha.split(";");
                String placa = partes[0];
                if (chave.equals(placa)) {
                    veiculo = new Veiculo(placa);
                    // Preencha os atributos do Veiculo a partir das partes do arquivo, se necessário.
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO ao ler o Veiculo do disco rígido!");
            veiculo = null;
            e.printStackTrace();
        }
        return veiculo;
    }

    @Override
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
            saveToFile(veiculos);
        }
    }

    @Override
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
            saveToFile(veiculos);
        }
    }

    @Override
    public List<Veiculo> getAll() {
        List<Veiculo> veiculos = new ArrayList<>();
        String linha = null;

        try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO))) {
            while ((linha = buffer_entrada.readLine()) != null) {
                String[] partes = linha.split(";");
                String placa = partes[0];
                Veiculo veiculo = new Veiculo(placa);
                // Preencha os atributos do Veiculo a partir das partes do arquivo, se necessário.
                veiculos.add(veiculo);
            }
        } catch (IOException e) {
            System.out.println("ERRO ao ler os Veiculos do disco rígido!");
            e.printStackTrace();
        }
        return veiculos;
    }

    private void saveToFile(List<Veiculo> veiculos) {
        try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            String separadorDeLinha = System.getProperty("line.separator");
            for (Veiculo veiculo : veiculos) {
                buffer_saida.write(veiculo.getPlaca() + ";");
                // Adicione mais atributos do Veiculo aqui, se necessário.
                buffer_saida.newLine();
            }
        } catch (IOException e) {
            System.out.println("ERRO ao gravar os Veiculos no disco!");
            e.printStackTrace();
        }
    }
}
