package hackerrank.candies;

import java.io.*;
import java.util.*;

public class Solution {

    private static class MinMax {
        public MinMax(int p, boolean m) {
            this.position = p;
            this.isMin = m;
        }

        public int position;
        public boolean isMin;
    }

    private static Queue<MinMax> arrangement;

    private static int[] data;

    private static boolean isMin(int p) {
        boolean result = false;
        if (p == 0) {
            if (data[0] > data[1]) {
                result = false;
            } else {
                result = true;
            }
        } else if (p == data.length - 1) {
            if (data[p] > data[p - 1]) {
                result = false;
            } else {
                result = true;
            }
        } else {
            if (data[p - 1] >= data[p] && data[p + 1] >= data[p]) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    private static boolean isMax(int p) {
        boolean result = false;
        if (p == 0) {
            if (data[0] > data[1]) {
                result = true;
            } else {
                result = false;
            }
        } else if (p == data.length - 1) {
            if (data[p] > data[p - 1]) {
                result = true;
            } else {
                result = false;
            }
        } else {
            if (data[p - 1] < data[p] && data[p + 1] <= data[p]) {
                result = true;
            } else if (data[p - 1] <= data[p] && data[p + 1] < data[p]) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nLine = br.readLine();
        int n = Integer.parseInt(nLine);

        data = new int[n];
        arrangement = new LinkedList<MinMax>();

        for (int i = 0; i < n; i++) {
            String arri = br.readLine();
            data[i] = Integer.parseInt(arri);
        }

        for (int i = 0; i < n; i++) {
            if (isMin(i)) {
                arrangement.offer(new MinMax(i, true));
            }
            if (isMax(i)) {
                arrangement.offer(new MinMax(i, false));
            }
        }

        int[] result = new int[n];
        MinMax current = arrangement.poll();
        while (arrangement.peek() != null) {
            MinMax next = arrangement.peek();

            if (current.isMin && next.isMin) {
                // vvv
                for (int i = current.position; i <= next.position; i++) {
                    result[i] = 1;
                }
            } else if (current.isMin && (!next.isMin)) {
                // v/^
                int increment = 1;
                for (int i = current.position; i < next.position; i++) {
                    result[i] = increment;
                    increment++;
                }
                if (increment > result[next.position]) {
                    result[next.position] = increment;
                }
            } else {
                // ^\v
                int increment = 1;
                for (int i = next.position; i > current.position; i--) {
                    result[i] = increment;
                    increment++;
                }
                if (increment > result[current.position]) {
                    result[current.position] = increment;
                }
            }

            current = arrangement.poll();
        }
        long total = 0;
        for (int candies : result) {
            total += candies;
        }

        System.out.println(total);
    }
}