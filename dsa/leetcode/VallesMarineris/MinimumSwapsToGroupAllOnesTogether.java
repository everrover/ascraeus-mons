package dsa.leetcode.VallesMarineris;

public class MinimumSwapsToGroupAllOnesTogether {
  /**
   * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/description/?envType=weekly-question&envId=2025-03-01
   *
   * The problem is to group all '1's together in a binary array. Calculate the total number of '1's to form a sliding window. Use the sliding window technique to find the subarray with maximum '1's, which will require minimum swaps to gather all '1's.
   *
   * TC: O(n) SC: O(1)
   * #array #sliding-window #medium
   */

  public int minSwaps(int[] data) {
    int ones = 0;
    // Count the total number of ones in the array
    for(int num: data) ones += num;
    int idx = 0, windowones = 0;
    // Initialize the first window with number of ones required
    for(; idx < data.length && idx < ones; idx++){
      windowones += data[idx];
    }
    int res = ones - windowones;
    // Slide the window across the array to find the minimum number of swaps
    for(; idx < data.length; idx++){
      windowones -= data[idx - ones]; // Remove the element going out of the window
      windowones += data[idx]; // Add the new element entering the window
      res = Math.min(res, ones - windowones); // Update the result with minimum swaps
    }
    return res;
  }
}