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

    /**
     * Verifica se a árvore está vazia.
     * @return true caso não tenho valores na árvore ou false se houve pelo menos um.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Remove o endereçamento dos valores da árvore.
     */
    public void clear() {
        this.root = TNULL;
    }

    /**
     * Verifica a existência de uma chave na árvore.
     * @param key Chave que será verificada.
     * @return true caso a chave esteja mapeada ou false se não estiver.
     */
    public boolean containsKey(K key) {
        return search(key) != TNULL;
    }

    /**
     * Verifica se um valor esta mapeado na árvore.
     * @param value Valor que será verificado.
     * @return true caso valor esteja na árvore ou false se não estiver.
     */
    public boolean containsValue(V value) {
        return searchValue(value) != TNULL;
    }

    /**
     * Implementação iterativa que busca o nó cujo sua chave seja igual á passada como parâmetro. 
     * @param key Chave a ser procurada.
     * @return Nó contendo a chave equivalente. Retorna nulo caso a chave não esteja mapeada.
     */
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

    /**
     * Implementação iterativa que busca o nó cujo valor seja igual passado como parâmetro.
     * @param value Valor que será procurado.
     * @return Nó contendo o valor procurado. Retorna nulo caso o valor não esteja presente na árvore.
     */
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

    /**
     * @return Set contendo as chaves presentes na árvore.
     */
    public Set<K> collectKeys() {
        Set<K> keys = new TreeSet<>();

        collectKeysAux(this.root, keys);

        return keys;
    }

    /**
     * Método auxiliar que coleta as chaves presentes na árvore de forma recursiva.
     * @param node Nó atual da árvore.
     * @param set Conjunto onde as chaves serão adicionadas
     */
    public void collectKeysAux(Node<K, V> node, Set<K> set) {
        if (node == TNULL)
            return;

        collectKeysAux(node.getLeft(), set);

        set.add(node.getKey());

        collectKeysAux(node.getRight(), set);
    }

    /**
     * @return Coleção contendo os valores presentes na árvore.
     */
    public Collection<V> collectValues() {
        Collection<V> values = new ArrayList<>();
        collectValuesAux(this.root, values);

        return values;
    }

    /**
     * Método auxiliar que coleta os valores presentes na árvore de forma recursiva.
     * @param node Nó atual da árvore.
     * @param collection Coleção onde os valores serão adicionados.
     */
    public void collectValuesAux(Node<K, V> node, Collection<V> collection) {
        if (node == TNULL)
            return;

        collectValuesAux(node.getLeft(), collection);

        collection.add(node.getValue());

        collectValuesAux(node.getRight(), collection);
    }

    /**
     * @return Set contendo os pares chave-valor presentes na árvore.
     */
    public Set<Entry<K, V>> collectEntries() {
        Set<Entry<K, V>> entries = new HashSet<>();

        collectEntriesAux(this.root, entries);

        return entries;
    }

    /**
     * Método auxiliar que coleta os pares de chave-valor presentes na árvore de forma recursiva.
     * @param node Nó atual da árvore.
     * @param set Conjunto onde os pares serão adicionados.
     */
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

    /**
     * Método para realizar o percurso em-ordem na árvore.
     * @param node Nó atual do percurso.
     * @param action Ação à ser realizada.
     */
    private void inOrder(Node<K, V> node, Consumer<Node<K, V>> action) {
        if (node == TNULL)
            return;
        inOrder(node.getLeft(), action);
        action.accept(node);
        inOrder(node.getRight(), action);
    }

    /**
     * Insere um novo nó na árvore com a chave e o valor informado.
     * @param key Chave que será inserida.
     * @param value Valor relacionado à chave.
     */
    public abstract void insert(K key, V value);

    /**
     * Remove o nó correspondente à chave informada da ávore.
     * @param key Chave do nó que será deletado.
     */
    public abstract void delete(K key);
}
