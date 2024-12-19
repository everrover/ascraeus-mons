package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class MinimumTimeToVisitACellInAGrid {

  /**
   * https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/
   *
   * ~ logic as in FindMinimumTimeToReachLastRoomIi.java.
   * 
   * However, instead of waiting for threshold to be reached, we move back and forth between
   * current and previous cell to wait for the threshold to be reached.
   *
   * TC: O(m * n * log(m * n)) SC: O(m * n)
   * #array #breadth-first-search #graph #heap #matrix #shortest-path #hard
   */

  private static class T {
    public int r, c, t;
    public T(int r, int c, int t){
      this.r = r;
      this.c = c;
      this.t = t;
    }
  }

  private final static int [][]dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

  public int minimumTime(int[][] grid) {
    final int m = grid.length;
    final int n = m==0?0:grid[0].length;
    if(m == 0 || n == 0) return 0;
    else if(grid[0][1] > 1 && grid[1][0] > 1) return -1;

    Queue<T> pq = new PriorityQueue<>((a,b)->(a.t-b.t));
    int [][]v = new int[m][n];
    for(int []d: v) Arrays.fill(d, Integer.MAX_VALUE);
    pq.offer(new T(0,0,0));

    while(!pq.isEmpty()){
      T curr = pq.poll();
      if(curr.r == m-1 && curr.c == n-1) return curr.t;
      else if(v[curr.r][curr.c] < curr.t) continue;
      v[curr.r][curr.c] = curr.t;
      for(int []dir: dirs){
        int r = curr.r+dir[0], c = curr.c+dir[1];
        if(r<0 || r>=m || c<0 || c>=n) continue; // Skip invalid positions
        int next = Math.max(
          ((grid[r][c]-curr.t)%2==0?1:0)+grid[r][c], // go back and forth between current and previous cell
          curr.t+1
        );
        if(v[r][c] > next){
          v[r][c] = next;
          pq.offer(new T(r,c,next));
        }
      }
    }
    return -1;
  }
}