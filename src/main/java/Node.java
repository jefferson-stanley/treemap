public class Node<K extends Comparable<K>, V> {

    private K key;
    private V value;
    private Color color;
    private Node<K, V> parent;
    private Node<K, V> left;
    private Node<K, V> right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.color = Color.RED;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Node<K, V> getParent() {
        return parent;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

}

enum Color {
    RED,
    BLACK
}
