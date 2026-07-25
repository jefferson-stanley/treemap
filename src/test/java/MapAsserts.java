package map.interfaces;

import map.impl.TreeMap;
import java.util.Collection;
import java.util.Set;

public class MapAsserts {

    public void testPutAndGet() {
        Map<String, Integer> map = new TreeMap<>();

        assert map.isEmpty();
        assert map.size() == 0;
        assert map.get("ChaveInexistente") == null;

        map.put("A", 100);
        map.put("B", 200);

        assert !map.isEmpty();
        assert map.size() == 2;
        assert map.get("A") == 100;
        assert map.get("B") == 200;

        map.put("A", 999);
        assert map.size() == 2;
        assert map.get("A") == 999;
    }

    public void testContains() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("Java", 1);
        map.put("Python", 2);

        assert map.containsKey("Java");
        assert map.containsKey("Python");
        assert !map.containsKey("C++");

        assert map.containsValue(1);
        assert map.containsValue(2);
        assert !map.containsValue(99);
    }

    public void testRemove() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("X", 10);
        map.put("Y", 20);

        assert map.size() == 2;

        map.remove("X");

        assert map.size() == 1;
        assert !map.containsKey("X");
        assert map.get("X") == null;
        assert map.containsKey("Y");

        map.remove("Z");
        assert map.size() == 1;
    }

    public void testClear() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        assert map.size() == 3;

        map.clear();

        assert map.isEmpty();
        assert map.size() == 0;
        assert !map.containsKey("A");
        assert map.get("A") == null;
    }

    public void testViews() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("Um", 1);
        map.put("Dois", 2);
        map.put("Tres", 3);

        Set<String> keys = map.keySet();
        assert keys.size() == 3;
        assert keys.contains("Um");
        assert keys.contains("Dois");
        assert keys.contains("Tres");

        Collection<Integer> values = map.values();
        assert values.size() == 3;
        assert values.contains(1);
        assert values.contains(2);
        assert values.contains(3);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        assert entries.size() == 3;

        for (Map.Entry<String, Integer> entry : entries) {
            assert entry.getKey() != null;
            assert entry.getValue() != null;
        }
    }

    public static void main(String[] args) {
        MapAsserts tests = new MapAsserts();

        tests.testPutAndGet();
        tests.testContains();
        tests.testRemove();
        tests.testClear();
        tests.testViews();

        System.out.println("Testes de Map executados com sucesso!");
    }
}