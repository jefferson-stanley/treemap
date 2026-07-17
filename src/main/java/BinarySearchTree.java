public abstract class BinarySearchTree<K extends Comparable<K>, V> {

    protected Node<K, V> root;
    protected final Node<K, V> TNULL;
    protected int size;

    public BinarySearchTree() {
        this.TNULL = new Node<K, V>(null, null);
        this.TNULL.setColor(Color.BLACK);

        this.root = this.TNULL;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isLeaf(Node<K, V> node) {
        return node.getLeft() == TNULL && node.getRight() == TNULL;
    }

    public Node<K, V> search(K key) {
        if (this.root.getKey().compareTo(key) == 0)
            return this.root;

        Node<K, V> aux = this.root;

        while (aux != TNULL) {
            if (aux.getKey().compareTo(key) == 0)
                return aux;

            if (aux.getKey().compareTo(key) == -1) {
                aux = aux.getLeft();

            } else {
                aux = aux.getRight();
            }
        }

        return TNULL;
    }

    public abstract void insert(K key, V value);

    public abstract void delete(K key);
}
