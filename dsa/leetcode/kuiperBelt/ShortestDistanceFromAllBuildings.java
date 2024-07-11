package dsa.leetcode.kuiperBelt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 *
 * Perform a BFS from each building to calculate the shortest distance to every empty piece of land.
 * Each land counts the distance from it to all buildings, and the result is the minimum of these sums.
 *
 * TC: O(m^2*n^2) SC: O(m*n)
 * #breadth-first-search #matrix #hard #bfs #queue #shortest-path #graph #brute-force
 */
class ShortestDistanceFromAllBuildings {
  class Solution {
    private int m, n;
    public int shortestDistance(int[][] grid) {
      m = grid.length; n = grid[0].length;
      int res = Integer.MAX_VALUE, cnt = 0;
      int [][]cnts = new int[m][n];
      for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
          if(bfs(i, j, grid, cnts
          )) cnt++;


        }
      }

      for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
          //  System.out.println(cnts[i][j]);
          // if(grid[i][j] == 0) return -1;
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
      if(grid[i][j] != 1) return false;
      if(i > 0) q.offer(new T(i-1, j, 1));
      if(j > 0) q.offer(new T(i, j-1, 1));
      if(j < n-1) q.offer(new T(i, j+1, 1));
      if(i < m-1) q.offer(new T(i+1, j, 1));
      boolean [][]v = new boolean[m][n];
      boolean any = false;
      while(!q.isEmpty()){
        T polled = q.poll();
        // if(!isF && grid[polled.a][polled.b] == 0) return false;
        if(grid[polled.a][polled.b] >= 1 || v[polled.a][polled.b]) continue;
        grid[polled.a][polled.b]-=polled.d;
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
}