package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesII {
  /**
   * https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/description/?envType=daily-question&envId=2025-05-29
   *
   * Compute an array even where even[u] is the number of nodes at an even distance from node u, for every u of the first tree.
   * Compute an array odd where odd[u] is the number of nodes at an odd distance from node u, for every u of the second tree.
   * answer[i] = even[i] + max(odd[1], odd[2], ..., odd[m - 1])
   *
   * TC: O(n + m) SC: O(n + m)
   * #tree #bfs #dfs #hard
   */

  public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
    Map<Integer, List<Integer>> g1 = new HashMap<>();
    Map<Integer, List<Integer>> g2 = new HashMap<>();
    Set<Integer> oddgrp1 = new HashSet<>();
    Set<Integer> evegrp1 = new HashSet<>();
    Set<Integer> oddgrp2 = new HashSet<>();
    Set<Integer> evegrp2 = new HashSet<>();

    buildGraph(edges1, g1);
    buildGraph(edges2, g2);

    bfs(g1, oddgrp1, evegrp1);
    bfs(g2, oddgrp2, evegrp2);

    int[] res = new int[g1.size()];
    int maxgrpintwo = Math.max(oddgrp2.size(), evegrp2.size());

    for (int i = 0; i < g1.size(); i++) {
      if (oddgrp1.contains(i))
        res[i] = oddgrp1.size() + maxgrpintwo;
      else
        res[i] = evegrp1.size() + maxgrpintwo;
    }
    return res;
  }

  private void bfs(final Map<Integer, List<Integer>> g, Set<Integer> oddgrp, Set<Integer> evegrp) {
    boolean[] v = new boolean[g.size()];
    Queue<Integer> q = new LinkedList<>();
    q.offer(0);
    boolean iseve = true;
    // BFS to calculate distances...
  }

  private void buildGraph(int[][] edges, Map<Integer, List<Integer>> graph) {
    // Build graph from edges...
  }
}