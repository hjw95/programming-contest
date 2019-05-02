package hackerrank.abbreviation;

import java.io.*;
import java.util.*;

public class Solution {

    // apos -> bpos -> match
    private static Map<Integer, Map<Integer, Boolean>> data;
    private static Map<Integer, Boolean> dataRemovable;

    private static char[] a;
    private static char[] b;

    private static boolean isSet(Integer apos, Integer bpos) {
        if (!data.containsKey(apos)) {
            return false;
        }
        Map<Integer, Boolean> bmap = data.get(apos);
        if (!bmap.containsKey(bpos)) {
            return false;
        }
        return true;
    }

    private static void set(Integer apos, Integer bpos, Boolean match) {
        if (!data.containsKey(apos)) {
            data.put(apos, new HashMap<Integer, Boolean>());
        }
        Map<Integer, Boolean> bmap = data.get(apos);
        bmap.put(bpos, match);
    }

    private static boolean get(Integer apos, Integer bpos) {
        return data.get(apos).get(bpos);
    }

    private static boolean isLower(char c) {
        if (c >= 97 && c <= 122) {
            return true;
        }
        return false;
    }

    private static char capitalize(char c) {
        return (char) (c - 32);
    }

    private static boolean equalable(char ac, char bc) {
        if (ac == bc) {
            // a == a or A == A
            return true;
        }
        if (isLower(ac)) {
            // a == A
            return capitalize(ac) == bc;
        }
        return false;
    }

    private static boolean matchable(int apos, int bpos) {
        if (isSet(apos, bpos)) {
            return get(apos, bpos);
        }

        if (bpos == b.length) {
            if (truncable(apos)) {
                set(apos, bpos, true);
                return true;
            } else {
                set(apos, bpos, false);
                return false;
            }
        }

        if (apos == a.length) {
            set(apos, bpos, false);
            return false;
        }

        boolean result = false;
        if (equalable(a[apos], b[bpos])) {
            // Match
            result = matchable(apos + 1, bpos + 1);
        }

        if (isLower(a[apos])) {
            // Skip
            result |= matchable(apos + 1, bpos);
        }

        set(apos, bpos, result);
        return result;
    }

    private static boolean truncable(int apos) {
        if (dataRemovable.containsKey(apos)) {
            return dataRemovable.get(apos);
        }

        if (apos == a.length) {
            dataRemovable.put(apos, true);
            return true;
        }

        if (!isLower(a[apos])) {
            dataRemovable.put(apos, false);
            return false;
        }

        boolean result = truncable(apos + 1);
        dataRemovable.put(apos, result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String qLine = br.readLine();
        int q = Integer.parseInt(qLine);

        boolean[] result = new boolean[q];
        for (int i = 0; i < q; i++) {
            a = br.readLine().toCharArray();
            b = br.readLine().toCharArray();
            data = new HashMap<Integer, Map<Integer, Boolean>>();
            dataRemovable = new HashMap<Integer, Boolean>();
            result[i] = matchable(0, 0);
        }

        for (boolean resultItem : result) {
            if (resultItem) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}