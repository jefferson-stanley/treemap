package structures;


public class BinarySearchTreeAsserts {

    public void testIsEmpty() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        assert bst.isEmpty();
        assert bst.getSize() == 0;
        assert bst.root == bst.TNULL;

        bst.insert(10, "Dez");
        assert !bst.isEmpty();
    }

    public void testGetSize() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        assert bst.getSize() == 0;

        bst.insert(10, "Dez");
        bst.insert(20, "Vinte");
        assert bst.getSize() == 2;
    }

    public void testClear() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        bst.insert(10, "Dez");
        bst.insert(20, "Vinte");
        assert bst.getSize() == 2;

        bst.clear();
        assert bst.root == bst.TNULL;
    }

    public void testContainsKey() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        bst.insert(10, "Dez");

        assert bst.containsKey(10);
        assert !bst.containsKey(999);
    }

    public void testContainsValue() {
        BinarySearchTree<String, Integer> bst = new RedBlackTree<>();

        bst.insert("Java", 1);

        assert bst.containsValue(1);
        assert !bst.containsValue(99);
    }

    public void testSearch() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        bst.insert(10, "Dez");

        assert bst.search(10) != bst.TNULL;
        assert bst.search(10).getValue().equals("Dez");
        assert bst.search(999) == bst.TNULL;
    }

    public void testSearchValue() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        assert bst.searchValue("Dez") == bst.TNULL;

        bst.insert(10, "Dez");

        assert bst.searchValue("Dez") != bst.TNULL;
        assert bst.searchValue("Dez").getKey() == 10;
        assert bst.searchValue("Inexistente") == bst.TNULL;
    }

    public void testCollectKeys() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        bst.insert(20, "Vinte");
        bst.insert(10, "Dez");
        bst.insert(30, "Trinta");

        assert bst.collectKeys().size() == 3;
        assert bst.collectKeys().contains(10);
        assert bst.collectKeys().contains(20);
        assert bst.collectKeys().contains(30);
    }

    public void testCollectValues() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        bst.insert(20, "Vinte");
        bst.insert(10, "Dez");

        assert bst.collectValues().size() == 2;
        assert bst.collectValues().contains("Dez");
        assert bst.collectValues().contains("Vinte");
    }

    public void testCollectEntries() {
        BinarySearchTree<Integer, String> bst = new RedBlackTree<>();

        bst.insert(10, "Dez");

        assert bst.collectEntries().size() == 1;
    }

    public static void main(String[] args) {
        BinarySearchTreeAsserts tests = new BinarySearchTreeAsserts();

        tests.testIsEmpty();
        tests.testGetSize();
        tests.testClear();
        tests.testContainsKey();
        tests.testContainsValue();
        tests.testSearch();
        tests.testSearchValue();
        tests.testCollectKeys();
        tests.testCollectValues();
        tests.testCollectEntries();

        System.out.println("Testes da BinarySearchTree executados com sucesso!");
    }
}