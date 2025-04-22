package dsa.leetcode.JupitersGreatStorm;

public class CountHiddenSequences {

  /**
   * https://leetcode.com/problems/count-the-hidden-sequences/description/?envType=daily-question&envId=2025-04-21
   *
   * To solve this problem, we use the prefix sum concept, maintaining a running total to derive the hidden sequence values from the difference array.
   * Calculate the minimum and maximum of this sequence and then determine how many starting elements could fit the requirement.
   * Specifically, the count is given by the possible starting point variance fitting within the valid range which is (upper - lower + 1) - (max - min).
   *
   * TC: O(n) SC: O(1)
   * #array #prefix-sum #medium
   */

  public int numberOfArrays(int[] differences, int lower, int upper) {
    long min = 0, max = 0, current = 0;
    for (int diff : differences) {
      current += diff;
      if (current < min) min = current;
      if (current > max) max = current;
    }
    return (int) Math.max(0, (upper - lower + 1) - (max - min));
  }
}