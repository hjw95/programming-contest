package hackerrank.maxarraysum;

import java.io.*;
import java.text.*;
import java.util.concurrent.*;
import java.util.*;

public class Solution {

    private static boolean[] subsetDataSet;
    private static long[] subsetData;
    private static long[] arrData;

    private static long maxNonAdjacentSubsetSum(int start) {
        if (subsetDataSet[start]) {
            // known case
            return subsetData[start];
        }

        if (start == arrData.length - 1 || start == arrData.length - 2) {
            // base case
            subsetData[start] = arrData[start];
            subsetDataSet[start] = true;
            return arrData[start];
        }

        long maxSubset = maxNonAdjacentSubsetSum(start + 2);

        if (start + 3 < arrData.length) {
            long subSetThree = maxNonAdjacentSubsetSum(start + 3);
            if (subSetThree > maxSubset) {
                maxSubset = subSetThree;
            }
        }

        long subsetResult = 0;
        if (arrData[start] < 0) {
            if (arrData[start] < maxSubset) {
                subsetResult = maxSubset;
            } else {
                subsetResult = arrData[start];
            }
        } else {
            if (maxSubset < 0) {
                subsetResult = arrData[start];
            } else {
                subsetResult = maxSubset + arrData[start];
            }
        }

        subsetData[start] = subsetResult;
        subsetDataSet[start] = true;

        return subsetResult;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        // Scanner scanner = new Scanner(new File("input.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        // Subset data set is used to make checks easier and faster
        subsetDataSet = new boolean[n];
        subsetData = new long[n];
        arrData = new long[n];

        int j = 0;
        while (scanner.hasNextInt() && j < n) {
            arrData[j] = scanner.nextInt();
            j++;
        }

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        scanner.close();

        long result = 0;
        boolean resultSet = false;
        for (int i = n - 1; i >= 0; i--) {
            if (!resultSet) {
                result = maxNonAdjacentSubsetSum(i);
                resultSet = true;
            } else {
                long currentResult = maxNonAdjacentSubsetSum(i);
                if (currentResult > result) {
                    result = currentResult;
                    resultSet = true;
                }
            }
        }

        System.out.println(result);
    }
}