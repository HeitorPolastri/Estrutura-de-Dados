package Estruturadedados;

public class Vetor {
    private int[] elementos;
    private int tamanho;

    public Vetor(int capacidade) {
        
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

    public int buscar(int valor) {
        for (int i = 0; i < this.tamanho; i++) {
            if (this.elementos[i] == valor) {
                
                return i; 

            }
        }
        
        return -1; 

    }

    private void aumentarVetor() {
        int[] novoArray = new int[this.elementos.length * 2];
        for (int i = 0; i < this.tamanho; i++) {
            
            novoArray[i] = this.elementos[i];

        }
        
        this.elementos = novoArray;

    }
}