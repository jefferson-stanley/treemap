package benchmark;

public record BenchmarkResult(
        String structure,
        String operation,
        String dataset,
        int inputsize,
        double averageTime,
        double stdDev
) {
    public String toCsvLine() {
        return structure + "," + operation + "," + dataset + "," + 
               inputsize + "," + averageTime + "," + stdDev;
    }
}