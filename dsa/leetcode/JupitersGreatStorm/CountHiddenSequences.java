package dsa.leetcode.JupitersGreatStorm;

public class CountHiddenSequences {

  /**
   * https://leetcode.com/problems/count-the-hidden-sequences/description/?envType=daily-question&envId=2025-04-21
   *
   * TC: O(n) SC: O(1)
   * #array #prefix-sum #medium
   * 3, -4, 5, 1, -2
   * ... all ❌s
   * -4 -1 -5 0 1 -1 ❌
   * -3 0 -4 1 2 0 ✅
   * -2 1 -3 2 3 1 ✅
   * -1 2 -2 3 4 2 ✅
   *  0 3 -1 4 5 3 ✅
   * 1 4  0 5 6 4 ❌
   * 2 5  1 6 7 5 ❌
   * ... all ❌s
   */
  public int numberOfArrays(int[] differences, int lower, int upper) {
    long min = 0, max = 0, current = 0;

    for (int diff : differences) {
      current += diff;
      min = Math.min(min, current);
      max = Math.max(max, current);
    }

    long range = max - min;
    long totalPossible = (upper - lower + 1) - range;
    return (int) Math.max(0, totalPossible);
  }
}