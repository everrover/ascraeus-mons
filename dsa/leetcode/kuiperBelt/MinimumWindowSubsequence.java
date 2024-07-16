package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

/**
 * URL: https://leetcode.com/problems/minimum-window-subsequence/
 *
 * for s1[i] == s2[j], we can either skip s1[i] or match s1[i] with s2[j], otherwise we can only skip s1[i]
 *
 * skip s1[i] => dfs(i+1,j)
 * match s1[i] with s2[j] => dfs(i+1,j+1)
 *
 * The shortest window is the one where we have matched all characters of s2, so when j == s2.length, we return
 * i(index where all characters of s2 are in subseq)
 * If i == s1.length, we return Integer.MAX_VALUE as we have reached the end of s1 and s2 is not matched.
 *
 * If the matched window is smaller than the current minimum window, we update the minimum window, with start index and length.
 *
 * We memoize the result of dfs(i,j) in dp[i][j] to avoid recomputation.
 *
 * TC: O(n*m) SC: O(n*m)
 *
 * #dynamic-programming #sliding-window #string #hard
 */

class MinimumWindowSubsequence {
  public String minWindow(String s1, String s2) {
    int [][]dp = new int[s1.length()][s2.length()];
    for(int []d: dp) Arrays.fill(d, -1);
    int []minLenAStart = new int[]{Integer.MAX_VALUE, -1};
    dfs(0,0,s1.toCharArray(),s2.toCharArray(),dp, minLenAStart);
    if(minLenAStart[1]==-1) return "";
    return s1.substring(minLenAStart[1],minLenAStart[1]+minLenAStart[0]);
  }

  private int dfs(int i, int j, char []s1, char []s2, int [][]dp, int []minLenAStart){
    if(j == s2.length) return i;
    else if(i==s1.length) return Integer.MAX_VALUE;
    int matchedIdx = Integer.MAX_VALUE;
    if(s1[i] == s2[j]){
      matchedIdx = dfs(i+1,j+1,s1,s2,dp,minLenAStart);
    }
    int skippedIdx = dfs(i+1,j,s1,s2,dp,minLenAStart);
    int res = Math.min(skippedIdx, matchedIdx);
    if(j == 0 && res != Integer.MAX_VALUE && (minLenAStart[1]==-1 || res-i <= minLenAStart[0])){
      minLenAStart[0] = res-i;
      minLenAStart[1] = i;
    }
    return dp[i][j]=res;
  }
}

/*
Recursive DFS with memoization to find minimum window substring length.
*/