package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/
 *
 * Transform the problem into finding the shortest path with constraints.
 * Use a priority queue to traverse the graph, ensuring we keep track of the cost of the path and time taken.
 * Store the minimum cost to reach each city at various times.
 *
 * TC: O(E + V * logV) where V is the number of cities, and E is the number of roads
 * SC: O(n * maxTime)
 * #graph #shortest-path #dijkstra #hard
 */

class Solution {

  private static class T {
    public int t, cost, time;
    public T(int t, int cost, int time){
      this.t = t;
      this.cost = cost;
      this.time = time;
    }
  }

  public int minCost(int maxTime, int[][] edges, int[] passingFees) {
    final int N = passingFees.length;
    // prep adj list
    Map<Integer, List<int[]>> adj = new HashMap<>();
    for(int i=0; i<N; i++) adj.putIfAbsent(i, new LinkedList<>());
    for(int []edge: edges) {
      adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
      adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
    }

    // prep `dp` cost
    int [][]dp = new int[N][maxTime+1];
    for(int []d: dp) Arrays.fill(d, Integer.MAX_VALUE);

    Queue<T> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
    pq.offer(new T(0, passingFees[0], 0));
    while(!pq.isEmpty()){
      T edge = pq.poll();
      if(edge.t == N-1) return edge.cost;
      if(dp[edge.t][edge.time] <= edge.cost) continue;
      // System.out.println(edge.t+":"+edge.cost+":"+edge.time);
      dp[edge.t][edge.time] = edge.cost;
      for(int []to: adj.get(edge.t)){
        if(edge.time+to[1] > maxTime) continue;
        pq.offer(new T(to[0], edge.cost+passingFees[to[0]], edge.time+to[1]));
      }
    }
    // System.out.println("-----------"+N);
    int res = Integer.MAX_VALUE;
    for(int d: dp[N-1]) res = Math.min(res, d);
    if(res == Integer.MAX_VALUE) return -1;
    return res;
  }
}