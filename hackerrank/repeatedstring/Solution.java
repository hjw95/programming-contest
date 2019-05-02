package hackerrank.repeatedstring;

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s =  br.readLine();
        String nLine = br.readLine();
        long n = Long.parseLong(nLine);

        int sLength = s.length();
        long repetition = n / sLength;
        int remainder = (int)(n % sLength);

        int aCount = 0;
        int remaCount = 0;

        char[] schar = s.toCharArray();
        for (int i = 0; i < remainder; i++) {
            if (schar[i] == 'a') {
                remaCount += 1;
                aCount += 1;
            }
        }
        for (int i = remainder; i < sLength; i++) {
            if (schar[i] == 'a') {
                aCount += 1;
            }
        }

        long total = (repetition * aCount) + remaCount;

        System.out.println(total);
    }
}