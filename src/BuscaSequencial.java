

public class BuscaSequencial {

    public static void main(String[] args) {
        
        int[] numeros = { 45, 10, 7, 90, 12, 55 };
        
        int alvo = 90; 
        
        int posicao = buscar(numeros, alvo);
        
        if (posicao != -1) {

            System.out.println("Encontrado na posição: " + posicao);

        } else {

            System.out.println("Número não encontrado.");

        }
    }

    
    public static int buscar(int[] vetor, int valorProcurado) {
        
        for (int i = 0; i < vetor.length; i++) {

            if (vetor[i] == valorProcurado) 
                {

                return i; 

            }
            
        }

        return -1; 
    }
}