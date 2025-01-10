package dsa.leetcode.mithrim_montez;

import java.util.*;

/**
 * https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
 *
 * For each node in the first tree, calculate the number of target nodes within distance k.
 * For each node in the second tree, calculate the number of nodes within distance (k - 1).
 * The result is determined by connecting nodes from the first tree with nodes from the second tree.
 *
 * TC: O(n * m) SC: O(n + m)
 * #tree #bfs #medium
 */

public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {

  public int calculateTargetNodes(Map<Integer, List<Integer>> g, int start, int k) {
    int cnt = 0;
    boolean[] v = new boolean[g.size()];
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);

    while (!q.isEmpty()) {
      int curr = q.poll();
      v[curr] = true;
      cnt++;
      for (int next : g.get(curr)) {
        if (v[next]) continue;
        q.offer(next);
      }
    }
    return cnt;
  }
}