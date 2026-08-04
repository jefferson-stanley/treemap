package map.impl;

import java.util.Collection;
import java.util.Set;

import map.interfaces.Map;

public class TreeMapAsserts {

    public void testPutAndGet() {
        TreeMap<Integer, String> map = new TreeMap<>();

        assert map.isEmpty();
        assert map.size() == 0;
        assert map.get(10) == null;

        map.put(10, "Dez");
        map.put(5, "Cinco");
        map.put(15, "Quinze");

        assert !map.isEmpty();
        assert map.size() == 3;
        assert map.get(10).equals("Dez");
        assert map.get(5).equals("Cinco");
        assert map.get(15).equals("Quinze");
        assert map.get(99) == null;

        map.put(10, "Dez Atualizado");
        assert map.size() == 3;
        assert map.get(10).equals("Dez Atualizado");
    }

    public void testContainsKeyAndValue() {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(100, "Cem");
        map.put(200, "Duzentos");
        map.put(300, "Trezentos");

        assert map.containsKey(100);
        assert map.containsKey(200);
        assert map.containsKey(300);
        assert !map.containsKey(400);

        assert map.containsValue("Cem");
        assert map.containsValue("Duzentos");
        assert !map.containsValue("Quatrocentos");
    }

    public void testRemove() {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(50, "Cinquenta");
        map.put(20, "Vinte");
        map.put(80, "Oitenta");

        assert map.size() == 3;

       
        map.remove(20);
        assert map.size() == 2;
        assert !map.containsKey(20);
        assert map.get(20) == null;

        map.remove(999);
        assert map.size() == 2;

        map.remove(50);
        map.remove(80);

        assert map.isEmpty();
        assert map.size() == 0;
    }

    public void testClear() {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(1, "Um");
        map.put(2, "Dois");
        map.put(3, "Três");

        assert map.size() == 3;

        map.clear();

        assert map.isEmpty();
        assert map.size() == 0;
        assert map.get(1) == null;
    }

    public void testKeySetValuesAndEntrySet() {
        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(3, "Três");
        map.put(1, "Um");
        map.put(2, "Dois");

        Set<Integer> keys = map.keySet();
        assert keys.size() == 3;
        assert keys.contains(1);
        assert keys.contains(2);
        assert keys.contains(3);

        Collection<String> values = map.values();
        assert values.size() == 3;
        assert values.contains("Um");
        assert values.contains("Dois");
        assert values.contains("Três");

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        assert entries.size() == 3;
    }

    public static void main(String[] args) {
        TreeMapAsserts tests = new TreeMapAsserts();

        tests.testPutAndGet();
        tests.testContainsKeyAndValue();
        tests.testRemove();
        tests.testClear();
        tests.testKeySetValuesAndEntrySet();

        System.out.println("Testes do TreeMap executados com sucesso!");
    }
}