class AVL {

    private class Node {

        int val, height;

        Node esquerda, direita;

        Node(int v) { val = v; height = 1; }

    }
    private Node root;

    public void inserir(int val) {
        root = inserirRec(root, val);
    }

    private Node inserirRec(Node n, int val) {
        if (n == null) return new Node(val);
        if (val < n.val) n.esquerda = inserirRec(n.esquerda, val);

        else if (val > n.val) n.direita = inserirRec(n.direita, val);

        else return n;

        n.height = 1 + Math.max(height(n.esquerda), height(n.direita));

        int balance = getBalance(n);


        if (balance > 1 && val < n.esquerda.val) return rotateRight(n);

        if (balance < -1 && val > n.direita.val) return rotateesquerda(n);

        if (balance > 1 && val > n.esquerda.val) {

            n.esquerda = rotateesquerda(n.esquerda);

            return rotateRight(n);
        }
        if (balance < -1 && val < n.direita.val) {
            
            n.direita = rotateRight(n.direita);

            return rotateesquerda(n);
        }
        return n;
    }

    public boolean buscar(int val) {

        return buscarRec(root, val);

    }

    private boolean buscarRec(Node n, int val) {

        if (n == null) return false;

        if (n.val == val) return true;

        if (val < n.val) return buscarRec(n.esquerda, val);

        else return buscarRec(n.direita, val);

    }

    private int height(Node n) {

        return n == null ? 0 : n.height;

    }

    private int getBalance(Node n) {

        return n == null ? 0 : height(n.esquerda) - height(n.direita);

    }

    private Node rotateRight(Node y) {
        Node x = y.esquerda;

        Node T2 = x.direita;

        x.direita = y;

        y.esquerda = T2;

        y.height = Math.max(height(y.esquerda), height(y.direita)) + 1;

        x.height = Math.max(height(x.esquerda), height(x.direita)) + 1;

        return x;
    }

    private Node rotateesquerda(Node x) {
        Node y = x.direita;

        Node T2 = y.esquerda;

        y.esquerda = x;

        x.direita = T2;

        x.height = Math.max(height(x.esquerda), height(x.direita)) + 1;

        y.height = Math.max(height(y.esquerda), height(y.direita)) + 1;
        
        return y;

    }
}