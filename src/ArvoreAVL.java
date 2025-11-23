

public class ArvoreAVL {

    private class No {
        int valor, altura;
        No esquerda, direita;

        public No(int valor) {
            this.valor = valor;
            this.altura = 1;
        }
    }

    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No no, int valor) {
        if (no == null) return new No(valor);

        if (valor < no.valor) no.esquerda = inserirRecursivo(no.esquerda, valor);
        else if (valor > no.valor) no.direita = inserirRecursivo(no.direita, valor);
        else return no; 

        no.altura = 1 + maior(altura(no.esquerda), altura(no.direita));

        int balanceamento = getBalanceamento(no);

        if (balanceamento > 1 && valor < no.esquerda.valor) return rodarDireita(no);
        if (balanceamento < -1 && valor > no.direita.valor) return rodarEsquerda(no);
        if (balanceamento > 1 && valor > no.esquerda.valor) {
            no.esquerda = rodarEsquerda(no.esquerda);
            return rodarDireita(no);
        }
        if (balanceamento < -1 && valor < no.direita.valor) {
            no.direita = rodarDireita(no.direita);
            return rodarEsquerda(no);
        }

        return no;
    }

    public boolean buscar(int valor) {
        No atual = raiz;
        while (atual != null) {
            if (valor == atual.valor) return true;
            if (valor < atual.valor) atual = atual.esquerda;
            else atual = atual.direita;
        }
        return false;
    }


    private int altura(No n) {
        return (n == null) ? 0 : n.altura;
    }

    private int maior(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getBalanceamento(No n) {
        return (n == null) ? 0 : altura(n.esquerda) - altura(n.direita);
    }

    private No rodarDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = 1 + maior(altura(y.esquerda), altura(y.direita));
        x.altura = 1 + maior(altura(x.esquerda), altura(x.direita));
        return x;
    }

    private No rodarEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = 1 + maior(altura(x.esquerda), altura(x.direita));
        y.altura = 1 + maior(altura(y.esquerda), altura(y.direita));
        return y;
    }
}