package dsa.leetcode.VallesMarineris;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/description/?envType=daily-question&envId=2025-04-05
// Given an m x n integer matrix grid and an array queries of size k, determine the maximum
// number of points obtainable by starting from the top left cell. Use BFS to gather points
// efficiently, considering point values as a threshold for exploration.
// 
// TC: O(m * n * log k) SC: O(m * n)
// #array #bfs #priority-queue #matrix #hard

public class MaxNumberOfPointsFromGridQueries {
  public int[] maxPoints(int[][] grid, int[] queries) {
    int qres = 0;
    int[][] qs = new int[queries.length][2];
    for(int i = 0; i < qs.length; i++) {
      qs[i][0] = queries[i];
      qs[i][1] = i;
    }
    Arrays.sort(qs, (a, b) -> a[0] - b[0]);

    int[] res = new int[queries.length];
    Queue<N> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
    pq.offer(new N(grid[0][0], 0, 0));
    final int m = grid.length, n = grid[0].length;
    boolean[][] v = new boolean[m][n];
    v[0][0] = true;

    for (int[] q : qs) {
      while (!pq.isEmpty() && pq.peek().val < q[0]) {
        N node = pq.poll();
        qres++;

        if (node.y < n - 1 && !v[node.x][node.y + 1]) {
          pq.offer(new N(grid[node.x][node.y + 1], node.x, node.y + 1));
          v[node.x][node.y + 1] = true;
        }
        if (node.x > 0 && !v[node.x - 1][node.y]) {
          pq.offer(new N(grid[node.x - 1][node.y], node.x - 1, node.y));
          v[node.x - 1][node.y] = true;
        }
        if (node.y > 0 && !v[node.x][node.y - 1]) {
          pq.offer(new N(grid[node.x][node.y - 1], node.x, node.y - 1));
          v[node.x][node.y - 1] = true;
        }
        if (node.x < m - 1 && !v[node.x + 1][node.y]) {
          pq.offer(new N(grid[node.x + 1][node.y], node.x + 1, node.y));
          v[node.x + 1][node.y] = true;
        }
      }
      res[q[1]] = qres;
    }
    return res;
  }

  private static class N {
    int val, x, y;
    N(int val, int x, int y) {
      this.val = val;
      this.x = x;
      this.y = y;
    }
  }
}