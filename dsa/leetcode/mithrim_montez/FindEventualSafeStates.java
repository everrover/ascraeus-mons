package dsa.leetcode.mithrim_montez;

import java.util.*;

public class FindEventualSafeStates {

  /**
   * https://leetcode.com/problems/find-eventual-safe-states/description/?envType=daily-question&envId=2025-01-24
   *
   * 0 = not-visited, 1 = visited, 2 = marked-unsafe-after-completely-visited, 3 = marked-safe-after-completely-visited
   * 
   * The algorithm uses depth-first search (DFS) to identify safe nodes.
   * For each node, we perform a DFS to see if it eventually leads to a terminal node.
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

  private boolean dfs(int idx, int []v, final int [][]graph, final List<Integer> res){
    if(v[idx]!=0) return v[idx] == 3;
    v[idx] = 1;
    boolean isSafe = true;
    for(int next: graph[idx]){
      isSafe = isSafe && dfs(next, v, graph, res);
    }
    if(isSafe){
      v[idx] = 3;
      res.add(idx);
    }else{
      v[idx] = 2;
    }

    return isSafe;
  }

}