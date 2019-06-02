package hackerrank.sockmerchant;

import java.io.*;
import java.util.*;

public class Program {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        
        String arLine = br.readLine();
        String[] arLineTokens = arLine.trim().split("\\s+");

        int pairCount = 0;
        Map<Integer, Boolean> currentOdd = new HashMap<Integer, Boolean>();
        for (String currentArToken : arLineTokens) {
            int ari = Integer.parseInt(currentArToken);
            Boolean ariOdd = currentOdd.get(ari);
            if (ariOdd == null || (!ariOdd)) {
                currentOdd.put(ari, true);
            } else {
                pairCount += 1;
                currentOdd.put(ari, false);
            }
        }

        System.out.println(pairCount);
    }
}