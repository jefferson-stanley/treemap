package benchmark;

import map.TreeMap;
import utils.DataGenerator;

import java.util.List;

public class TreeMapBenchmark {
    private final Benchmark benchmark = new Benchmark();
    private final int repetitions;
    private final int warmup;

    public TreeMapBenchmark(int repetitions, int warmup) {
        this.repetitions = repetitions;
        this.warmup = warmup;
    }

    public Benchmark run(int[] sizes) {
        String[] datasets = {"random", "sorted", "reverse"};

        for (int size : sizes) {
            for (String datasetType : datasets) {
                List<Integer> data = loadData(datasetType, size);

                benchmark.register("MyTreeMap", "INSERT", datasetType, size, () -> {
                    TreeMap<Integer, Integer> tree = new TreeMap<>();
                    for (int v : data) tree.put(v, v);
                }, repetitions, warmup);

                benchmark.register("JavaTreeMap", "INSERT", datasetType, size, () -> {
                    java.util.TreeMap<Integer, Integer> tree = new java.util.TreeMap<>();
                    for (int v : data) tree.put(v, v);
                }, repetitions, warmup);

                TreeMap<Integer, Integer> myPrebuilt = new TreeMap<>();
                java.util.TreeMap<Integer, Integer> javaPrebuilt = new java.util.TreeMap<>();
                for (int v : data) {
                    myPrebuilt.put(v, v);
                    javaPrebuilt.put(v, v);
                }

                benchmark.register("MyTreeMap", "SEARCH", datasetType, size, () -> {
                    for (int v : data) myPrebuilt.get(v);
                }, repetitions, warmup);

                benchmark.register("JavaTreeMap", "SEARCH", datasetType, size, () -> {
                    for (int v : data) javaPrebuilt.get(v);
                }, repetitions, warmup);
            }
        }
        return benchmark;
    }

    private List<Integer> loadData(String type, int size) {
        return switch (type) {
            case "random"  -> DataGenerator.generateRandomData(size);
            case "sorted"  -> DataGenerator.generateSortedData(size);
            case "reverse" -> DataGenerator.generateReverseData(size);
            default -> throw new IllegalArgumentException("Dataset inválido: " + type);
        };
    }
}