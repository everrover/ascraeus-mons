package dsa.leetcode.kuiperBelt;

// imports here

public class BestTimeToBuyAndSellStockIV {
  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
   *
   * Dynamic programming approach to maximize profit by completing at most k transactions.
   * The state dp[i][j] represents the maximum profit using at most i transactions up to day j (0-based).
   * Transitions are based on whether to execute the i-th transaction on day j or another day.
   *
   * TC: O(n*k) SC: O(n*k)
   * #array #dynamic-programming #hard
   */
  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (n == 0) return 0;
    if (k > n / 2) { // Same as unlimited transactions problem
        int maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    int[][] dp = new int[k + 1][n];
    for (int i = 1; i <= k; i++) {
        int maxDiff = -prices[0];
        for (int j = 1; j < n; j++) {
            dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
            maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
        }
    }
    return dp[k][n - 1];
  }
}