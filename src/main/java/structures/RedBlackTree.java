package structures;

public class RedBlackTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    public RedBlackTree() {
        super();
    }

    @Override
    public void insert(K key, V value) {

        Node<K, V> aux = this.root;
        Node<K, V> parent = TNULL;

        while (aux != TNULL) {
            parent = aux;

            if (key.compareTo(aux.getKey()) < 0) {
                aux = aux.getLeft();

            } else if (key.compareTo(aux.getKey()) > 0) {
                aux = aux.getRight();

            } else {
                aux.setValue(value);
                return;
            }
        }

        Node<K, V> newNode = new Node<K, V>(key, value);

        newNode.setLeft(TNULL);
        newNode.setRight(TNULL);
        newNode.setParent(parent);

        if (parent == TNULL) {
            root = newNode;
            newNode.setColor(Color.BLACK);
        } else if (key.compareTo(parent.getKey()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        size++;

        propriertiesAfterInsert(newNode);
    }

    @Override
    public void delete(K key) {
        Node<K, V> node = search(key);

        if (node == TNULL)
            return;

        Node<K, V> upNode;
        Color deletedNodeColor;

        if (node.getLeft() == TNULL || node.getRight() == TNULL) {
            deletedNodeColor = node.getColor();
            upNode = deleteNodeMaximumOneSon(node);

        } else {
            Node<K, V> successor = min(node.getRight());

            node.setKey(successor.getKey());
            node.setValue(successor.getValue());

            deletedNodeColor = successor.getColor();
            upNode = deleteNodeMaximumOneSon(successor);
        }

        if (deletedNodeColor == Color.BLACK) {
            propriertiesAfterDelete(upNode);

        }

        this.size--;
    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> right = node.getRight();
        Node<K, V> oldParent = node.getParent();

        node.setRight(right.getLeft());

        if (right.getLeft() != TNULL) {
            right.getLeft().setParent(node);
        }

        right.setParent(oldParent);

        if (oldParent == TNULL) {
            root = right;

        } else if (oldParent.getLeft() == node) {
            oldParent.setLeft(right);

        } else {
            oldParent.setRight(right);
        }

        right.setLeft(node);

        node.setParent(right);

    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> left = node.getLeft();
        Node<K, V> oldParent = node.getParent();

        node.setLeft(left.getRight());

        if (left.getRight() != TNULL) {
            left.getRight().setParent(node);
        }

        left.setParent(oldParent);

        if (oldParent == TNULL) {
            root = left;

        } else if (oldParent.getRight() == node) {
            oldParent.setRight(left);

        } else {
            oldParent.setLeft(left);
        }

        left.setRight(node);

        node.setParent(left);
    }

    private void changeDadsSons(Node<K, V> parent, Node<K, V> oldSon, Node<K, V> newSon) {
        if (parent == TNULL) {
            this.root = newSon;

        } else if (parent.getLeft() == oldSon) {
            parent.setLeft(newSon);

        } else if (parent.getRight() == oldSon) {
            parent.setRight(newSon);

        } else {
            throw new IllegalStateException();
        }

        if (newSon != TNULL) {
            newSon.setParent(parent);
        }

    }

    public void propriertiesAfterInsert(Node<K, V> node) {
        Node<K, V> parent = node.getParent();

        if (parent == TNULL) {
            node.setColor(Color.BLACK);
            return;

        }

        if (isBlack(parent)) {
            return;
        }

        Node<K, V> grandParent = parent.getParent();

        if (grandParent == TNULL) {
            parent.setColor(Color.BLACK);
            return;
        }

        Node<K, V> uncle = getUncle(node);

        if (uncle != TNULL && uncle.getColor() == Color.RED) {
            parent.setColor(Color.BLACK);
            grandParent.setColor(Color.RED);
            uncle.setColor(Color.BLACK);

            propriertiesAfterInsert(grandParent);

        } else if (parent == grandParent.getLeft()) {

            if (node == parent.getRight()) {
                rotateLeft(parent);

                parent = node;
            }

            rotateRight(grandParent);

            parent.setColor(Color.BLACK);
            grandParent.setColor(Color.RED);

        } else {
            if (node == parent.getLeft()) {
                rotateRight(parent);

                parent = node;
            }

            rotateLeft(grandParent);

            parent.setColor(Color.BLACK);
            grandParent.setColor(Color.RED);

        }

        root.setColor(Color.BLACK);
    }

    private void propriertiesAfterDelete(Node<K, V> node) {

        if (node == this.root)
            return;

        Node<K, V> brother = getBrother(node);

        if (!isBlack(brother)) {
            handleRedBrother(node, brother);
            brother = getBrother(node);
        }

        if (isBlack(brother.getLeft()) && isBlack(brother.getRight())) {
            brother.setColor(Color.RED);

            if (!isBlack(node.getParent())) {
                node.getParent().setColor(Color.BLACK);

            } else {
                propriertiesAfterDelete(node.getParent());
            }

        } else {
            handleBlackBrotherLeastOneRedSon(node, brother);
        }
    }

    private Node<K, V> getUncle(Node<K, V> node) {
        Node<K, V> parent = node.getParent();
        Node<K, V> grandparent = parent.getParent();

        if (grandparent == TNULL)
            return TNULL;

        if (grandparent.getLeft() == parent)
            return grandparent.getRight();

        if (grandparent.getRight() == parent)
            return grandparent.getLeft();

        throw new IllegalStateException();
    }

    private Node<K, V> getBrother(Node<K, V> node) {
        Node<K, V> parent = node.getParent();

        if (parent == TNULL) {
            return TNULL;
        }

        if (node == parent.getLeft())
            return parent.getRight();
        if (node == parent.getRight())
            return parent.getLeft();

        throw new IllegalStateException();

    }

    private Node<K, V> deleteNodeMaximumOneSon(Node<K, V> node) {
        if (node.getLeft() != TNULL) {
            changeDadsSons(node.getParent(), node, node.getLeft());

            return node.getLeft();

        } else if (node.getRight() != TNULL) {
            changeDadsSons(node.getParent(), node, node.getRight());

            return node.getRight();

        } else {
            changeDadsSons(node.getParent(), node, TNULL);

            return TNULL;

        }

    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.getLeft() != TNULL) {
            node = node.getLeft();
        }
        return node;
    }

    private boolean isBlack(Node<K, V> node) {
        return node == TNULL || node.getColor() == Color.BLACK;
    }

    private void handleRedBrother(Node<K, V> node, Node<K, V> brother) {
        brother.setColor(Color.BLACK);
        node.getParent().setColor(Color.RED);

        if (node == node.getParent().getLeft()) {
            rotateLeft(node.getParent());
        } else {
            rotateRight(node.getParent());
        }
    }

    private void handleBlackBrotherLeastOneRedSon(Node<K, V> node, Node<K, V> brother) {

        boolean nodeIsLeftChild = node == node.getParent().getLeft();

        if (nodeIsLeftChild && isBlack(brother.getRight())) {
            brother.getLeft().setColor(Color.BLACK);
            brother.setColor(Color.RED);
            rotateRight(brother);
            brother = node.getParent().getRight();
        } else if (!nodeIsLeftChild && isBlack(brother.getLeft())) {
            brother.getRight().setColor(Color.BLACK);
            brother.setColor(Color.RED);
            rotateLeft(brother);
            brother = node.getParent().getLeft();
        }

        brother.setColor(node.getParent().getColor());
        node.getParent().setColor(Color.BLACK);
        if (nodeIsLeftChild) {
            brother.getRight().setColor(Color.BLACK);
            rotateLeft(node.getParent());
        } else {
            brother.getLeft().setColor(Color.BLACK);
            rotateRight(node.getParent());
        }

    }
}
