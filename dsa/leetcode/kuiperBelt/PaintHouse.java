package dsa.leetcode.kuiperBelt;

// Solution class
public class PaintHouse {
  /**
   * https://leetcode.com/problems/paint-house/
   *
   * We need to find the minimum cost to paint all the houses such that no two adjacent
   * houses have the same color. We can solve this problem using dynamic programming.
   *
   * TC: O(n) SC: O(1)
   * #array #dynamic-programming #medium
   */
  public int minCost(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;

    // Initialize the previous house costs
    int prevRed = costs[0][0];
    int prevBlue = costs[0][1];
    int prevGreen = costs[0][2];

    // Iterate over each house starting from the second one
    for (int i = 1; i < costs.length; i++) {
      int currRed = costs[i][0] + Math.min(prevBlue, prevGreen);
      int currBlue = costs[i][1] + Math.min(prevRed, prevGreen);
      int currGreen = costs[i][2] + Math.min(prevRed, prevBlue);

      // Update previous house costs
      prevRed = currRed;
      prevBlue = currBlue;
      prevGreen = currGreen;
    }

    return Math.min(prevRed, Math.min(prevBlue, prevGreen));
  }
}