package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/
 * 
 * Got stuck on the wrong path. Tried using checks on both cost and time, but it's throwing
 * a TLE. Presented in comments #1;
 *
 * Transform the problem into finding the shortest path with constraints along state graph of
 * cities and time. 
 * Use dijkstra algorithm then. 
 * Because we're using dijkstra, first time we hit `N-1` that's the cheapest cost...
 *
 * TC: O(E + V * logV) where V is the number of cities, and E is the number of roads
 * SC: O(n * maxTime)
 * #graph #shortest-path #dijkstra #hard
 */

class MinimumCostToReachDestinationInTime {

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
  /**
  private static class T {
    public int f, t, cost, time;
    public T(int t, int cost, int time){
      // this.f = f;
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
    int []dpcost = new int[N];
    int []dptime = new int[N];
    Arrays.fill(dpcost, Integer.MAX_VALUE); dpcost[0] = passingFees[0];
    Arrays.fill(dptime, Integer.MAX_VALUE); dptime[0] = 0;
    
    Queue<T> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
    
    int res = Integer.MAX_VALUE;
    pq.offer(new T(0, passingFees[0], 0));
    while(!pq.isEmpty()){
      T edge = pq.poll();
      
      if(edge.t == N-1 && edge.time <= maxTime) {
        res = Math.min(res, edge.cost);
        break;
      }
      for(int []to: adj.get(edge.t)){
        if(edge.time+to[1] <= maxTime && edge.cost+passingFees[to[0]] < dpcost[to[0]]){
          dpcost[to[0]] = edge.cost+passingFees[to[0]];
          dptime[to[0]] = edge.time+to[1];
          pq.offer(new T(to[0], dpcost[to[0]], dptime[to[0]]));
        }else if(edge.time+to[1] <= dptime[to[0]]){
          dptime[to[0]] = edge.time+to[1];
          pq.offer(new T(to[0], edge.cost+passingFees[to[0]], dptime[to[0]]));
        }
      }
    }
    // System.out.println("-----------"+N);
    if(res == Integer.MAX_VALUE) return -1;
    return res;
  }
   */
}