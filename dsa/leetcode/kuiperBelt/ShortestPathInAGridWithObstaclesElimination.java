package dsa.leetcode.KuiperBelt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 * This solution employs BFS to navigate through the grid by considering each point as a potential step,
 * accounting for the number of obstacles that can be eliminated. It maintains a 3D DP array to track the minimum
 * cost to reach any point [(x, y) with k obstacles left to remove].
 * 
 * TC: Potentially O(m*n*k) due to traversing grid and considering k eliminations.
 * SC: O(m*n*k) for the 3D DP array to hold states.
 * 
 * #breadth-first-search #dynamic-programming #matrix #hard
 */
public class ShortestPathInAGridWithObstaclesElimination {

  private static class T{
    public int x, y, k, cost;
    public T(int x, int y, int k, int cost){
      this.x = x;
      this.y = y;
      this.k = k;
      this.cost = cost;
    }
  }
  private final static int[][] moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
  private int m, n;
  public int shortestPath(int[][] grid, int k) {
    m=grid.length; n=grid[0].length;
    int[][][] dp = new int[m][n][k+1];
    for(int [][]dd: dp)
      for(int []d: dd)
        Arrays.fill(d, Integer.MAX_VALUE);
    minCostTree(grid, dp, k);
    int res = Integer.MAX_VALUE;
    for(int i=0; i<=k; i++) res = Math.min(res, dp[m-1][n-1][i]);
    return res==Integer.MAX_VALUE?-1:res;
  }
  private void minCostTree(int [][]grid, int [][][]dp, int kk){
    Queue<T> q = new LinkedList<>();
    q.offer(new T(0, 0, kk, 0));
    while(!q.isEmpty()){
      T p = q.poll();
      if(p.k<0 || dp[p.x][p.y][p.k] <= p.cost) continue;
      dp[p.x][p.y][p.k] = p.cost;
      for(int []move: moves){
        int i=p.x+move[0], j = p.y+move[1];
        if(i<0 || i>=m || j<0 || j>=n) continue;
        q.offer(new T(i, j, p.k-grid[i][j], p.cost+1));
      }
    }
    return;
  }
}