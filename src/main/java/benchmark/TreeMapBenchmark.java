package benchmark;

import java.util.List;
import java.util.Random;

import map.impl.TreeMap;
import utils.DataGenerator;

public class TreeMapBenchmark {
    private final Benchmark benchmark = new Benchmark();
    private final int repetitions;
    private final int warmup;

    public TreeMapBenchmark(int repetitions, int warmup) {
        this.repetitions = repetitions;
        this.warmup = warmup;
    }

    public Benchmark run(int[] sizes) {
        String[] datasets = { "random", "sorted", "reverse", "nearly_sorted", "duplicates" };

        for (int size : sizes) {
            for (String datasetType : datasets) {
                List<Integer> data = loadData(datasetType, size);

                benchmark.register("MyTreeMap", "INSERT", datasetType, size, () -> {
                    TreeMap<Integer, Integer> tree = new TreeMap<>();
                    for (int v : data) {
                        tree.put(v, v);
                    }
                }, repetitions, warmup);

                benchmark.register("JavaTreeMap", "INSERT", datasetType, size, () -> {
                    java.util.TreeMap<Integer, Integer> tree = new java.util.TreeMap<>();
                    for (int v : data) {
                        tree.put(v, v);
                    }
                }, repetitions, warmup);

                TreeMap<Integer, Integer> myPrebuilt = buildMyTree(data);
                java.util.TreeMap<Integer, Integer> javaPrebuilt = buildJavaTree(data);

                benchmark.register("MyTreeMap", "SEARCH", datasetType, size, () -> {
                    for (int v : data) {
                        myPrebuilt.get(v);
                    }
                }, repetitions, warmup);

                benchmark.register("JavaTreeMap", "SEARCH", datasetType, size, () -> {
                    for (int v : data) { 
                        javaPrebuilt.get(v);
                    }
                }, repetitions, warmup);

                benchmark.register("MyTreeMap", "DELETE", datasetType, size, () -> {
                    TreeMap<Integer, Integer> tree = buildMyTree(data);
                    for (int v : data) {
                        tree.remove(v);
                    }
                }, repetitions, warmup);

                benchmark.register("JavaTreeMap", "DELETE", datasetType, size, () -> {
                    java.util.TreeMap<Integer, Integer> tree = buildJavaTree(data);
                    for (int v : data) {
                        tree.remove(v);
                    }
                }, repetitions, warmup);

                benchmark.register("MyTreeMap", "MIXED_WORKLOAD", datasetType, size, () -> {
                    TreeMap<Integer, Integer> tree = buildMyTree(data);
                    runMixedMyTree(tree, data);
                }, repetitions, warmup);

                benchmark.register("JavaTreeMap", "MIXED_WORKLOAD", datasetType, size, () -> {
                    java.util.TreeMap<Integer, Integer> tree = buildJavaTree(data);
                    runMixedJavaTree(tree, data);
                }, repetitions, warmup);
            }
        }
        return benchmark;
    }

    private TreeMap<Integer, Integer> buildMyTree(List<Integer> data) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int v : data) {
            tree.put(v, v);
        }
        return tree;
    }

    private java.util.TreeMap<Integer, Integer> buildJavaTree(List<Integer> data) {
        java.util.TreeMap<Integer, Integer> tree = new java.util.TreeMap<>();
        for (int v : data) {
            tree.put(v, v);
        }
        return tree;
    }

    private void runMixedMyTree(TreeMap<Integer, Integer> tree, List<Integer> data) {
        Random rand = new Random(1);
        int n = data.size();
        for (int i = 0; i < n; i++) {
            int op = rand.nextInt(100);
            int val = data.get(rand.nextInt(n));
            if (op < 70) {
                tree.get(val);
            } else if (op < 90) {
                tree.put(val + 1_000_000, val);
            } else {
                tree.remove(val);
            }
        }
    }

    private void runMixedJavaTree(java.util.TreeMap<Integer, Integer> tree, List<Integer> data) {
        Random rand = new Random(1);
        int n = data.size();
        for (int i = 0; i < n; i++) {
            int op = rand.nextInt(100);
            int val = data.get(rand.nextInt(n));
            if (op < 70) {
                tree.get(val);
            } else if (op < 90) {
                tree.put(val + 1_000_000, val);
            } else {
                tree.remove(val);
            }
        }
    }

    private List<Integer> loadData(String type, int size) {
        return switch (type) {
            case "random" -> DataGenerator.generateRandomData(size);
            case "sorted" -> DataGenerator.generateSortedData(size);
            case "reverse" -> DataGenerator.generateReverseData(size);
            case "nearly_sorted" -> DataGenerator.generateNearlySortedData(size);
            case "duplicates" -> DataGenerator.generateHighDuplicatesData(size);
            default -> throw new IllegalArgumentException("Dataset inválido: " + type);
        };
    }
}