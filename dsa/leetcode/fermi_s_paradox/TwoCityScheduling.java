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
   * The greedy approach: Send all to city A. Then we need to find the top-N to send to city B.
   * Those who are expensive(min(cost-a - cost-b)) to send to city A, send them to city B.
   * For that, sort the costs array based on the difference between cost-a and cost-b. And 
   * select smallest N and deduct their cost-a from the total cost and add their cost-b.
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

  // Approach 2: Greedy
  //public int twoCitySchedCost(int[][] costs) {
  //   int N=costs.length/2;
  //   int res = 0;
  //   for(int []cost: costs) res += cost[0];
  //   Arrays.sort(costs, (a,b)->(a[1]-a[0])-(b[1]-b[0])); // Maximize refunds by sending to city B
  //   for(int i=0; i<N; i++){
  //     // System.out.println(":"+costs[i][0]);
  //     // System.out.println(":"+costs[i][1]);
  //     res += (costs[i][1]-costs[i][0]);
  //   }
  //   return res;
  // }
}