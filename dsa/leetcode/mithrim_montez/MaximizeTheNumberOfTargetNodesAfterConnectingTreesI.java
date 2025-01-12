package dsa.leetcode.mithrim_montez;

import java.util.*;

/**
 * https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
 *
 * For each node in the first tree, calculate the number of target nodes within distance k.
 * For second tree, find a node with max number of nodes within distance (k - 1).
 * The result is determined by connecting nodes from the first tree to the chosen node from the second tree.
 * 
 * Essentially this was exactly what was being asked to be implemented.
 *
 * TC: O(n * m) SC: O(n + m)
 * #tree #bfs #medium
 */

public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {

  public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
    Map<Integer, List<Integer>> g1 = new HashMap<>();
    Map<Integer, List<Integer>> g2 = new HashMap<>();
    
    int max = -1;
    for(int []edge: edges1){
      g1.putIfAbsent(edge[0], new LinkedList<>());
      g1.putIfAbsent(edge[1], new LinkedList<>());
      g1.get(edge[0]).add(edge[1]);
      g1.get(edge[1]).add(edge[0]);
      max = Math.max(max, Math.max(edge[0], edge[1]));
    }
    for(int []edge: edges2){
      g2.putIfAbsent(edge[0], new LinkedList<>());
      g2.putIfAbsent(edge[1], new LinkedList<>());
      g2.get(edge[0]).add(edge[1]);
      g2.get(edge[1]).add(edge[0]);
    }

    int []res = new int[max+1];
    int g2max = 0, i=0;
    for(Map.Entry<Integer, List<Integer>> e: g2.entrySet()) g2max = Math.max(g2max, bfs(e.getKey(), k-1, g2));
    for(Map.Entry<Integer, List<Integer>> e: g1.entrySet()){
      res[i++] = bfs(e.getKey(), k, g1) + g2max;
    }
    return res;
  }

  private int bfs(final int idx, final int k, final Map<Integer, List<Integer>> g){
    if(k == 0) return 1;
    boolean []v = new boolean[g.size()];
    Queue<Integer> q = new LinkedList<>();
    q.offer(idx);
    int cnt=0, kdx=0;
    while(!q.isEmpty() && kdx <= k){
      int sz = q.size();
      kdx++;
      while(sz-->0){
        int curr = q.poll();
        v[curr] = true;
        cnt++;
        for(int next: g.get(curr)){
          if(v[next]) continue;
          q.offer(next);
        }
      }
    }
    return cnt;
  }
}