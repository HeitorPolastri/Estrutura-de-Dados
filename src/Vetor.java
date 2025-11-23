public class Vetor {
    private int[] dados;
    private int tamanho = 0;

    public Vetor(int capacidade) {
        dados = new int[capacidade];
    }

    public void inserir(int valor) {
        if (tamanho < dados.length) {
            dados[tamanho++] = valor;
        }
    }

    public int buscaSequencial(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i] == valor) return i;
        }
        return -1;
    }

    public void bubbleSort() {
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (dados[j] > dados[j + 1]) {
                    int temp = dados[j];
                    dados[j] = dados[j + 1];
                    dados[j + 1] = temp;
                }
            }
        }
    }

    public void quickSort() {
        quickSort(0, tamanho - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = dados[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (dados[j] < pivot) {
                i++;
                int temp = dados[i];
                dados[i] = dados[j];
                dados[j] = temp;
            }
        }
        int temp = dados[i + 1];
        dados[i + 1] = dados[high];
        dados[high] = temp;
        return i + 1;
    }
}