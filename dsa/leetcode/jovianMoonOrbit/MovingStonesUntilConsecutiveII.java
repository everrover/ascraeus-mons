package dsa.leetcode.jovianMoonOrbit;

import java.util.Arrays;

public class MovingStonesUntilConsecutiveII {

  /**
   * https://leetcode.com/problems/moving-stones-until-consecutive-ii/
   *
   * Got stuck on `reslow` part...
   * 
   * The main idea is that end-result array size is always N, no matter the play.
   * 
   * The idea for `reshigh` is to move the end-stones to the ends of array, each time choice presents itself.
   * `1 4 5 10` -> `1 2 4 5` or -> `4 5 9 10`, second choice is better so that's chosen... and we keep making similar choices
   * And this is computed by finding the gaps between the stones, excluding one of the outermost stones.
   * 
   * For `reslow`, since resulting size is always N, we can find the sliding window of size N with the most stones. And
   * remaining gaps are filled by stones not in the window. Since, result is never greater than N, we find min starting from N.
   * 
   * 
   * TC: O(n log n) - Due to sorting. SC: O(1) - No additional space required except for variables.
   * #array #two-pointers #sorting #medium
   */

  public int[] numMovesStonesII(int[] stones) {
    final int N = stones.length;
    Arrays.sort(stones); // Sort the stones initially
    int reslow = N;
    for(int i=0, j=0; j<stones.length; j++){
      while(stones[j] - stones[i] >= N) i++; // Maintain window of size >= N
      // when count(stones) in window is N-1 and gap is N-2, we have one contiguous
      // group of stones and an odd one, we need to use 2 moves, not 1. We can't move 
      // the odd stone in the middle, so we need to move the stone at one end one 
      // step ahead of the group to make space for the stone at the other end.
      // N=7, 1 2 4 5 6 7 ; 10 -> 1 2 3 4 5 6 7 // 1 move
      // N=7, 1 2 3 4 5 6 ; 10 -> 2 3 4 5 6 7 9 ; 10 -> 2 3 4 
      if(j-i+1 == N-1 && stones[j] - stones[i] == N - 2)
        reslow = Math.min(reslow, 2);
      else
        reslow = Math.min(reslow, N - (j - i + 1)); // Minimize total moves
    }
    int reshigh = Math.max((stones[N-1] - stones[1]) - N + 2, (stones[N-2] - stones[0]) - N + 2); // Calculate max moves
    return new int[]{reslow, reshigh};
  }

}