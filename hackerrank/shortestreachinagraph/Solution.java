package hackerrank.shortestreachinagraph;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node {
        public Node(int label) {
            this.label = label;
            this.distance = -1;
            this.connection = new ArrayList<Node>();
        }

        public int label;

        public int distance;

        public ArrayList<Node> connection;
    }

    private static void solve(Node[] nodes, int s) {
        Queue<Node> q = new LinkedList<Node>();
        q.offer(nodes[s]);
        boolean[] visited = new boolean[nodes.length];
        visited[s] = true;
        nodes[s].distance = 0;
        while (q.peek() != null) {
            Node current = q.poll();
            for (Node n : current.connection) {
                if (!visited[n.label]) {
                    n.distance = current.distance + 6;
                    q.offer(n);
                    visited[n.label] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // long start = System.nanoTime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int q = Integer.parseInt(br.readLine());
        String[] result = new String[q];

        for (int i = 0; i < q; i++) {
            String[] tokens = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);

            Node[] nodes = new Node[n];
            for (int j = 0; j < n; j++) {
                nodes[j] = new Node(j);
            }

            for (int j = 0; j < m; j++) {
                String[] edges = br.readLine().trim().split("\\s+");
                int u = Integer.parseInt(edges[0]) - 1;
                int v = Integer.parseInt(edges[1]) - 1;
                nodes[u].connection.add(nodes[v]);
                nodes[v].connection.add(nodes[u]);
            }

            int s = Integer.parseInt(br.readLine()) - 1;

            solve(nodes, s);

            int qindex = 0;
            String qstring = "";
            for (int j = 0; j < n; j++) {
                if (j != s) {
                    qstring += nodes[j].distance;
                    qindex++;
                    if (qindex < n - 1) {
                        qstring += " ";
                    }
                }
            }
            result[i] = qstring;
        }

        for (String resultItem : result) {
            System.out.println(resultItem);
        }
        br.close();
    }
}