package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class TwoCityScheduling {

  /**
   * https://leetcode.com/problems/two-city-scheduling/
   * 
   * Approach uses dynamic programming to find the minimum cost to assign n people to each city. 
   * The DFS function helps compute the minimal cost recursively by checking each option 
   * of sending a person to either city and accumulating costs accordingly.
   * 
   * TC: O(n^2) SC: O(n^2)
   * #array #greedy #dynamic-programming #medium
   */

  private int dfs(int a, int b, int idx, int [][]cs, int [][][]dp){
    if(idx == 2*N) return 0;
    else if(dp[idx][a][b] != -1) return dp[idx][a][b];
    int res = Integer.MAX_VALUE;
    if(a < N){
      res = Math.min(
        res,
        cs[idx][0]+dfs(a+1, b, idx+1, cs, dp)
      );
    }
    if(b < N){
      res = Math.min(
        res,
        cs[idx][1]+dfs(a, b+1, idx+1, cs, dp)
      );
    }
    return dp[idx][a][b]=res;
  }

  private int N = 0;

  public int twoCitySchedCost(int[][] costs) {
    N=costs.length/2;
    int [][][]dp = new int[2*N][N+1][N+1];
    for(int [][]dd: dp) for(int []d: dd) Arrays.fill(d, -1);
    return dfs(0,0,0,costs,dp);
  }
}