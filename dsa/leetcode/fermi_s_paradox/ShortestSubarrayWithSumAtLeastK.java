package dsa.leetcode.fermi_s_paradox;

import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {

  /**
   * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
   *
   * The goal is to find the shortest subarray with a sum at least k.
   * We use a prefix sum to efficiently compute subarray sums and a deque for maintaining
   *   indices of potential starting points of such subarrays, leveraging the monotonic queue pattern.
   *
   * TC: O(n), where n is the length of the array.
   * SC: O(n), to store the prefix sums and deque.
   * #array #queue #prefix-sum #monotonic-queue #hard
   */

  public int shortestSubarray(int[] nums, long k) {
    long[] prefix = new long[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }

    Deque<Integer> dq = new ArrayDeque<>();
    int res = Integer.MAX_VALUE;

    for (int i = 0; i <= nums.length; ++i) {
      while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
        res = Math.min(res, i - dq.pollFirst());
      }
      while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
        dq.pollLast();
      }
      dq.addLast(i);
    }

    if (res == Integer.MAX_VALUE) return -1;
    return res;
  }

}