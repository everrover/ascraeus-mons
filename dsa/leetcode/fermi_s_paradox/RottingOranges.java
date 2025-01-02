package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class RottingOranges {
  /**
   * https://leetcode.com/problems/rotting-oranges/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
   *
   * To find the minimum number of minutes for all fresh oranges to become rotten, use a Breadth-First Search (BFS) strategy.
   * Use a queue to keep track of rotten oranges and iterate for each minute to spread rot to adjacent fresh oranges.
   *
   * TC: O(m * n) SC: O(m * n)
   * #array #breadth-first-search #matrix #medium
   */
  public int orangesRotting(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int fresh = 0, res = 0;
    Queue<int[]> q = new LinkedList<>();
    boolean[][] v = new boolean[m][n]; // Visited array
    int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) q.offer(new int[]{i, j}); // Add all initially rotten oranges to queue
        if (grid[i][j] == 1) fresh++; // Count fresh oranges
      }
    }
    while (!q.isEmpty() && fresh > 0) {
      res++;
      int size = q.size();
      for (int s = 0; s < size; s++) {
        int[] co = q.poll();
        for (int[] move: moves) {
          int r = co[0] + move[0], c = co[1] + move[1];
          if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0 || grid[r][c] == 2 || v[r][c]) continue;
          grid[r][c] = 2; // Rot fresh orange.
          fresh--;
          v[r][c] = true; // Mark as visited.
          q.offer(new int[]{r, c}); // Add newly rotten orange to queue.
        }
      }
    }
    return fresh == 0 ? res : -1; // Only return res if no fresh oranges are left
  }
}