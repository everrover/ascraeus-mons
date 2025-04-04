package dsa.leetcode.VallesMarineris;

import java.util.*;

public class NumberOfWaysToArriveAtDestination {
  /**
   * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/?envType=daily-question&envId=2025-04-04
   *
   * To find the number of ways to reach the destination in the shortest time, use a shortest path algorithm such as Dijkstra's. Track 
   * the number of ways each node can be reached using dynamic programming.
   *
   * TC: O(n + E * log n) SC: O(n + E)
   * #dynamic-programming #graph #shortest-path #medium
   */

  static final int M = 1000000007;

  public int countPaths(int n, int[][] roads) {
    List<int[]>[] g = new ArrayList[n];
    for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
    for (int[] road : roads) {
      g[road[0]].add(new int[]{road[1], road[2]});
      g[road[1]].add(new int[]{road[0], road[2]});
    }
    long[][] v = new long[n][2]; // v[i][0] stores shortest time, v[i][1] stores number of ways
    for (int i = 0; i < n; i++) Arrays.fill(v[i], Long.MAX_VALUE);
    v[0][0] = 0L;
    v[0][1] = 1L;
    Queue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    q.add(new long[]{0, 0L});
    while (!q.isEmpty()) {
      long[] values = q.poll();
      int curr = (int) values[0];
      long dist = values[1];
      if (v[curr][0] < dist) continue;
      for (int[] nd : g[curr]) {
        long ndist = dist + nd[1];
        if (v[nd[0]][0] > ndist) {
          v[nd[0]][0] = ndist;
          v[nd[0]][1] = v[curr][1];
          q.offer(new long[]{nd[0], ndist});
        } else if (v[nd[0]][0] == ndist) {
          v[nd[0]][1] = (v[curr][1] + v[nd[0]][1]) % M;
        }
      }
    }
    return (int) v[n - 1][1];
  }
}