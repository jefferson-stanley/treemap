package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final long SEED = 1;

    public static List<Integer> generateRandomData(int size) {
        Random random = new Random(SEED);
        List<Integer> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(Integer.MAX_VALUE));
        }
        return data;
    }

    public static List<Integer> generateSortedData(int size) {
        List<Integer> data = generateRandomData(size);
        Collections.sort(data);
        return data;
    }

    public static List<Integer> generateReverseData(int size) {
        List<Integer> data = generateSortedData(size);
        Collections.reverse(data);
        return data;
    }

    public static List<Integer> generateNearlySortedData(int size) {
        List<Integer> data = generateSortedData(size);
        Random random = new Random(SEED);
        
        int swaps = Math.max(1, (int) (size * 0.10));
        for (int i = 0; i < swaps; i++) {
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            Collections.swap(data, idx1, idx2);
        }
        return data;
    }

    public static List<Integer> generateHighDuplicatesData(int size) {
        Random random = new Random(SEED);
        List<Integer> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(100));
        }
        return data;
    }
}