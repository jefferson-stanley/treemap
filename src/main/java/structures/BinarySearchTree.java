package structures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import map.interfaces.Map.Entry;

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

    public Set<K> collectKeys() {
        Set<K> keys = new TreeSet<>();

        collectKeysAux(this.root, keys);

        return keys;
    }

    public void collectKeysAux(Node<K, V> node, Set<K> set) {
        if (node == TNULL)
            return;

        collectKeysAux(node.getLeft(), set);

        set.add(node.getKey());

        collectKeysAux(node.getRight(), set);
    }

    public Collection<V> collectValues() {
        Collection<V> values = new ArrayList<>();

        return values;
    }

    public void collectValuesAux(Node<K, V> node, Collection<V> collection) {
        if (node == TNULL)
            return;

        collectValuesAux(node.getLeft(), collection);

        collection.add(node.getValue());

        collectValuesAux(node.getRight(), collection);
    }

    public Set<Entry<K, V>> collectEntries() {
        Set<Entry<K, V>> entries = new HashSet<>();

        collectEntriesAux(this.root, entries);

        return entries;
    }

    public void collectEntriesAux(Node<K, V> node, Set<Entry<K, V>> set) {
        if (node == TNULL)
            return;

        collectEntriesAux(node.getLeft(), set);

        set.add(new Entry<K, V>() {
            @Override
            public K getKey() {
                return node.getKey();
            }

            @Override
            public V getValue() {
                return node.getValue();
            }
        });

        collectEntriesAux(node.getRight(), set);
    }

    public void forEachEntry(Consumer<Node<K, V>> action) {
        inOrder(this.root, action);
    }

    private void inOrder(Node<K, V> node, Consumer<Node<K, V>> action) {
        if (node == TNULL)
            return;
        inOrder(node.getLeft(), action);
        action.accept(node);
        inOrder(node.getRight(), action);
    }

    public abstract void insert(K key, V value);

    public abstract void delete(K key);
}
