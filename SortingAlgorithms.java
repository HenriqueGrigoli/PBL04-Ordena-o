/**
 * Uma classe utilitária que agrupa os algoritmos de ordenação.
 * Todos os métodos são estáticos e podem ser chamados diretamente
 * sem a necessidade de instanciar a classe.
 * Ex: SortingAlgorithms.bubbleSort(meuArray);
 */
public class SortingAlgorithms {

    /**
     * Ordena um array de inteiros usando o algoritmo Bubble Sort.
     * @param data O array a ser ordenado.
     */
    public static void bubbleSort(int[] data) {
        int n = data.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    // Troca os elementos
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
            // Se não houve trocas, o array já está ordenado.
            if (!swapped) break;
        }
    }

    /**
     * Ordena um array de inteiros usando o algoritmo Insertion Sort.
     * @param data O array a ser ordenado.
     */
    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
    }

    /**
     * Ordena um array de inteiros usando o algoritmo Quick Sort.
     * Este é o método público que inicia a chamada recursiva.
     * @param data O array a ser ordenado.
     */
    public static void quickSort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        quickSortRecursive(data, 0, data.length - 1);
    }

    /**
     * Método recursivo privado para o QuickSort.
     */
    private static void quickSortRecursive(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortRecursive(arr, low, pi - 1);
            quickSortRecursive(arr, pi + 1, high);
        }
    }

    /**
     * Método de partição para o QuickSort.
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}