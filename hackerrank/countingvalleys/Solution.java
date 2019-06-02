package hackerrank.countingvalleys;

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        String sLine = br.readLine();
        char[] sChars = sLine.toCharArray();

        int currentElevation = 0; // Default sea level
        int valleyCount = 0;
        for (char s : sChars) {
            if (s == 'D') {
                if(currentElevation == 0) {
                    valleyCount++;
                }
                currentElevation--;
            } else {
                currentElevation++;
            }
        }

        System.out.println(valleyCount);
    }
}