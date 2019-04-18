package codechef.beginner.hs08test;

import java.io.*;

class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();

        String[] strs = lines.trim().split("\\s+");

        double x = Double.parseDouble(strs[0]);
        double y = Double.parseDouble(strs[1]);

        double res = y;
        if (x % 5 == 0 && y >= (x + 0.5) && x >= 0 && y >= 0) {
            res = res - x - 0.5;
        }

        System.out.println(String.format("%.2f", res));
    }
}