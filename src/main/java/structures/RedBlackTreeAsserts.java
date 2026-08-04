package structures;

public class RedBlackTreeAsserts {

    public void testInsertAndBasicBalance() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(10, "10");
        tree.insert(20, "20");
        tree.insert(30, "30"); 

        assert tree.getSize() == 3;
        assert !tree.isEmpty();
        assert tree.root.getColor() == Color.BLACK;
    }

    public void testComplexInsertAndRotations() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        int[] values = {10, 20, 30, 15, 25, 5, 1};
        for (int val : values) {
            tree.insert(val, "Val-" + val);
        }

        assert tree.getSize() == 7;
        assert tree.root.getColor() == Color.BLACK;
    }

    public void testUpdateExistingKey() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(15, "15");
        assert tree.search(15).getValue().equals("15");
        assert tree.getSize() == 1;

        tree.insert(15, "15 Atualizado");
        assert tree.getSize() == 1;
        assert tree.search(15).getValue().equals("15 Atualizado");
    }

    public void testSearch() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(15, "15");

        assert tree.search(15) != tree.TNULL;
        assert tree.search(15).getValue().equals("15");
        assert tree.search(999) == tree.TNULL;
    }

    public void testContainsKey() {
        RedBlackTree<String, Integer> tree = new RedBlackTree<>();

        tree.insert("A", 1);
        tree.insert("B", 2);

        assert tree.containsKey("A");
        assert tree.containsKey("B");
        assert !tree.containsKey("c");
    }

    public void testContainsValue() {
        RedBlackTree<String, Integer> tree = new RedBlackTree<>();

        tree.insert("A", 1);
        tree.insert("B", 2);

        assert tree.containsValue(1);
        assert tree.containsValue(2);
        assert !tree.containsValue(99);
    }

    public void testClear() {
        RedBlackTree<String, Integer> tree = new RedBlackTree<>();

        tree.insert("A", 1);
        tree.insert("B", 2);
        assert tree.getSize() == 2;

        tree.clear();
        assert tree.isEmpty();
        assert tree.getSize() == 0;
        assert !tree.containsKey("A");
    }

    public void testDeleteLeaf() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(50, "Cinquenta");
        tree.insert(30, "Trinta");
        tree.insert(70, "Setenta");

        tree.delete(30); 

        assert tree.getSize() == 2;
        assert tree.search(30) == tree.TNULL;
        assert tree.containsKey(50);
        assert tree.containsKey(70);
    }

    public void testDeleteNodeWithTwoChildren() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int k : keys) {
            tree.insert(k, "Val-" + k);
        }

        tree.delete(30); 

        assert tree.getSize() == keys.length - 1;
        assert tree.search(30) == tree.TNULL;
        assert tree.containsKey(40);
    }

    public void testDeleteRoot() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        tree.insert(50, "Cinquenta");
        tree.insert(30, "Trinta");
        tree.insert(70, "Setenta");

        tree.delete(50); 

        assert tree.search(50) == tree.TNULL;
        assert tree.root.getColor() == Color.BLACK;
        assert tree.getSize() == 2;
    }

    public void testEmptyTreeCompletely() {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();

        int[] keys = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int k : keys) {
            tree.insert(k, "Val-" + k);
        }

        for (int k : keys) {
            tree.delete(k);
        }

        assert tree.isEmpty();
        assert tree.getSize() == 0;
        assert tree.root == tree.TNULL;
    }

    public static void main(String[] args) {
        RedBlackTreeAsserts tests = new RedBlackTreeAsserts();

        tests.testInsertAndBasicBalance();
        tests.testComplexInsertAndRotations();
        tests.testUpdateExistingKey();
        tests.testSearch();
        tests.testContainsKey();
        tests.testContainsValue();
        tests.testClear();
        tests.testDeleteLeaf();
        tests.testDeleteNodeWithTwoChildren();
        tests.testDeleteRoot();
        tests.testEmptyTreeCompletely();

        System.out.println("Testes da RedBlackTree executados com sucesso!");
    }
}