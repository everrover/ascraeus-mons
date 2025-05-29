package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MaximizeTheNumberOfTargetNodesAfterConnectingTrees {

  /**
   * https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
   *
   * For each node in the first tree, determine the number of nodes that can be reached from it within a distance of at most k.
   * For each node in the second tree, determine the maximum number of nodes that can be reached within a distance of k-1.
   *
   * TC: O(n * m * (n + m)) SC: O(n + m)
   * #tree #bfs #dfs #medium
   */

  public int[] solve(int[][] edges1, int[][] edges2, int k) {
    Map<Integer, List<Integer>> g1 = new HashMap<>();
    Map<Integer, List<Integer>> g2 = new HashMap<>();
    buildGraph(edges1, g1);
    buildGraph(edges2, g2);

    int g2max = 0;
    int[] res = new int[g1.size()];
    for(Map.Entry<Integer, List<Integer>> e: g2.entrySet()) {
      g2max = Math.max(g2max, bfs(e.getKey(), k-1, g2));
    }
    int i = 0;
    for(Map.Entry<Integer, List<Integer>> e: g1.entrySet()) {
      res[i++] = bfs(e.getKey(), k, g1) + g2max;
    }
    return res;
  }

  private int bfs(final int idx, final int k, final Map<Integer, List<Integer>> g) {
    if(k == 0) return 1;
    boolean []v = new boolean[g.size()];
    Queue<Integer> q = new LinkedList<>();
    q.offer(idx);
    int cnt = 0, kdx = 0;
    while(!q.isEmpty() && kdx <= k) {
      int sz = q.size();
      kdx++;
      while(sz-- > 0) {
        int curr = q.poll();
        if(!v[curr]) {
          v[curr] = true;
          cnt++;
          for(int neighbor : g.getOrDefault(curr, new ArrayList<>())) {
            if(!v[neighbor]) q.offer(neighbor);
          }
        }
      }
    }
    return cnt;
  }

  private void buildGraph(int[][] edges, Map<Integer, List<Integer>> graph) {
    for(int[] edge : edges) {
      graph.putIfAbsent(edge[0], new ArrayList<>());
      graph.putIfAbsent(edge[1], new ArrayList<>());
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
  }
}