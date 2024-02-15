package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class NumberOfMusicPlaylists {
  /**
   * https://leetcode.com/problems/number-of-music-playlists/
   * Creating playlists considering constraints of playing each song at least once and K other songs between replays.
   * Uses DFS with memoization to calculate possible combinations.
   * 
   * TC: O(n * goal) SC: O(n * goal)
   * #dynamic-programming #combinatorics #hard
   */
  private static long MOD = (long)(1e9+7);
  private long [][]dp;
  private int k, count;
  public int numMusicPlaylists(int n, int goal, int k) {
    this.dp = new long[n+1][goal+1];
    for(int i=n; i>=0; i--) Arrays.fill(dp[i], -1);
    this.k = k; this.count = n;
    long res = dfs(n, goal)%MOD;
    return (int)res;
  }

  private long dfs(int n, int goal){
    if(n == 0 && goal == 0) return dp[0][0] = 1;
    else if(n == 0 || goal == 0) return dp[n][goal] = 0;
    else if(dp[n][goal] != -1) return dp[n][goal];
    long ways = 0;
    ways += ((count-n+1)*(dfs(n-1, goal-1))%MOD); // Using different elements
    ways %= MOD;
    if(n > k){
      ways += ((n-k)*dfs(n, goal-1))%MOD; // Can reuse elements
      ways %= MOD;
    }
    return dp[n][goal] = ways;
  }
}
