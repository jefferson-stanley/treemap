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

}
