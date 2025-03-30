package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MinimumOperationsToMakeArrayElementsZero {

  /**
   * https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero/description/
   *
   * For a number x, the number of "/4" operations to change it to 0 is floor(log4(x)) + 1.
   * THIS basically counts the number of 4's in the binary representation of x.
   * We mimic it using a group of numbers in range [4^i, 4^(i+1) - 1].
   * 
   * The counted numbers are tracked using `sum` and `cnt` is number of `/4` operations needed per number
   * in the range.
   * 
   * For accomodating the odd counted groups, we `/2` the sum just once. and no=t at #2
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
        sum += Math.max(0L, ++cnt * (p2 - p1 + 1)); // #2
      }
      res += Math.ceil(sum / 2.0);
    }
    return res;
  }
}