package structures;

public class RedBlackTreeAsserts {

    public void testInsertAndBalance() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(10, "10");
        tree.insert(20, "20");
        tree.insert(30, "30");

        assert tree.getSize() == 3;
        assert tree.search(20) != tree.TNULL;
        assert tree.root.getColor() == Color.BLACK;
    }

    public void testDelete() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(50, "50");
        tree.insert(25, "25");
        tree.insert(75, "75");

        tree.delete(25);

        assert tree.getSize() == 2;
        assert tree.search(25) == tree.TNULL;
        assert tree.containsKey(50);
        assert tree.containsKey(75);
    }

    public static void main(String[] args) {
        RedBlackTreeAsserts tests = new RedBlackTreeAsserts();

        tests.testInsertAndBalance();
        tests.testDelete();

        System.out.println("Testes da RedBlackTree executados com sucesso!");
    }
}