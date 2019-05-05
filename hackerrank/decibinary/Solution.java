package hackerrank.decibinary;

import java.io.*;
import java.util.*;

public class Solution {
    private static long[] tenPower;

    private static ArrayList<Long> everything;

    private static ArrayList<Long> result;

    private static int generateSize = 1000;

    // O(1) functions

    // Add to index
    private static long add(int i, long value) {
        return value + tenPower[i];
    }

    // Get value of index
    private static int get(int i, long value) {
        long div = value / tenPower[i];
        return (int) (div % 10);
    }

    // Set value of index
    private static long set(int i, long value, int digit) {
        long rem = value % tenPower[i];
        long div = (value / tenPower[i + 1]) * 10;
        return ((div + digit) * tenPower[i]) + rem;
    }

    private static void decibinaryAdd(int i, long value, ArrayList<Long> result) {
        int digit = get(i, value);
        switch (digit) {
        case 1: {
            result.add(add(i, value));
            long digitZero = set(i, value, 0);
            decibinaryAdd(i + 1, digitZero, result);
            break;
        }
        case 9: {
            break;
        }
        default:
            result.add(add(i, value));
            break;
        }
    }

    private static ArrayList<Long> decibinaryAdd(ArrayList<Long> values) {
        ArrayList<Long> result = new ArrayList<Long>();
        for (long v : values) {
            decibinaryAdd(0, v, result);
        }
        return result;
    }

    private static void generate() {
        tenPower = new long[19];
        long c = 1;
        for (int i = 0; i < 19; i++) {
            tenPower[i] = c;
            c *= 10;
        }
        ArrayList<Long> prev = new ArrayList<Long>();
        prev.add(0L);
        everything = new ArrayList<Long>();
        everything.add(0L);
        for (int i = 1; i < generateSize; i++) {
            prev = decibinaryAdd(prev);
            Collections.sort(prev);
            everything.addAll(prev);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String qline = br.readLine();
        int q = Integer.parseInt(qline);

        result = new ArrayList<Long>();
        generate();
        for (int i = 0; i < q; i++) {
            String xline = br.readLine();
            int x = Integer.parseInt(xline);
            result.add(everything.get(x - 1));
        }

        for (long l : result) {
            System.out.println(l);
        }
    }
}