import java.io.*;
import java.util.*;

public class Solution {

    private static class Node {
        public int value;

        public HashMap<Integer, Integer> distance;

        public int currentShortest;

        public boolean isMachine;

        public ArrayList<Node> connection;
    }

    // Iteratively break of connection, 
    // Maintain shortest path to destroy
    // Break off connection if : is leaf and next is not machine
    // If is leaf and next is machine, add distance to the total


    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        String lines = br.readLine();
        String[] tokens = lines.trim().split("\\s+");

        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[1]);

        System.out.println(a + b);

        long elapsed = System.nanoTime() - start;
        System.out.println(elapsed / 1000000);
    }
}