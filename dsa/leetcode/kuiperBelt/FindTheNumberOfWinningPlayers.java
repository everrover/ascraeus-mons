package dsa.leetcode.kuiperBelt;

// Required imports if any

public class FindTheNumberOfWinningPlayers {

  /**
   * https://leetcode.com/problems/find-the-number-of-winning-players/
   *
   * Count the number of balls of each color for each player using an array.
   * For each player, check if they picked more than i balls of any color.
   *
   * TC: O(n * k) SC: O(n * k)
   * #hashmap #array #greedy #easy
   */

  public int winningPlayerCount(int n, int[][] pick) {
    int res = 0;
    for (int i = 0; i < n; i++) {
      int[] bs = new int[11]; // Array to count the balls of each color
      for (int[] p : pick) {
        if (p[0] == i) bs[p[1]]++; // Increment the count for the color
      }
      for (int b : bs) if (b > i) {res++; break;} // Check if player wins
    }
    return res;
  }
}