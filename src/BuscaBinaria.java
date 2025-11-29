

public class BuscaBinaria {

    public static void main(String[] args) {
        
        int[] numeros = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
        
        int alvo = 70; 
        
        int posicao = buscar(numeros, alvo);
        
        if (posicao != -1) {
            System.out.println("Encontrado na posição: " + posicao);
        } else {
            System.out.println("Número não encontrado.");
        }
    }

   
    public static int buscar(int[] vetor, int valorProcurado) {
        int inicio = 0;
        int fim = vetor.length - 1;

        while (inicio <= fim) {
           
            int meio = (inicio + fim) / 2;

            if (vetor[meio] == valorProcurado) {
                return meio; 
            }

            
            if (valorProcurado < vetor[meio]) {
                fim = meio - 1;
            } 
            
            else {
                inicio = meio + 1;
            }
        }
        
        return -1; 
    }
}