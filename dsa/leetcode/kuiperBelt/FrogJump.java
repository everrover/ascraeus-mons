package dsa.leetcode.kuiperBelt;

import java.util.HashMap;
import java.util.Map;

public class FrogJump {

  /**
   * https://leetcode.com/problems/frog-jump/
   *
   * A frog can cross the river using a Depth First Search (DFS) approach with memoization. 
   * The frog keeps track of its last jump size, and its next jump must be k-1, k, or k+1 units. 
   * The DFS method recursively checks if it can reach the last stone from the current stone by attempting all valid jump sizes. 
   * Memoization is used to store results of subproblems to avoid redundant calculations.
   * 
   * TC: O(n^2) | SC: O(n^2)
   * #array #dynamic-programming #hard
   */
  
  public boolean canCross(int[] stones) {
    Map<Integer, Boolean>[] dp = new Map[stones.length];
    return dfs(0, 0, stones, dp);
  }

  private boolean dfs(int i, int k, int[] stones, Map<Integer, Boolean>[] dp) {
    if (i == stones.length - 1) return true;
    if (dp[i] != null && dp[i].containsKey(k)) return dp[i].get(k);
    boolean res = false;
    if (dp[i] == null) dp[i] = new HashMap<>();
    dp[i].put(k, false);
    for (int j = i + 1; j < stones.length; j++) {
      if (stones[j] == stones[i] + k) res = res || dfs(j, k, stones, dp);
      else if (stones[j] == stones[i] + k + 1) res = res || dfs(j, k + 1, stones, dp);
      else if (stones[j] == stones[i] + k - 1) res = res || dfs(j, k - 1, stones, dp);
      if (res == true) break;
    }
    dp[i].put(k, res);
    return res;
  }
}