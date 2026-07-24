package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();

    public static List<Integer> generateRandomData(int size) {
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

}