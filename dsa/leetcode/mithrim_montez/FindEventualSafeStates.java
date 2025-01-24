package dsa.leetcode.mithrim_montez;

import java.util.*;

public class FindEventualSafeStates {

  /**
   * https://leetcode.com/problems/find-eventual-safe-states/description/?envType=daily-question&envId=2025-01-24
   *
   * The algorithm uses depth-first search (DFS) to identify safe nodes.
   * For each node, we perform a DFS to see if it eventually leads to a terminal node.
   * A coloring schema is used where nodes marked with `0` are unvisited, `1` are visited
   * and currently in the DFS path, and `2` are safe.
   * Each node is evaluated, and if deemed safe, it is added to the result.
   *
   * TC: O(n + e), SC: O(n)
   * #depth-first-search #graph #topological-sort #medium
   */ 

  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;
    int[] v = new int[n];
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (dfs(i, v, graph, res)) {
        res.add(i);
      }
    }
    Collections.sort(res);
    return res;
  }

  private boolean dfs(int idx, int[] v, final int[][] graph, final List<Integer> res) {
    if (v[idx] != 0) return v[idx] == 2;
    v[idx] = 1;
    for (int next : graph[idx]) {
      boolean isSafe = true;
      isSafe = isSafe && dfs(next, v, graph, res);
      if (!isSafe) return false;
    }
    v[idx] = 2;
    return true;
  }

}