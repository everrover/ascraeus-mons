package dsa.leetcode.mithrim_montez;

public class LongestStrictlyIncreasingOrDecreasingSubarray {

  /**
   * https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/description/?envType=daily-question&envId=2025-02-03
   *
   * The solution involves iterating through the array while keeping track of the longest strictly
   * increasing or decreasing subarray by comparing the current and previous elements. Increment the
   * count for a strict sequence, reset if a sequence violation occurs, and keep track of the maximum
   * sequence length encountered.
   *
   * TC: O(n) SC: O(1)
   * #array #two-pointers #easy
   */
  
  public int longestSubarray(int[] nums) {
    int res = 0;
    int prev = 1000; // Use as a flag for uninitialized state
    int cnt = 0;
    int idx = 0;
    prev = -1;
    cnt = 0;
    
    while (idx < nums.length) {
      res = Math.max(res, cnt);
      prev = 1000;
      cnt = 0;

      // Check increasing
      while (idx < nums.length && prev < nums[idx]) {
        cnt++;
        prev = nums[idx];
        idx++;
      }
      res = Math.max(res, cnt);

      if (prev == -1) idx++;
      
      // Skip checking decreasing or overlap of while loop
      idx++;
      cnt++;
      prev = nums[idx];

      if (prev == 1000) idx++;
    }

    return res;
  }
}