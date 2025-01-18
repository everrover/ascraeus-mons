package dsa.leetcode.mithrim_montez;

import java.util.*;

public class MinimumCostToMakeAtLeastOneValidPathInGrid {

  /**
   * https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/?envType=daily-question&envId=2025-01-18
   *
   * Build a graph where grid[i][j] is connected to all the four side-adjacent cells with weighted edge.
   * The weight is 0 if the sign is pointing to the adjacent cell or 1 otherwise.
   * Do BFS from (0, 0) visit all edges with weight = 0 first.
   * The answer is the distance to (m -1, n - 1).
   * 
   * TC: O(m * n)
   * SC: O(m * n)
   * #array #bfs #graph #heap #matrix #shortest-path #hard
   */

  public int minCost(int[][] grid) {
    final int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    int m = grid.length, n = grid[0].length;
    int[][] v = new int[m][n];
    for (int[] vv : v) Arrays.fill(vv, Integer.MAX_VALUE);
    Deque<T> pq = new ArrayDeque<>();
    pq.offerFirst(new T(0,0,0));
    while (!pq.isEmpty()) {
      T curr = pq.pollFirst();
      if (v[curr.x][curr.y] <= curr.c) continue;
      v[curr.x][curr.y] = curr.c;
      for (int i = 0; i < dirs.length; i++) {
        int[] dir = dirs[i];
        int nx = curr.x + dir[0], ny = curr.y + dir[1];
        if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
        int cost = curr.c + (grid[curr.x][curr.y] != i + 1 ? 1 : 0);
        if (v[nx][ny] > cost) pq.offerFirst(new T(nx, ny, cost));
      }
    }
    return v[m-1][n-1];
  }

  private static class T {
    int x, y, c;
    T(int x, int y, int c) {
      this.x = x;
      this.y = y;
      this.c = c;
    }
  }
}