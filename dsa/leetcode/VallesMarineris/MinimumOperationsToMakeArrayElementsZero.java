package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MinimumOperationsToMakeArrayElementsZero {

  /**
   * https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero/description/
   *
   * For a number x, the number of "/4" operations to change it to 0 is floor(log4(x)) + 1.
   * Always pair the 2 numbers with the maximum "/4" operations needed.
   *
   * TC: O(n * log(max_r))  SC: O(1)
   * #array #math #bit-manipulation #hard
   */

  public long minOperations(int[][] queries) {
    long res = 0;
    for(int[] q : queries) {
      long sum = 0, cnt = 0;
      for(long fours = 1; fours <= q[1]; fours *= 4) {
        long p1 = Math.max(q[0], fours), p2 = Math.min(fours * 4 - 1, q[1]);
        sum += Math.max(0L, ++cnt * (p2 - p1 + 1));
      }
      res += Math.ceil(sum / 2.0);
    }
    return res;
  }
}