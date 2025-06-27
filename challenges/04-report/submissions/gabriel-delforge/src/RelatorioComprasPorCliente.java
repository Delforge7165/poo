import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioComprasPorCliente {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java RelatorioComprasPorCliente <entrada> <saida>");
            System.exit(1);
        }

        Path inputPath  = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);

        ArrayList<String> clientes = new ArrayList<>();
        ArrayList<Double> totais   = new ArrayList<>();

        try {
            List<String> linhas = Files.readAllLines(inputPath);

            for (String linha : linhas) {
                String[] partes = linha.split(",");
                if (partes.length < 3) {
                    continue;
                }

                String nome     = partes[0].trim();
                String valorStr = partes[2].trim();
                double valor;

                try {
                    valor = Double.parseDouble(valorStr);
                } catch (NumberFormatException e) {
                    System.err.println("Valor inválido na linha: \"" + linha + "\"");
                    continue;
                }

                int idx = clientes.indexOf(nome);
                if (idx >= 0) {
                    totais.set(idx, totais.get(idx) + valor);
                } else {
                    clientes.add(nome);
                    totais.add(valor);
                }
            }

            ArrayList<String> saida = new ArrayList<>();
            for (int i = 0; i < clientes.size(); i++) {
                saida.add(clientes.get(i) + ": " + totais.get(i));
            }

            Files.write(outputPath, saida);
            System.out.println("Relatório gerado em: " + outputPath);

        } catch (IOException e) {
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
            System.exit(2);
        }
    }
}
