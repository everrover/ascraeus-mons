package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountTheNumberOfFairPairs {
  /**
   * https://leetcode.com/problems/count-the-number-of-fair-pairs/description/?envType=daily-question&envId=2025-04-19
   * 
   * For each number in the sorted array, perform binary search to find the smallest and largest numbers
   * that can form a fair pair with the current number, given the constraints.
   * 
   * TC: O(n log n) due to sorting and binary search; SC: O(1)
   * #array #two-pointers #binary-search #sorting #medium
   */

  public int countFairPairs(int[] nums, int lower, int upper) {
    Arrays.sort(nums); // Sort the array first
    int count = 0;
    int till = nums.length;
    for (int i = 0; i < till; i++) {
      int l = i + 1, r = till - 1;
      int start = i, end = i;
      // Binary search for the lower bound
      while (l <= r) {
        int m = (l + r) / 2;
        if (nums[m] < lower - nums[i]) {
          l = m + 1;
          start = m;
        } else {
          r = m - 1;
        }
      }
      l = i + 1; 
      r = till - 1;
      // Binary search for the upper bound
      while (l <= r) {
        int m = (l + r) / 2;
        if (nums[m] > upper - nums[i]) {
          r = m - 1;
          end = m;
        } else {
          l = m + 1;
        }
      }
      count += end - start;
    }
    return count;
  }
}