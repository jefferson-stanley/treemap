public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    RedBlackTree<K, V> tree;

    public TreeMap() {
        tree = new RedBlackTree<>();
    }

    @Override
    public void put(K key, V value) {
        tree.insert(key, value);
    }

    @Override
    public V get(K key) {

        Node<K, V> node = tree.search(key);

        if (node == null || node.getKey() == null) {
            return null;
        }

        return node.getValue();
    }

    @Override
    public void remove(K key) {

        tree.delete(key);
    }

    @Override
    public boolean containsKey(K key) {
        return tree.containsKey(key);
    }

    @Override
    public int size() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public boolean containsValue(V value) {
        return tree.containsValue(value);
    }

    @Override
    public void clear() {
        tree.clear();
    }

}
