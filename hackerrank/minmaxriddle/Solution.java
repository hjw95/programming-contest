package hackerrank.minmaxriddle;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void insertMinMap(HashMap<Long, Integer> minMap, int size, long value) {
        if (minMap.containsKey(value) && minMap.get(value) > size) {
            return;
        }
        minMap.put(value, size);
    }

    static void insertMaxMap(HashMap<Integer, Long> maxMap, int size, long value) {
        if (maxMap.containsKey(size) && maxMap.get(size) > value) {
            return;
        }
        maxMap.put(size, value);
    }

    // Complete the riddle function below.
    static long[] riddle(long[] arr) {
        HashMap<Long, Integer> minMap = new HashMap<Long, Integer>();
        
        Deque<Long> increasingValue = new ArrayDeque<Long>();
        Deque<Integer> increasingIndex = new ArrayDeque<Integer>();
        for (int i = 0, l = arr.length; i < l; i++) {
            long val = arr[i];
            int stackIndex = i;
            
            while (increasingValue.peekFirst() != null && increasingValue.peekFirst() >= val) {
                long stackValue = increasingValue.pollFirst();
                stackIndex = increasingIndex.pollFirst();

                insertMinMap(minMap, i - stackIndex, stackValue);
                insertMinMap(minMap, i - stackIndex + 1, val);
            }

            increasingValue.offerFirst(val);
            increasingIndex.offerFirst(stackIndex);
        }

        while (increasingValue.peekFirst() != null) {
            long stackValue = increasingValue.pollFirst();
            int stackIndex = increasingIndex.pollFirst();

            insertMinMap(minMap, arr.length - stackIndex, stackValue);
        }

        HashMap<Integer, Long> maxMap = new HashMap<Integer, Long>();
        for (Map.Entry<Long, Integer> vals : minMap.entrySet()) {
            insertMaxMap(maxMap, vals.getValue(), vals.getKey());
        }

        long[] result = new long[arr.length];

        TreeSet<Integer> keySet = new TreeSet<Integer>(Collections.reverseOrder());
        keySet.addAll(maxMap.keySet());

        int lastIndex = arr.length - 1;
        long lastValue = -1;
        for (Integer current : keySet) {
            long value = maxMap.get(current);
            if (value > lastValue) {
                for (int i = current; i < lastIndex; i++) {
                    result[i] = lastValue;
                }

                lastIndex = current;
                lastValue = value;
            }
        }

        for (int i = 0; i < lastIndex; i++) {
            result[i] = lastValue;
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
