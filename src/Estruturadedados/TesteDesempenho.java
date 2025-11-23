package Estruturadedados;

import java.util.Random;

public class TesteDesempenho {

    public static void main(String[] args) {

        int[] tamanhos = { 100, 1000, 10000 };

        for (int tamanho : tamanhos) {
        
            System.out.println("Teste com tamanho: " + tamanho);
            System.out.println("----------------------------------------------");

            
            int[] dadosOrdenados = gerarOrdenado(tamanho);

            int[] dadosInversos  = gerarOrdenadoinverso(tamanho);

            int[] dadosAleatorios = gerarAleatorio(tamanho);

        
            executarTeste("ORDENADA", dadosOrdenados);

            executarTeste("INVERSAMENTE ORDENADA", dadosInversos);

            executarTeste("ALEATÓRIA", dadosAleatorios);
            
            System.out.println("\n");
        }
    }

    private static void executarTeste(String cenario, int[] dados) {

        System.out.println("\n>> Ordem de Inserção: " + cenario);


        Vetor vetor = new Vetor(10); 
        long inicio = System.nanoTime();

        for (int valor : dados) {

            vetor.inserir(valor);

        }
        long fim = System.nanoTime();

        System.out.printf("Vetor: %.3f ms%n", (fim - inicio) / 1_000_000.0);


    
        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();

        inicio = System.nanoTime();
        try {

            for (int valor : dados) {
                abb.inserir(valor);

            }
            fim = System.nanoTime();

            System.out.printf("ArvoreBinariaBusca:   %.4f ms%n", (fim - inicio) / 1_000_000.0);
        } catch (StackOverflowError e) {
            System.out.println("ArvoreBinariaBusca:ERRO (Árvore desbalanceada)");
        }

      
        ArvoreAVL avl = new ArvoreAVL();
        inicio = System.nanoTime();
        for (int valor : dados) {
            avl.inserir(valor);
        }
        fim = System.nanoTime();

        System.out.printf("AVL:   %.4f ms%n", (fim - inicio) / 1_000_000.0);
    }


    private static int[] gerarOrdenado(int tamanho) {
        int[] array = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            array[i] = i;

        }
        return array;
    }

    
    private static int[] gerarOrdenadoinverso(int tamanho) {
        int[] array = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            array[i] = (tamanho - 1) - i;

        }
        return array;
    }

    private static int[] gerarAleatorio(int tamanho) {
        
        int[] array = gerarOrdenado(tamanho);

        Random rng = new Random();
        
     
        for (int i = array.length - 1; i > 0; i--) {
            
            int index = rng.nextInt(i + 1);

            int temp = array[index];

            array[index] = array[i];

            array[i] = temp;

        }
        return array;
    }
}