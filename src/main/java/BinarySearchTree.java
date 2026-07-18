import java.util.ArrayDeque;
import java.util.Deque;

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

    public void clear() {
        this.root = TNULL;
    }

    public boolean containsKey(K key) {
        return search(key) != TNULL;
    }

    public boolean containsValue(V value) {
        return searchValue(value) != TNULL;
    }

    public Node<K, V> search(K key) {
        Node<K, V> aux = this.root;

        while (aux != TNULL) {
            int cmp = key.compareTo(aux.getKey());

            if (cmp == 0)
                return aux;

            if (cmp < 0) {
                aux = aux.getLeft();

            } else {
                aux = aux.getRight();
            }
        }

        return TNULL;
    }

    public Node<K, V> searchValue(V value) {
        if (isEmpty())
            return TNULL;

        Deque<Node<K, V>> deque = new ArrayDeque<>();
        Node<K, V> current = this.root;

        while (current != TNULL || !deque.isEmpty()) {
            while (current != TNULL) {
                deque.push(current);
                current = current.getLeft();
            }

            current = deque.pop();

            if (value == null) {
                if (current.getValue() == null)
                    return current;
            } else if (value.equals(current.getValue())) {
                return current;
            }

            current = current.getRight();
        }

        return TNULL;
    }

    public abstract void insert(K key, V value);

    public abstract void delete(K key);
}
