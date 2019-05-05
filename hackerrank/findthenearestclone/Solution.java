package hackerrank.findthenearestclone;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node {
        public long color;
        public int value;
        public ArrayList<Node> child = new ArrayList<Node>();
    }

    private static class NodeQueue {
        public NodeQueue(int s, long d, Node n) {
            this.starting = s;
            this.distance = d;
            this.node = n;
        }

        public int starting;
        public long distance;
        public Node node;
    }

    private static Node[] nodes;

    private static long solve(long color) {
        Queue<NodeQueue> q = new LinkedList<NodeQueue>();
        Map<Integer, Map<Integer, Boolean>> covered = new HashMap<Integer, Map<Integer, Boolean>>();
        for (Node n : nodes) {
            if (n.color == color) {
                q.offer(new NodeQueue(n.value, 0, n));
            }
            set(covered, n.value, n.value);
        }
        while (q.peek() != null) {
            NodeQueue current = q.poll();
            for (Node n : current.node.child) {
                if (n.color == color && current.starting != n.value) {
                    return current.distance + 1;
                }
                if (!get(covered, current.starting, n.value)) {
                    set(covered, current.starting, n.value);
                    q.offer(new NodeQueue(current.starting, current.distance + 1, n));
                }
            }
        }
        return -1;
    }

    private static boolean get(Map<Integer, Map<Integer, Boolean>> coveredDistance, int starting, int target) {
        if (!coveredDistance.containsKey(starting)) {
            return false;
        }
        Map<Integer, Boolean> map = coveredDistance.get(starting);
        if (!map.containsKey(target)) {
            return false;
        }
        return true;
    }

    private static void set(Map<Integer, Map<Integer, Boolean>> coveredDistance, int starting, int target) {
        if (!coveredDistance.containsKey(starting)) {
            coveredDistance.put(starting, new HashMap<Integer, Boolean>());
        }
        Map<Integer, Boolean> map = coveredDistance.get(starting);
        map.put(target, true);
    }

    private static class CustomReader {
        BufferedReader br;

        public CustomReader() throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            // br = new BufferedReader(new FileReader("input.txt"));
        }

        private boolean terminateToken(int i) {
            if (i == -1) {
                return true;
            }
            char c = (char) i;
            if (c == ' ' || c == '\n' || c == '\r') {
                return true;
            }
            return false;
        }

        public String next() throws IOException {
            StringBuilder response = new StringBuilder();
            int c = br.read();
            while (terminateToken(c) && c != -1) {
                c = br.read();
            }
            while (!terminateToken(c)) {
                response.append((char) c);
                c = br.read();
            }
            return response.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public void close() throws IOException {
            br.close();
        }
    }

    public static void main(String[] args) throws IOException {
        CustomReader scanner = new CustomReader();

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].value = i;
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            nodes[u].child.add(nodes[v]);
            nodes[v].child.add(nodes[u]);
        }

        for (int i = 0; i < n; i++) {
            nodes[i].color = scanner.nextLong();
        }

        long color = scanner.nextLong();

        scanner.close();

        System.out.println(solve(color));
    }
}