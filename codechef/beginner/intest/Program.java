package codechef.beginner.intest;

import java.io.*;

class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        String[] strs = lines.trim().split("\\s+");

        long n = Long.parseLong(strs[0]);
        long k = Long.parseLong(strs[1]);

        long divisible = 0;

        for (long i = 0; i < n; i++) {
            String line = br.readLine();
            long t = Long.parseLong(line);

            if (t >= k && t % k == 0) {
                divisible++;
            }
        }

        System.out.println(divisible);
    }
}