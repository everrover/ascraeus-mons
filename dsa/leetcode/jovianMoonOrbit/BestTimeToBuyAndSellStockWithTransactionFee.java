package dsa.leetcode.jovianMoonOrbit;

public class BestTimeToBuyAndSellStockWithTransactionFee {

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
   *
   * We use dynamic programming to solve this problem. We maintain two arrays buy and sell such that:
   * - buy[i]: Represents the maximum profit achievable by buying on or before the ith day.
   * - sell[i]: Represents the maximum profit achievable by selling on or before the ith day.
   * The transition equations are as follows:
   * - buy[i] = max(buy[i-1], sell[i-1] - prices[i] - fee)
   * - sell[i] = max(sell[i-1], buy[i-1] + prices[i])
   * 
   * TC: O(n) SC: O(n)
   * #array #dp #greedy #medium
   */

  public int maxProfit(int[] prices, int fee) {
    if (prices.length <= 1) return 0;
    int days = prices.length, buy[] = new int[days], sell[] = new int[days];
    buy[0] = -prices[0] - fee;
    for (int i = 1; i < prices.length; i++) {
      buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee);
      sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
    }
    return sell[prices.length - 1];
  }
}