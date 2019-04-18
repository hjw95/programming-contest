package codechef.beginner.tsort;

import java.io.*;
import java.util.*;

class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        int t = Integer.parseInt(lines);

        long[] inp = new long[t];

        for (int i = 0; i < t; i++) {
            String line = br.readLine();
            inp[i] = Long.parseLong(line);
        }

        Arrays.sort(inp);

        for (int i = 0; i < t; i++) {
            System.out.println(inp[i]);
        }
    }
}