package hackerrank.largestrectangle;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class MinMax {
        public int min;
        public int max;
    }

    static boolean valid(int index, int start, int end, boolean[] chosen) {
        if (index >= start && index <= end && !chosen[index]) {
            return true;
        }
        return false;
    }

    static long rectangleSize(int length, int height, int add) {
        if (add > height) {
            return (length + 1) * height;
        } 
        
        return (length + 1) * add;
    }


    static MinMax minmax(int[] h, int start, int end) {
        int min = h[end];
        int max = h[end];

        for (int i = start; i < end; i++) {
            if (h[i] > max) {
                max = h[i];
            }

            if (h[i] < min) {
                min  = h[i];
            }
        }

        MinMax result = new MinMax();
        result.min = min;
        result.max = max;

        return result;
    }

    // Greedy to get largest
    // Run over all range to get all possibility
    static long largestRectangle(int[] h, int start, int end, long prevLargest) {
        if (end < start) {
            return -1;
        }

        MinMax minmaxres = minmax(h, start, end);
        if (((double)minmaxres.max * (end - start + 1)) < prevLargest) {
            return -1;
        }

        int mid = ((end - start) / 2) + start;

        Queue<Integer> testI = new LinkedList<Integer>();
    
        int length = 0;
        int height = h[mid];
        int lIndex = mid - 1;
        int rIndex = mid + 1;

        long largest = prevLargest;

        boolean[] chosen = new boolean[h.length];

        testI.offer(mid);
        chosen[mid] = true;
        
        while (testI.peek() != null) {    
            int currI = testI.poll();
            chosen[currI] = true;

            long currSize = rectangleSize(length, height, h[currI]);
            if (currSize > largest) {
                largest = currSize;
            }

            if (h[currI] < height) {
                height = h[currI];
            }
            length += 1;

            boolean lValid = valid(lIndex, start, end, chosen);
            boolean rValid = valid(rIndex, start, end, chosen);

            if (lValid && rValid) {
                long lRect = rectangleSize(length, height, h[lIndex]);
                long rRect = rectangleSize(length, height, h[rIndex]);
                if (lRect > rRect) {
                    testI.offer(lIndex);
                    lIndex -= 1;
                } else {
                    testI.offer(rIndex);
                    rIndex += 1;
                }
            } else if (lValid) {
                testI.offer(lIndex);
                lIndex -= 1;
            } else if (rValid) {
                testI.offer(rIndex);
                rIndex += 1;
            }
        }

        long left = largestRectangle(h, start, mid -1, largest);
        long right = largestRectangle(h, mid + 1, end, largest);

        if (left > largest) {
            largest = left;
        }

        if (right > largest) {
            largest = right;
        }

        return largest;
    }

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
        return largestRectangle(h, 0, h.length - 1, 0);
    }

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();

        Scanner scanner = new Scanner(new File("input.txt"));
        
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }


        long elapsed = System.nanoTime() - start;
        System.out.println(elapsed/1000000);

        long result = largestRectangle(h);

        System.out.println(result);

        scanner.close();

         elapsed = System.nanoTime() - start;
        System.out.println(elapsed/1000000);
    }
}
