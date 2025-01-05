package dsa.leetcode.fermi_s_paradox;

public class MaximumCoinsFromKConsecutiveBags {
  /**
   * https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags/description/
   *
   * To obtain the maximum coins from k consecutive bags, consider optimal starting positions for collecting k bags, such as either starting from li or adjusting for ri - k + 1. Calculate the total coins available within these segments while checking constraints to maintain the maximum result.
   *
   * TC: O(n) SC: O(1)
   * #sliding-window #prefix-sum #medium
   */

  public int maxCoins(int[][] coins, int k) {
    int res = 0;
    long currscore = 0;
    int l = 0;
    for (int r = 0; r < coins.length; r++) {
      currscore += 1L * coins[r][2] * (coins[r][1] - coins[r][0] + 1);
      while (l <= r && coins[r][1] - coins[l][1] + 1 > k) {
        currscore -= 1L * coins[l][2] * (coins[l][1] - coins[l][0] + 1);
        l++;
      }
      int tillk = coins[r][1] - k + 1 - coins[l][0];
      long part = 1L * Math.max(0, tillk) * coins[l][2];
      res = Math.max(res, currscore - part);
    }
    return res;
  }
}