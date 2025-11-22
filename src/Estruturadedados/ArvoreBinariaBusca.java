package Estruturadedados;

public class ArvoreBinariaBusca {
    
    private class No {
        int valor;

        No esquerda, direita;
        
        
        public No(int valor) {
            this.valor = valor;
        }
    }

    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No no, int valor) {
        if (no == null) {
            return new No(valor);
        }
        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        }
        return no;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No no, int valor) {
        if (no == null) return false;
        if (valor == no.valor) return true;
        
        if (valor < no.valor) {
            return buscarRecursivo(no.esquerda, valor);
        } else {
            return buscarRecursivo(no.direita, valor);
        }
    }
}