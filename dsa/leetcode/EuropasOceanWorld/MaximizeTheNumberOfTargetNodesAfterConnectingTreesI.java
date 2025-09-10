package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {
  
  /**
   * https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
   * 
   * Calculate the maximum number of nodes target to each node in the first tree by connecting them optimally to the second tree. 
   * Use BFS to find nodes within the distance `k` in the first tree and `k-1` in the second tree. 
   * Iterate through possible connections to maximize the count.
   * 
   * TC: O(n * m) SC: O(n + m)
   * #tree #bfs #dfs #medium
   */

  public int[] maximizeTargetNodes(Map<Integer, List<Integer>> g1, Map<Integer, List<Integer>> g2, int k) {
    int g2max = 0, i = 0;
    int[] res = new int[g1.size()];
    for(Map.Entry<Integer, List<Integer>> e: g2.entrySet()) 
      g2max = Math.max(g2max, bfs(e.getKey(), k-1, g2));
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
    int cnt = 0, kdx = 0;
    while(!q.isEmpty() && kdx <= k){
      int sz = q.size();
      kdx++;
      while(sz-- > 0){
        int curr = q.poll();
        if (v[curr]) continue;  // Avoid cycles
        v[curr] = true;
        cnt++;
        for(int neighbor : g.getOrDefault(curr, new ArrayList<>())){
          if(!v[neighbor]) q.offer(neighbor);
        }
      }
    }
    return cnt;
  }
}