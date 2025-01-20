package dsa.leetcode.mithrim_montez;

import java.util.*;

public class MinimizeTheMaximumEdgeWeightOfGraph {
  
  /**
   * https://leetcode.com/problems/minimize-the-maximum-edge-weight-of-graph/description/
   * 
   * Utilize a binary search approach to find the minimal possible maximum edge weight. 
   * Check feasibility using a modified BFS/DFS approach with a threshold for outgoing edges.
   * 
   * TC: O(E * log(maxWeight)), where E is the number of edges and maxWeight is the max possible edge weight.
   * SC: O(V + E), where V is the number of nodes and E is the number of edges.
   * #binary-search #graph #bfs #dfs #medium
   */
  
  public boolean checkConnectivity(int n, Map<Integer, Map<Integer, Integer>> g, int maxw, int threshold) {
    boolean[] v = new boolean[n];
    Queue<Integer> q = new LinkedList<>();
    q.offer(0);
    int cnt = 0;
    while(!q.isEmpty()){
      int curr = q.poll();
      if(v[curr]) continue;
      v[curr] = true;
      cnt++;
      for(Map.Entry<Integer, Integer> node: g.get(curr).entrySet()){
        if(node.getValue() == 0 || v[node.getKey()] || node.getValue() > maxw) 
          continue;
        q.offer(node.getKey());
      }
    }
    return cnt == n;
  }

  public int minMaxWeight(int n, int[][] edges, int threshold) {
    // Implement binary search on edge weights with connectivity check
  }

}