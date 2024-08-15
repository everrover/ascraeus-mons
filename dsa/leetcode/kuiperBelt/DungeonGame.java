package dsa.leetcode.kuiperBelt;

class DungeonGame {
  /**
   * https://leetcode.com/problems/dungeon-game/
   *
   * To calculate the knight's minimum initial health needed to rescue the princess, we use dynamic programming.
   * Starting from the bottom-right corner (where the princess is), we move leftward and upward, calculating the minimum health required to reach each cell.
   * We ensure the knight's health never drops to zero or below.
   * 
   * TC: O(m*n) SC: O(m*n)
   * #array #dynamic-programming #matrix #hard
   */
  public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon.length == 0) {
      return 1;
    }
    final int m = dungeon.length, n = dungeon[0].length;
    int[][] dp = new int[m][n];
    dp[m - 1][n - 1] = Math.max(1, -dungeon[m - 1][n - 1] + 1);
    // Fill last row
    for (int i = m - 2; i >= 0; i--) {
      dp[i][n - 1] = Math.max(1, -dungeon[i][n - 1] + dp[i + 1][n - 1]);
    }
    // Fill last column
    for (int i = n - 2; i >= 0; i--) {
      dp[m - 1][i] = Math.max(1, -dungeon[m - 1][i] + dp[m - 1][i + 1]);
    }
    // Fill the rest of the grid
    for (int i = m - 2; i >= 0; i--) {
      for (int j = n - 2; j >= 0; j--) {
        dp[i][j] = Math.max(-dungeon[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]), 1);
      }
    }
    return dp[0][0];
  }
}