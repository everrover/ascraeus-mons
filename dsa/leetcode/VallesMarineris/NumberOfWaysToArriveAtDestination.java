package dsa.leetcode.VallesMarineris;

import java.util.*;

public class NumberOfWaysToArriveAtDestination {
  /**
   * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/?envType=daily-question&envId=2025-04-04
   *
   * Dijkstra loop with a priority queue to find the shortest path from the source to all nodes.
   * For a node, if it's already visited with a shorter distance, use it from it's source.
   *
   * TC: O(n + E * log n) SC: O(n + E)
   * #dynamic-programming #graph #shortest-path #medium
   */

  private final static int M = 7+(int)1e9;
  public int countPaths(int n, int[][] roads) {
    int [][]g = new int[n][n];
    for(int []gg: g) Arrays.fill(gg, -1);
    for(int []r : roads) {
      g[r[0]][r[1]] = g[r[1]][r[0]] = r[2];
    }

    long [][]v = new long[n][2];
    for(long []vv: v) {vv[0] = Long.MAX_VALUE; vv[1] = 0;}

    bfs(0, g, v);

    return (int)v[0][1];
  }

  // v[i][0] = dist, v[i][1] = count
  private void bfs(int idx, int [][]g, long [][]v){
    final int n = g.length;
    Queue<long[]> q = new PriorityQueue<>((a,b)->(int)(a[1]-b[1])); // {node:dist}

    q.offer(new long[]{n-1, 0, n-1});
    v[n-1][0] = Integer.MAX_VALUE;
    v[n-1][1] = 1;

    while(!q.isEmpty()){
      long []polled = q.poll();
      int curr = (int)polled[0], prev = (int)polled[2];
      long dist = polled[1];
      if(v[curr][0] < dist) continue;

      if(v[curr][0] > dist) {
        v[curr][0] = dist;
        v[curr][1] = v[prev][1];
      }else {
        v[curr][1] = (v[prev][1] + v[curr][1]) % M;
        continue;
      }
      
      for(int i=0; i<n; i++){
        if(g[curr][i] == -1) continue;
        long ndist = dist+g[curr][i];
        if(v[i][0] < ndist) continue;
        q.offer(new long[]{i, ndist, curr});
      }
    }
  }
}