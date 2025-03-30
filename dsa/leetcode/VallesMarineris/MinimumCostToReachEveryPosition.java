package dsa.leetcode.VallesMarineris;

public class MinimumCostToReachEveryPosition {

  /**
   * https://leetcode.com/problems/minimum-cost-to-reach-every-position/description/
   *
   * Use a greedy approach to keep track of minimum cost encountered from the end of the array.
   * Update the result array with this minimum cost to allow free swaps later.
   *
   * TC: O(n) SC: O(1)
   * #greedy #array #minimum-cost #easy
   */

  public int[] minCosts(int[] cost) {
    int[] res = new int[cost.length];
    int idx = cost.length - 1;
    int mini = cost[cost.length - 1];

    while (idx >= 0) {
      mini = Math.min(mini, cost[idx]); // update minimum cost encountered
      res[idx] = mini;  // set the minimum cost to the current position
      idx--;
    }

    return res;
  }
}