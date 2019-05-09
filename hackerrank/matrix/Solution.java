package hackerrank.matrix;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node {
        public Node(int v) {
            this.value = v;
            this.distance = new HashMap<Integer, Integer>();
            this.currentShortest = 0;
            this.isMachine = false;
            this.connection = new ArrayList<Node>();
        }

        public int value;

        public int currentShortest;

        public boolean isMachine;

        private ArrayList<Node> connection;

        private HashMap<Integer, Integer> distance;

        public void connect(Node node, int distance) {
            this.connection.add(node);
            this.distance.put(node.value, distance);
        }

        public int disconnect(Node node) {
            if (!connection.contains(node)) {
                return 0;
            }

            if (!node.isMachine) {
                this.connection.remove(node);
                node.connection.remove(this);
                return 0;
            }

            this.connection.remove(node);
            node.connection.remove(this);

            int left = this.distance.get(node.value);
            if (node.currentShortest != 0 && node.currentShortest < left) {
                left = node.currentShortest;
            }
            int right = this.currentShortest;

            if (this.isMachine) {
                if (right == 0) {
                    return left;
                } else if (left < right) {
                    return left;
                } else {
                    this.currentShortest = left;
                    return right;
                }
            } else {
                if (right == 0 || left < right) {
                    this.currentShortest = left;
                } else {
                    this.currentShortest = right;
                }
                this.isMachine = true;
                return 0;
            }
        }

        public boolean queue() {
            if (connection.size() == 1) {
                return true;
            } else {
                return false;
            }
        }

        public Node singular() {
            if (connection.size() == 1) {
                return connection.get(0);
            }
            return null;
        }
    }

    // Iteratively break off connection,
    // Maintain shortest path to destroy
    // Break off connection if is leaf and next is not machine
    // Add to queue if after connection is broken and next is leaf
    // If is leaf and next is machine, break connection
    // and add distance to the total
    // Start with adding all leaves into queue
    private static long solve(Node[] nodes) {
        Queue<Node> q = new LinkedList<Node>();
        for (Node n : nodes) {
            if (n.queue()) {
                q.offer(n);
            }
        }
        long total = 0;
        while (q.peek() != null) {
            Node current = q.poll();
            Node connected = current.singular();
            if (connected == null) {
                continue;
            }
            total += connected.disconnect(current);
            if (connected.queue()) {
                q.offer(connected);
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        String[] tokens = br.readLine().trim().split("\\s+");

        int n = Integer.parseInt(tokens[0]);
        int k = Integer.parseInt(tokens[1]);

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n - 1; i++) {
            tokens = br.readLine().trim().split("\\s+");
            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            int d = Integer.parseInt(tokens[2]);
            nodes[u].connect(nodes[v], d);
            nodes[v].connect(nodes[u], d);
        }

        for (int i = 0; i < k; i++) {
            int m = Integer.parseInt(br.readLine());
            nodes[m].isMachine = true;
        }

        long result = solve(nodes);
        System.out.println(result);

        br.close();
    }
}