package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class FindClosestNodeToGivenTwoNodes {

  /**
   * https://leetcode.com/problems/find-closest-node-to-given-two-nodes/description/?envType=daily-question&envId=2025-05-30
   *
   * Use BFS to find the shortest distance from both node1 and node2 to all nodes in the graph. Then iterate over all nodes,
   * and find the node with the minimum maximum distance.
   *
   * TC: O(n) SC: O(n)
   * #graph #breadth-first-search #medium
   */

  public int findClosestNode(int[] edges, int node1, int node2) {
    int n = edges.length;
    int[] v1 = new int[n];
    int[] v2 = new int[n];
    Arrays.fill(v1, -1);
    Arrays.fill(v2, -1);

    // BFS from node1
    Queue<Integer> q1 = new LinkedList<>();
    q1.offer(node1);
    int dist = 0;
    while (!q1.isEmpty()) {
      int sz1 = q1.size();
      while (sz1-- > 0) {
        int c = q1.poll();
        if (v1[c] == -1) {
          v1[c] = dist;
          if (edges[c] != -1) q1.offer(edges[c]);
        }
      }
      dist++;
    }

    // BFS from node2
    Queue<Integer> q2 = new LinkedList<>();
    q2.offer(node2);
    dist = 0;
    while (!q2.isEmpty()) {
      int sz2 = q2.size();
      while (sz2-- > 0) {
        int c = q2.poll();
        if (v2[c] == -1) {
          v2[c] = dist;
          if (edges[c] != -1) q2.offer(edges[c]);
        }
      }
      dist++;
    }

    // Find the closest node
    int res = Integer.MAX_VALUE, resi = n;
    for (int i = 0; i < n; i++) {
      if (v1[i] != -1 && v2[i] != -1) {
        int maxDist = Math.max(v1[i], v2[i]);
        if (maxDist < res) {
          res = maxDist;
          resi = i;
        }
      }
    }
    return resi == n ? -1 : resi;
  }

}