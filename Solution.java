import java.io.*;

public class Solution {

    static final int maxDigit = 100;

    static int[] square(int[] value) {
        int[] result = new int[maxDigit];
        int starting = 0;
        for (int ld : value) {
            int current = starting;
            for (int rd : value) {
                int digitMult = (ld * rd) + result[current];
                result[current] = digitMult % 10;
                result[current + 1] += digitMult / 10;
                current++;
            }
            starting++;
        }
        return result;
    }

    static boolean match(int[] squared, int[] otherdigit) {
        return match(squared, otherdigit, otherdigit.length);
    }

    static boolean match(int[] squared, int[] otherdigit, int jl) {
        for (int i = 0, j = 0, il = squared.length; i < il && j < jl; i += 2, j += 1) {
            if (squared[i] != otherdigit[j]) {
                return false;
            }
        }
        return true;
    }

    static void print(int[] result) {
        boolean start = false;
        for (int i = maxDigit; i >= 0; i--) {
            if (i >= result.length) {
                continue;
            }
            if (result[i] != 0) {
                start = true;
            }
            if (start) {
                System.out.print(result[i]);
            }
        }
        System.out.println();
    }

    static int length(int[] result) {
        boolean start = false;
        int length = 0;
        for (int i = maxDigit; i >= 0; i--) {
            if (i >= result.length) {
                continue;
            }
            if (result[i] != 0) {
                start = true;
            }
            if (start) {
                length++;
            }
        }
        return length;
    }

    static int[] dfs(int[] current, int[] otherdigit, int index) {
        int[] squared = square(current);
        if (!match(squared, otherdigit, index - 1)) {
            return null;
        }
        if (match(squared, otherdigit)) {
            if (length(squared) == (otherdigit.length * 2) - 1) {
                return current;
            } else {
                return null;
            }
        }
        for (int i = 0; i < 10; i++) {
            current[index] = i;
            int[] result = dfs(current, otherdigit, index + 1);
            if (result != null) {
                return result;
            }
        }
        current[index] = 0;
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] otherdigit = new int[n];

        String[] tokens = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            otherdigit[i] = Integer.parseInt(tokens[n - i - 1]);
        }

        int[] current = new int[15];
        int[] result = dfs(current, otherdigit, 0);
        if (result != null) {
            print(result);
        }

        br.close();
    }
}