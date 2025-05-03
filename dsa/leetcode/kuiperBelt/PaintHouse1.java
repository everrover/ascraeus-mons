package dsa.leetcode.KuiperBelt;

// imports here

public class PaintHouse1 {
  
  /**
   * https://leetcode.com/problems/paint-house/
   *
   * We traverse each house, keeping track of costs with DP (dynamic programming) approach.
   * Calculate cost for painting house red, blue, or green based on previous house costs.
   * The minimum cost for each house will be the sum of the minimum cost from the previous house plus the current house's cost.
   * 
   * TC: O(n) SC: O(1)
   * #array #dp #paint-house #medium
   */
  
  class Solution {
    int dp[], costs[][];
    public int minCost(int[][] costs) {
      this.costs = costs;
      // dp = new int[costs.length];
      // dp[0] = Integer.min(Integer.min(costs[0][0], costs[0][1]), costs[0][2]);
      return util(0, 0, 0, 0);
    }
    private int util(int idx, int first, int second, int third){
      int ret = Integer.MAX_VALUE;
      if(idx == costs.length-1) {
        ret = Integer.min(Integer.min(second, third)+costs[idx][0], ret);
        ret = Integer.min(Integer.min(first, third)+costs[idx][1], ret);
        ret = Integer.min(Integer.min(first, second)+costs[idx][2], ret);
        return ret;
      }
      return util(idx+1, Integer.min(second, third)+costs[idx][0], Integer.min(first, third)+costs[idx][1], Integer.min(first, second)+costs[idx][2]);
    }
  }
}