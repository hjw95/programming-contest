package codechef.beginner.flow001;

import java.io.*;

class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        int t = Integer.parseInt(lines);

        int[] res = new int[t];

        for (int i = 0; i < t; i++) {
            String line = br.readLine();
            String[] strs = line.trim().split("\\s+");

            int a = Integer.parseInt(strs[0]);
            int b = Integer.parseInt(strs[1]);

            res[i] = a + b;
        }

        for (int i = 0; i < t; i++) {
            System.out.println(res[i]);
        }
    }
}