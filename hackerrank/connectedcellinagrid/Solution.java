package hackerrank.connectedcellinagrid;

import java.io.*;

public class Solution {

    private static boolean valid(int pos, int limit) {
        if (pos < 0)
            return false;
        if (pos < limit)
            return true;
        return false;
    }

    private static boolean visitable(int i, int j, int row, int col, int[][] grid, boolean[][] visited) {
        if (!valid(i, row)) {
            return false;
        }
        if (!valid(j, col)) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        return true;
    }

    private static int dfs(int i, int j, int row, int col, int[][] grid, boolean[][] visited) {
        visited[i][j] = true;

        if (grid[i][j] == 0) {
            return 0;
        }

        int left = j - 1;
        int right = j + 1;
        int top = i - 1;
        int bottom = i + 1;

        int total = 1;

        // top left
        if (visitable(top, left, row, col, grid, visited)) {
            total += dfs(top, left, row, col, grid, visited);
        }
        // middle left
        if (visitable(i, left, row, col, grid, visited)) {
            total += dfs(i, left, row, col, grid, visited);
        }
        // bottom left
        if (visitable(bottom, left, row, col, grid, visited)) {
            total += dfs(bottom, left, row, col, grid, visited);
        }
        // top middle
        if (visitable(top, j, row, col, grid, visited)) {
            total += dfs(top, j, row, col, grid, visited);
        }
        // bottom middle
        if (visitable(bottom, j, row, col, grid, visited)) {
            total += dfs(bottom, j, row, col, grid, visited);
        }
        // top right
        if (visitable(top, right, row, col, grid, visited)) {
            total += dfs(top, right, row, col, grid, visited);
        }
        // middle right
        if (visitable(i, right, row, col, grid, visited)) {
            total += dfs(i, right, row, col, grid, visited);
        }
        // bottom right
        if (visitable(bottom, right, row, col, grid, visited)) {
            total += dfs(bottom, right, row, col, grid, visited);
        }

        return total;
    }

    private static int solve(int row, int col, int[][] grid) {
        boolean[][] visited = new boolean[row][col];

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int current = dfs(i, j, row, col, grid, visited);
                    if (current > max) {
                        max = current;
                    }
                } else {
                    visited[i][j] = true;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().trim().split("\\s+");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int result = solve(n, m, grid);

        System.out.println(result);

        br.close();
    }
}