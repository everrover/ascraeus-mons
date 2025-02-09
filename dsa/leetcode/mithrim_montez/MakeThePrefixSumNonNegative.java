package dsa.leetcode.mithrim_montez;

import java.util.PriorityQueue;
import java.util.Queue;

public class MakeThePrefixSumNonNegative {
  /**
   * https://leetcode.com/problems/make-the-prefix-sum-non-negative/description/?envType=weekly-question&envId=2025-02-08
   *
   * Loop over the array keeping the prefix sum. Whenever it goes negative, move the smallest number you have seen to the end.
   *
   * TC: O(n log n) SC: O(n)
   * #array #greedy #heap #medium
   */

  public int makePrefSumNonNegative(int[] nums) {
    long p = 0;
    int moves = 0;
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (a - b));
    
    for (int i = 0; i < nums.length; i++) {
      p += nums[i];
      if (nums[i] < 0) pq.offer(nums[i]);
      if (p < 0) {
        p -= pq.poll(); // remove the smallest negative number, -5 over -3
        moves++;
      }
    }
    return moves;
  }
}