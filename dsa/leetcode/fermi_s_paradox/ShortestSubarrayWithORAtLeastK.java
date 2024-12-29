package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class ShortestSubarrayWithORAtLeastK {

  /**
   * https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-i/
   *
   * Iterate over all subarrays and compute their OR until it is greater than or equal to k.
   * Track the minimum length of such subarrays.
   * If no such subarray exists, return -1.
   * 
   * TC: O(n^2) SC: O(1)
   * #array #bit-manipulation #sliding-window #easy
   */

  class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
      int ans = 1000;
      for (int i = 0; i < nums.length; i++) {
        int curr = 0;
        for (int j = i; j < nums.length; j++) {
          curr |= nums[j];
          if (curr >= k) {
            ans = Math.min(ans, j - i + 1);
            break;
          }
        }
      }
      return ans == 1000 ? -1 : ans;
    }
  }
}