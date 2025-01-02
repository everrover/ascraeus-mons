package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class RottingOranges {
  /**
   * https://leetcode.com/problems/rotting-oranges/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
   *
   * Use a Breadth-First Search (BFS) with rotten oranges as the nodes from which edges emerge.
   * Use a queue to keep track of rotten oranges and iterate for each minute to spread rot to 
   * adjacent fresh oranges.
   * 
   * For each iteration before increasing time, poll only the rotten oranges that were rotten
   * in that minute, using `sz` var.
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
      int sz = q.size();
      if(fresh==0) return res; // If no fresh oranges are left, return res
      res++;
      while(sz-->0){
        int[] co = q.poll();
        if(v[co[0]][co[1]]) continue;
        v[co[0]][co[1]] = true;
        if(fresh==0) return res;
        for(int []move: moves){
          int r = co[0]+move[0], c = co[1]+move[1];
          if(r<0 || c<0 || r >= m || c>=n || grid[r][c] == 0 || grid[r][c] == 2) continue;
          grid[r][c] = 2; // Mark the fresh orange as rotten
          fresh--;
          q.offer(new int[]{r,c}); // Add the rotten orange to the queue
        }
      }
    }
    return fresh == 0 ? res : -1; // Only return res if no fresh oranges are left
  }
}