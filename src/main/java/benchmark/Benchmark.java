package benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    private final List<BenchmarkResult> results = new ArrayList<>();

    public void register(String structure, String operation, String dataset, int inputsize,
                         Runnable action, int rep, int warmup) {
        
        for (int i = 0; i < warmup; i++) {
            action.run();
        }

        double[] samples = new double[rep];
        TimerBench timer = new TimerBench();

        for (int i = 0; i < rep; i++) {
            timer.reset();
            timer.start();
            action.run();
            timer.stop();
            samples[i] = timer.getTotal(); 
        }

        double sum = 0.0;
        for (double s : samples) {
            sum += s;
        }
        double mean = sum / rep;

        double varianceSum = 0.0;
        for (double s : samples) {
            varianceSum += Math.pow(s - mean, 2);
        }

        double stdDevMs;
        if (rep > 1) {
            stdDevMs = Math.sqrt(varianceSum / (rep - 1));
        } else {
            stdDevMs = 0.0;
        }

        results.add(new BenchmarkResult(structure, operation, dataset, inputsize, mean, stdDevMs));
        
        System.out.println(structure + " [" + operation + " | " + dataset + " | n=" + inputsize + 
                            "]: Média = " + mean + " ms | Desvio = " + stdDevMs + " ms");
    }

    public void exportarCSV(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("structure,operation,dataset,len,averageTime,stdDevMs\n");
            for (BenchmarkResult result : results) {
                writer.write(result.toCsvLine());
                writer.newLine();
            }
            writer.flush();
        }
    }
}