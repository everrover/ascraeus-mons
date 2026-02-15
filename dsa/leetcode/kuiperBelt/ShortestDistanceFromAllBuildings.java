package dsa.leetcode.KuiperBelt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 * 
 * The only thing is that all buildings should be reachable from all buildings. Hence, we use
 * `cnts` to track the number of buildings that can reach each building.
 *
 * Perform a BFS from each building to calculate the shortest distance to every building.
 * Each land counts the distance from it to all buildings, and the result is the minimum of these 
 * sums.
 *
 * TC: O(m^2*n^2) SC: O(m*n)
 * #breadth-first-search #matrix #hard #bfs #queue #shortest-path #graph #brute-force
 */
class ShortestDistanceFromAllBuildings {
    private int m, n;
    public int shortestDistance(int[][] grid) {
      m = grid.length; n = grid[0].length;
      int res = Integer.MAX_VALUE, cnt = 0;
      int [][]cnts = new int[m][n]; // calculate the number of points from where we can reach the land
      for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
          if(bfs(i, j, grid, cnts)) cnt++;
        }
      }

      for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
          // if it's a building/obstacle that is not reachable from all buildings we skip those points
          if(grid[i][j] >= 1 || cnts[i][j] != cnt) continue;
          res = Math.min(res, -grid[i][j]);
        }
      }

      return res==Integer.MAX_VALUE?-1:res;
    }

    private static class T {
      public int a, b, d;
      public T(int a, int b, int d){
        this.a = a; this.b = b; this.d = d;
      }
    }

    private boolean bfs(int i, int j, int [][]grid, int [][]cnts){
      Queue<T> q = new LinkedList<>();
      if(grid[i][j] != 1) return false;// start from a building
      if(i > 0) q.offer(new T(i-1, j, 1));
      if(j > 0) q.offer(new T(i, j-1, 1));
      if(j < n-1) q.offer(new T(i, j+1, 1));
      if(i < m-1) q.offer(new T(i+1, j, 1));
      boolean [][]v = new boolean[m][n];
      while(!q.isEmpty()){
        T polled = q.poll();
        // if(!isF && grid[polled.a][polled.b] == 0) return false;
        if(grid[polled.a][polled.b] >= 1 || v[polled.a][polled.b]) continue;
        grid[polled.a][polled.b]-=polled.d; // -ve values kept to track distance from one of the buildings within same grid
        v[polled.a][polled.b] = true;
        cnts[polled.a][polled.b]++;
        if(polled.a > 0) q.offer(new T(polled.a-1, polled.b, polled.d+1));
        if(polled.b > 0) q.offer(new T(polled.a, polled.b-1, polled.d+1));
        if(polled.b < n-1) q.offer(new T(polled.a, polled.b+1, polled.d+1));
        if(polled.a < m-1) q.offer(new T(polled.a+1, polled.b, polled.d+1));
      }
      return true;
    }
}