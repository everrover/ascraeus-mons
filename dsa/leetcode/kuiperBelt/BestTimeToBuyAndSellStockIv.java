package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIv {
  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
   *
   * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
   * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
   *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
   * dp[0, j] = 0; 0 transactions makes 0 profit
   * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
   * 
   * TC: O(n*k) SC: O(n*k)
   * #array #dynamic-programming #hard
   */
  
  public int maxProfit(int k, int day, int prices[], int [][]dp){
    if(dp[k][day] != -1) return dp[k][day];
    int max = 0;
    // System.out.println(k+":"+day);
    for(int j=day; j<prices.length; j++){
      for(int i=j+1; i<prices.length; i++){
        if(prices[i]>prices[j]) {
          int price = prices[i]-prices[j]+(dp[k-1][i] != -1? dp[k-1][i]: maxProfit(k-1, i, prices, dp));
          // System.out.println(day+":"+i+":"+price+":"+max);
          max = Integer.max(max, price);
        }
      }
    }
    // System.out.println(max);
    dp[k][day] = max;
    return max;
  }

  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (n <= 1)
      return 0;

    //if k >= n/2, then you can make maximum number of transactions.
    if (k >= n/2) {
      int maxPro = 0;
      for (int i = 1; i < n; i++) {
        if (prices[i] > prices[i-1])
          maxPro += prices[i] - prices[i-1];
      }
      return maxPro;
    }
    int dp[][] = new int[k+1][prices.length];
    for(int []arr: dp){
      Arrays.fill(arr, -1);
      // arr[0] = 0;
    }
    Arrays.fill(dp[0], 0);
    return maxProfit(k, 0, prices, dp);
  }
}