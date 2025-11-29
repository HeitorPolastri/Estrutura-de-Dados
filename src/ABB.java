class ABB {
    private class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }
    private Node root;

    public void inserir(int val) {
        root = inserirRec(root, val);
    }

    private Node inserirRec(Node n, int val) {
        if (n == null) return new Node(val);
        if (val < n.val) n.left = inserirRec(n.left, val);
        else if (val > n.val) n.right = inserirRec(n.right, val);
        return n;
    }

    public boolean buscar(int val) {
        return buscarRec(root, val);
    }

    private boolean buscarRec(Node n, int val) {
        if (n == null) return false;
        if (n.val == val) return true;
        if (val < n.val) return buscarRec(n.left, val);
        else return buscarRec(n.right, val);
    }
}