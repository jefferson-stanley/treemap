package benchmark;

public class BenchmarkRunner {

    public static void main(String[] args) {
     
        int repetitions = 30; 
        int warmup = 5;
        int[] sizes = {100, 1000, 10000, 100000, 1000000};

        System.out.println("Executando Benchmark de desempenho da nossa TreeMap em comparação à java.util.TreeMap\n");

        TreeMapBenchmark suite = new TreeMapBenchmark(repetitions, warmup);
        Benchmark benchmark = suite.run(sizes);

        try {
            String outputPath = "benchmark_results.csv";
            benchmark.exportarCSV(outputPath);
            System.out.println("\n Benchmark finalizado, resultados salvos em: " + outputPath);
        } catch (Exception e) {
            System.err.println("\n Erro ao exportar CSV: " + e.getMessage());
        }
    }
}