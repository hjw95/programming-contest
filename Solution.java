import java.io.*;
import java.util.*;

public class Solution {

    private static class Edge {
        public Edge(int i, int j) {
            this.u = i;
            this.v = j;
        }

        public int u;
        public int v;
    }

    private static class LinkedNode {
        public LinkedNode(int val) {
            this.value = val;
            this.prev = null;
            this.next = null;
        }

        public int value;
        public LinkedNode prev;
        public LinkedNode next;

        public LinkedNode find(int value) {
            if (this.value == value) {
                return this;
            }
            LinkedNode nextChain = this.next;
            while (nextChain != null) {
                if (nextChain.value == value) {
                    return nextChain;
                }
                nextChain = nextChain.next;
            }
            LinkedNode prevChain = this.prev;
            while (prevChain != null) {
                if (prevChain.value == value) {
                    return prevChain;
                }
                prevChain = prevChain.prev;
            }
            return null;
        }

        public void link(LinkedNode node) {
            if (node == null) {
                return;
            }
            LinkedNode currentEnd = this;
            while (currentEnd.next != null) {
                currentEnd = currentEnd.next;
            }
            LinkedNode nodeStart = node;
            while (nodeStart.prev != null) {
                nodeStart = nodeStart.prev;
            }
            currentEnd.next = nodeStart;
            nodeStart.prev = currentEnd;
        }
    }

    private static long solve(int n, int clib, int croad, Edge[] edges) {
        if (croad >= clib) {
            return (long)clib * n;
        }
        Map<Integer, LinkedNode> nodeMap = new HashMap<Integer, LinkedNode>();
        Set<Integer> notConnected = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            nodeMap.put(i, new LinkedNode(i));
            notConnected.add(i);
        }
        // Linking, simplified kruskal
        for (Edge e : edges) {
            LinkedNode uNode = nodeMap.get(e.u);
            LinkedNode vNode = nodeMap.get(e.v);
            if (uNode.find(e.v) == null) {
                uNode.link(vNode);
            }
        }
        // Cost calculation
        long cost = 0;
        while (notConnected.size() > 0) {
            int start = notConnected.iterator().next();
            notConnected.remove(start);
            cost += clib;
            LinkedNode startNode = nodeMap.get(start);
            LinkedNode nextChain = startNode.next;
            while (nextChain != null) {
                notConnected.remove(nextChain.value);
                cost += croad;
                nextChain = nextChain.next;
            }
            LinkedNode prevChain = startNode.prev;
            while (prevChain != null) {
                notConnected.remove(prevChain.value);
                cost += croad;
                prevChain = prevChain.prev;
            }
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int q = Integer.parseInt(br.readLine());

        long[] solution = new long[q];
        for (int i = 0; i < q; i++) {
            String[] tokens = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            int clib = Integer.parseInt(tokens[2]);
            int croad = Integer.parseInt(tokens[3]);

            Edge[] edges = new Edge[m];
            for (int j = 0; j < m; j++) {
                String[] edgeTokens = br.readLine().trim().split("\\s+");
                edges[j] = new Edge(Integer.parseInt(edgeTokens[0]) - 1, Integer.parseInt(edgeTokens[1]) - 1);
            }
            solution[i] = solve(n, clib, croad, edges);
        }

        for (int i = 0; i < q; i++) {
            System.out.println(solution[i]);
        }
        br.close();
    }
}