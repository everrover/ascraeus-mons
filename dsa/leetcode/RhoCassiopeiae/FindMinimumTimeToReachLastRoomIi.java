package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class FindMinimumTimeToReachLastRoomIi {
  /**
   * https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/
   *
   * ❗️I do questions on a daily basis, yet forgot that Dijkstra's algo uses a priority queue
   * as an optimization... wonder why???
   * 
   * Used simple Dijkstra's algorithm with a priority queue to find the shortest path.
   *
   * TC: O(n * m * log(n * m)) SC: O(n * m)
   * #array #graph #heap #matrix #shortest-path #medium
   */
  
   private static class T {
    public int r, c, t, p;
    public T(int r, int c, int t, int p){
      this.r = r;
      this.c = c;
      this.t = t;
      this.p = p;
    }
  }
  private final static int [][]dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
  public int minTimeToReach(int[][] moveTime) {
    final int m = moveTime.length;
    final int n = m==0?0:moveTime[0].length;
    if(m == 0 || n == 0) return 0;
    Queue<T> pq = new PriorityQueue<>((a,b)->(a.t-b.t));
    int [][]dp = new int[m][n];
    for(int []d: dp) Arrays.fill(d, Integer.MAX_VALUE);
    // for(int []d: dp) Arrays.fill(d, -1);
    pq.offer(new T(0,0,0,0));
    while(!pq.isEmpty()){
      T curr = pq.poll();
      if(curr.r == m-1 && curr.c == n-1) return curr.t;
      else if(dp[curr.r][curr.c] <= curr.t) continue;
      dp[curr.r][curr.c] = curr.t;
      for(int []dir: dirs){
        int r = curr.r+dir[0], c = curr.c+dir[1];
        if(r<0 || r>=m || c<0 || c>=n) continue;
        int wait = (curr.p%2==0?1:2) + Math.max(moveTime[r][c], curr.t);
        if(dp[r][c] > wait){
        // if(dp[nr][nc] == -1 || dp[nr][nc] > cost) {
          pq.offer(new T(r,c,wait,curr.p+1));
        }
      }
    }
    return -1;
  }

}