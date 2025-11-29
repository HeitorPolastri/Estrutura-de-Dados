import java.util.Random;

public class TesteCompleto {

    // Configurações globais do teste
    private static final int[] TAMANHOS = { 100, 1_000, 10_000 };
    private static final int REPETICOES = 5; //num de repetiçoes q deseja 

    public static void main(String[] args) {
        
        System.out.println("Vao ser feitos:" + REPETICOES + " execuções)");
        System.out.println("--------------------------------------");

        for (int tamanho : TAMANHOS) {
            
            System.out.println(" Numero de elementos: " + tamanho);
            System.out.println("--------------------------------------");

            
            int[] dadosOrdenados = gerarOrdenado(tamanho);

            int[] dadosAleatorios = gerarAleatorio(tamanho);

            int[] dadosInversos = gerarInversamenteOrdenado(tamanho);

            

           
            rodarCenario("INVERSAMENTE ORDENADA", tamanho, dadosInversos);

            rodarCenario("ORDENADA", tamanho, dadosOrdenados);

            rodarCenario("ALEATÓRIA", tamanho, dadosAleatorios);
        }
    }

    private static void rodarCenario(String nomeCenario, int tamanho, int[] dadosOriginais) {
        System.out.println("\n>>> CENÁRIO: " + nomeCenario);
        System.out.printf("%-10s | %-15s | %-15s | %-20s%n", "Estrutura", "Inserção (ms)", "Busca (ms)", "Ordenação (ms)");
        System.out.println("-----------------------------------------------------------------------");

        
        double tempoInsVetor = 0, tempoBuscaVetor = 0;
        double tempoBubble = 0, tempoQuick = 0;
        double tempoInsABB = 0, tempoBuscaABB = 0;
        double tempoInsAVL = 0, tempoBuscaAVL = 0;
        
        boolean abbFalhou = false;

        
        for (int i = 0; i < REPETICOES; i++) {
            
         
            Vetor vetor = new Vetor(tamanho);
          
            long inicio = System.nanoTime();
            for (int val : dadosOriginais) vetor.inserir(val);
            tempoInsVetor += (System.nanoTime() - inicio);

           
            tempoBuscaVetor += medirBuscaVetor(vetor, dadosOriginais);

         
            tempoBubble += medirBubbleSort(dadosOriginais);
            tempoQuick += medirQuickSort(dadosOriginais);

          
            if (!abbFalhou) {
                try {
                    ABB abb = new ABB();
                    inicio = System.nanoTime();
                    for (int val : dadosOriginais) abb.inserir(val);
                    tempoInsABB += (System.nanoTime() - inicio);
                    
                    tempoBuscaABB += medirBuscaABB(abb, dadosOriginais);
                } catch (StackOverflowError e) {
                    abbFalhou = true; 
                }
            }

            
            AVL avl = new AVL();
            inicio = System.nanoTime();
            for (int val : dadosOriginais) avl.inserir(val);
            tempoInsAVL += (System.nanoTime() - inicio);
            
            tempoBuscaAVL += medirBuscaAVL(avl, dadosOriginais);
        }

      
        
        
        imprimirLinha("Vetor", tempoInsVetor, tempoBuscaVetor, "N/A (Ver abaixo)");
        
    
        if (abbFalhou) {
            System.out.printf("%-10s | %-15s | %-15s | %-20s%n", "ABB", "FALHA (Stack)", "-", "-");
        } else {
            imprimirLinha("ABB", tempoInsABB, tempoBuscaABB, "-");
        }

   
        imprimirLinha("AVL", tempoInsAVL, tempoBuscaAVL, "-");

        
        System.out.println("  - = Média Quick Sort:  " + formatarTempo(tempoQuick));
    
        
        System.out.println("  - =Média Bubble Sort: " + formatarTempo(tempoBubble));

    }

    
    
    private static int[] definirAlvosDeBusca(int[] dados) {
        Random r = new Random();
        int[] alvos = new int[7];
        alvos[0] = dados[0]; 
        alvos[1] = dados[dados.length - 1]; 
        alvos[2] = dados[dados.length / 2]; 
        alvos[3] = dados[r.nextInt(dados.length)]; 
        alvos[4] = dados[r.nextInt(dados.length)]; 
        alvos[5] = dados[r.nextInt(dados.length)];
        alvos[6] = -999; 
        return alvos;
    }

    private static long medirBuscaVetor(Vetor v, int[] dados) {

        int[] alvos = definirAlvosDeBusca(dados);

        long inicio = System.nanoTime();

        for (int alvo : alvos) {

            v.buscaSequencial(alvo);

        }

        return System.nanoTime() - inicio;

    }
    

    private static long medirBuscaABB(ABB abb, int[] dados) {

        int[] alvos = definirAlvosDeBusca(dados);

        long inicio = System.nanoTime();

        for (int alvo : alvos) {

            abb.buscar(alvo);

        }
        return System.nanoTime() - inicio;
    }

    private static long medirBuscaAVL(AVL avl, int[] dados) {

        int[] alvos = definirAlvosDeBusca(dados);

        long inicio = System.nanoTime();

        for (int alvo : alvos) {

            avl.buscar(alvo);
            
        }
        return System.nanoTime() - inicio;
    }



    private static long medirBubbleSort(int[] dadosOriginais) {
       
        Vetor v = new Vetor(dadosOriginais.length);
        for(int i : dadosOriginais) v.inserir(i);
        
        long inicio = System.nanoTime();
        v.bubbleSort();
        return System.nanoTime() - inicio;
    }

    private static long medirQuickSort(int[] dadosOriginais) {
        Vetor v = new Vetor(dadosOriginais.length);
        for(int i : dadosOriginais) v.inserir(i);
        
        long inicio = System.nanoTime();
        v.quickSort();
        return System.nanoTime() - inicio;
    }

  

    private static void imprimirLinha(String nome, double totalNanoIns, double totalNanoBusca, String extra) {

        double mediaMsIns = (totalNanoIns / REPETICOES) / 1_000_000.0;

        double mediaMsBusca = (totalNanoBusca / REPETICOES) / 1_000_000.0;

        System.out.printf("%-10s | %-15.4f | %-15.4f | %-20s%n", nome, mediaMsIns, mediaMsBusca, extra);

    }

    private static String formatarTempo(double totalNano) {

        return String.format("%.4f ms", (totalNano / REPETICOES) / 1_000_000.0);

    }

    private static int[] gerarOrdenado(int n) {

        int[] v = new int[n];

        for (int i = 0; i < n; i++) v[i] = i;

        return v;

    }

    private static int[] gerarInversamenteOrdenado(int n) {

        int[] v = new int[n];

        for (int i = 0; i < n; i++) v[i] = (n - 1) - i;

        return v;

    }

    private static int[] gerarAleatorio(int n) {
        int[] v = gerarOrdenado(n);

        Random r = new Random();

        for (int i = n - 1; i > 0; i--) {

            int j = r.nextInt(i + 1);

            int temp = v[i];

            v[i] = v[j];

            v[j] = temp;


        }


        return v;
    }
}