package hackerrank.jumpingontheclouds;

import java.io.*;

public class Solution {

    private static boolean isSafe(String c) {
        if (c.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nline = br.readLine();
        int n  = Integer.parseInt(nline);

        String cLine = br.readLine();
        String[] cTokens = cLine.trim().split("\\s+");

        int jumps = 0, i = 0, bound = n - 1;
        while (i < bound) {
            if (i + 1 == bound) {
                // First bound
                i += 1;
                jumps += 1;
            } else if (i + 2 == bound) {
                // Second bound
                i += 2;
                jumps += 1;
            } else if (isSafe(cTokens[i + 2])) {
                i += 2;
                jumps += 1;
            } else if (isSafe(cTokens[i + 1])) {
                i += 1;
                jumps += 1;
            }
            else {
                // Broken case
                break;
            }
        }
        System.out.println(jumps);
    }
}