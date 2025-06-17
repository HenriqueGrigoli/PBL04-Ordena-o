import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Classe principal para comparar o desempenho dos algoritmos de ordenação.
 */
public class SortingComparison {

    public static void main(String[] args) {
        String[] files = {
                "aleatorio_100.csv", "aleatorio_1000.csv", "aleatorio_10000.csv",
                "crescente_100.csv", "crescente_1000.csv", "crescente_10000.csv",
                "decrescente_100.csv", "decrescente_1000.csv", "decrescente_10000.csv"
        };

        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-25s | %-15s | %-15s | %-15s%n", "Conjunto de Dados", "Bubble Sort", "Insertion Sort", "Quick Sort");
        System.out.println("-------------------------------------------------------------------------");

        for (String file : files) {
            try {
                int[] originalData = readDataFromCSV(file);
                if (originalData == null) {
                    System.out.printf("Não foi possível ler o arquivo %s. Pulando...%n", file);
                    continue;
                }

                // --- Medição do Bubble Sort ---
                int[] dataForBubble = Arrays.copyOf(originalData, originalData.length);
                long startTimeBubble = System.nanoTime();
                SortingAlgorithms.bubbleSort(dataForBubble);
                long endTimeBubble = System.nanoTime();
                long bubbleTime = endTimeBubble - startTimeBubble;

                // --- Medição do Insertion Sort ---
                int[] dataForInsertion = Arrays.copyOf(originalData, originalData.length);
                long startTimeInsertion = System.nanoTime();
                SortingAlgorithms.insertionSort(dataForInsertion);
                long endTimeInsertion = System.nanoTime();
                long insertionTime = endTimeInsertion - startTimeInsertion;

                // --- Medição do Quick Sort ---
                int[] dataForQuick = Arrays.copyOf(originalData, originalData.length);
                long startTimeQuick = System.nanoTime();
                SortingAlgorithms.quickSort(dataForQuick);
                long endTimeQuick = System.nanoTime();
                long quickTime = endTimeQuick - startTimeQuick;

                // Formata e imprime os resultados
                System.out.printf("%-25s | %-15s | %-15s | %-15s%n",
                        file,
                        formatTime(bubbleTime),
                        formatTime(insertionTime),
                        formatTime(quickTime));

            } catch (IOException e) {
                System.err.println("Erro ao processar o arquivo " + file + ": " + e.getMessage());
            } catch(StackOverflowError e) {
                // Quick Sort pode causar estouro de pilha com dados ordenados ou inversamente ordenados
                System.err.printf("%-25s | %-15s | %-15s | %-15s%n", file, "OK", "OK", "StackOverflow");
            }
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    /**
     * Lê números inteiros de um arquivo CSV.
     */
    public static int[] readDataFromCSV(String filename) throws IOException {
        List<Integer> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    dataList.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    // Ignora linhas inválidas
                }
            }
        }
        return dataList.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Formata o tempo de nanossegundos para um formato legível (ms, µs, ou ns).
     */
    public static String formatTime(long nanos) {
        if (nanos > 1_000_000) {
            return TimeUnit.NANOSECONDS.toMillis(nanos) + " ms";
        }
        if (nanos > 1_000) {
            return TimeUnit.NANOSECONDS.toMicros(nanos) + " µs";
        }
        return nanos + " ns";
    }
}