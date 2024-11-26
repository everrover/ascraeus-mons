package dsa.leetcode.jovianMoonOrbit;

import java.util.Arrays;

public class MovingStonesUntilConsecutiveII {

  /**
   * https://leetcode.com/problems/moving-stones-until-consecutive-ii/
   *
   * To solve the problem, sort the array of stones and use a two-pointer technique to determine the minimum and maximum moves.
   * - Minimum Moves: Use two pointers to find the longest sequence of consecutive stones. Calculate the minimum moves needed to achieve this.
   * - Maximum Moves: Maximum moves would be to move the stones to the ends, using the formula for gaps excluding the outermost stones.
   *
   * TC: O(n log n) - Due to sorting. SC: O(1) - No additional space required except for variables.
   * #array #two-pointers #sorting #medium
   */

  public int[] numMovesStonesII(int[] stones) {
    final int N = stones.length;
    Arrays.sort(stones); // Sort the stones initially
    int reslow = N;
    for(int i=0, j=0; j<stones.length; j++){
      while(stones[j] - stones[i] >= N) i++; // Maintain window of size N
      if(j-i+1 == N-1 && stones[j] - stones[i] == N - 2)
        reslow = Math.min(reslow, 2); // Special case
      else
        reslow = Math.min(reslow, N - (j - i + 1)); // Minimize total moves
    }
    int reshigh = Math.max((stones[N-1] - stones[1]) - N + 2, (stones[N-2] - stones[0]) - N + 2); // Calculate max moves
    return new int[]{reslow, reshigh};
  }

}