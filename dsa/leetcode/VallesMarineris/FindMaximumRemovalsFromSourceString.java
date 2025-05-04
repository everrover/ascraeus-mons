package dsa.leetcode.VallesMarineris;

import java.util.*;

public class FindMaximumRemovalsFromSourceString {

  /**
   * https://leetcode.com/problems/find-maximum-removals-from-source-string/description/
   *
   * Use dynamic programming. At each index in targetIndices,
   * make the choice to remove or not remove the character.
   *
   * TC: O(n * m) SC: O(n * m)
   * #array #hash-table #two-pointers #string #dynamic-programming #medium
   */

  public int findMaximumRemovals(String source, String pattern, int[] targetIndices) {
    Set<Integer> set = new HashSet<>();
    char[] s = source.toCharArray();
    for(int t:targetIndices) set.add(t);
    // int []dp = new int[pattern.length()+1]; Arrays.fill(dp, -1); dp[pattern.length()] = 0;
    int [][]dp = new int[source.length()+1][pattern.length()+1];
    for(int []dd: dp) Arrays.fill(dd, -100001);
    int res = dfs(0, 0, dp, s, pattern.toCharArray(), set);
    return res;
  }

  private int dfs(int idx, int jdx, final int [][]dp, final char[] s, final char[] p, final Set<Integer> set){
    if(idx>=s.length) return jdx>=p.length?0:-100000;
    else if(dp[idx][jdx] != -100001) return dp[idx][jdx];

    int res = jdx>=p.length?0:-2;
    // take idx in ss
    if(jdx < p.length && s[idx] == p[jdx]) res = Math.max(dfs(idx+1, jdx+1, dp, s, p, set), res);
    // don't take idx in ss
    int r = dfs(idx+1, jdx, dp, s, p, set);
    if(r>=0) res = Math.max(r+(set.contains(idx)?1:0), res);
    
    return dp[idx][jdx] = res;
  }

}