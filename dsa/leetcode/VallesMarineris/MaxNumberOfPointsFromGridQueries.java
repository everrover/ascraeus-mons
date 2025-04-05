package dsa.leetcode.VallesMarineris;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;


public class MaxNumberOfPointsFromGridQueries {
  // https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/description/?envType=daily-question&envId=2025-04-05
  
  // Given an m x n integer matrix grid and an array queries of size k, determine the maximum
  // number of points obtainable by starting from the top left cell. Use BFS to gather points
  // efficiently, considering point values as a threshold for exploration.
  // 
  // Union-find algo can be used to track the minimum number query amount needed to reach a score,
  // and binary search can be used to find the maximum score that can be achieved for each query.
  // 
  // TC: O(m * n * log k) SC: O(m * n)
  // #array #bfs #priority-queue #matrix #hard
  private static class N {
    public int val, x, y;
    public N(int val, int x, int y){
      this.val = val;
      this.x = x;
      this.y = y;
    }
  }
  public int[] maxPoints(int[][] grid, int[] queries) {
    Queue<N> pq = new PriorityQueue<>((a,b)->a.val-b.val);
    final int m = grid.length, n = grid[0].length;
    int qres = 0;
    int [][]qs = new int[queries.length][2];
    for(int i=0; i<qs.length; i++){
      qs[i][0] = queries[i];
      qs[i][1] = i;
    }
    int []res = new int[queries.length];
    Arrays.sort(qs, (a,b)->a[0]-b[0]);
    pq.offer(new N(grid[0][0], 0, 0));
    boolean v[][] = new boolean[m][n];
    for(int []q: qs){
      while(!pq.isEmpty() && pq.peek().val<q[0]){
        N node = pq.poll();
        if(v[node.x][node.y]) continue;
        v[node.x][node.y] = true;
        qres++;
        if(node.x < m-1 && !v[node.x+1][node.y]) pq.offer(new N(grid[node.x+1][node.y], node.x+1, node.y));
        if(node.y < n-1 && !v[node.x][node.y+1]) pq.offer(new N(grid[node.x][node.y+1], node.x, node.y+1));
        if(node.x > 0 && !v[node.x-1][node.y]) pq.offer(new N(grid[node.x-1][node.y], node.x-1, node.y));
        if(node.y > 0 && !v[node.x][node.y-1]) pq.offer(new N(grid[node.x][node.y-1], node.x, node.y-1));
      }
      res[q[1]] = qres;
    }
    return res;
  }
}