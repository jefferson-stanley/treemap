public class RedBlackTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    public RedBlackTree() {
        super();
    }

    @Override
    public void insert(K key, V value) {

        Node<K, V> node = new Node<>(key, value);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        this.size += 1;

        if (isEmpty()) {
            this.root = new Node<>(key, value);
        }

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void delete(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private int height(Node<K, V> node) {

        if (node == TNULL)
            return -1;

        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));

    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> parent = node.getParent();
        Node<K, V> right = node.getRight();

        node.setRight(right.getLeft());

        if (right.getLeft() != TNULL) {
            right.getLeft().setParent(node);
        }

        right.setLeft(node);

        node.setParent(right);

        changeDadsSons(node.getParent(), node, right);

    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> parent = node.getParent();
        Node<K, V> left = node.getLeft();

        node.setLeft(left.getRight());

        if (left.getRight() != TNULL) {
            left.getRight().setParent(node);
        }

        left.setRight(node);

        node.setParent(left);

        changeDadsSons(node.getParent(), node, left);
    }

    private void changeDadsSons(Node<K, V> parent, Node<K, V> oldSon, Node<K, V> newSon) {
        if (parent == TNULL) {
            this.root = newSon;

        } else if (parent.getLeft().getKey().compareTo(oldSon.getKey()) == 0) {
            parent.setLeft(newSon);

        } else if (parent.getRight().getKey().compareTo(oldSon.getKey()) == 0) {
            parent.setRight(newSon);

        } else {
            throw new IllegalStateException();
        }

        if (newSon != TNULL) {
            newSon.setParent(parent);
        }

    }

    private boolean isBalanced() {
        int BF = Math.abs(height(this.root.getLeft()) - height(this.root.getRight()));

        return BF >= 0 && BF <= 1;
    }
}
