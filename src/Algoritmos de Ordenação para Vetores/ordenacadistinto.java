public class ordenacadistinto {
    private int[] elementos;
    private int tamanho;

    public ordenacadistinto(int capacidade) {
        this.elementos = new int[capacidade];
        this.tamanho = 0;
    }

    public void inserir(int valor) {
        if (this.tamanho == this.elementos.length) {
            aumentarVetor();
        }
        this.elementos[this.tamanho] = valor;
        this.tamanho++;
    }

    private void aumentarVetor() {
        int[] novoArray = new int[this.elementos.length * 2];
        for (int i = 0; i < this.tamanho; i++) {
            novoArray[i] = this.elementos[i];
        }
        this.elementos = novoArray;
    }

  

    // Bubble sort 
    public void bubbleSort() {
        for (int i = 0; i < tamanho - 1; i++) {

            for (int j = 0; j < tamanho - 1 - i; j++) {

                if (elementos[j] > elementos[j + 1]) {

                    
                    int aux = elementos[j];

                    elementos[j] = elementos[j + 1];

                    elementos[j + 1] = aux;

                }
            }
        }
    }

    // Quick sort 
    public void quickSort() {
        quickSortRecursivo(0, tamanho - 1);
    }

    private void quickSortRecursivo(int inicio, int fim) {
        if (inicio < fim) {
            int pivo = particionar(inicio, fim);
            quickSortRecursivo(inicio, pivo - 1);
            quickSortRecursivo(pivo + 1, fim);
        }
    }

    private int particionar(int inicio, int fim) {

        int pivo = elementos[fim];

        int i = inicio - 1;
        
        for (int j = inicio; j < fim; j++) {

            if (elementos[j] < pivo) {

                i++;
                
                int aux = elementos[i];

                elementos[i] = elementos[j];

                elementos[j] = aux;

            }
        }
        
        int aux = elementos[i + 1];

        elementos[i + 1] = elementos[fim];

        elementos[fim] = aux;
        
        return i + 1;
        
    }
}