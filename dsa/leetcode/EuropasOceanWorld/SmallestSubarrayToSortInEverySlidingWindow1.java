package dsa.leetcode.EuropasOceanWorld;

public class SmallestSubarrayToSortInEverySlidingWindow {
  /**
   * https://leetcode.com/problems/smallest-subarray-to-sort-in-every-sliding-window/
   * 
   * For each subarray of length k, identify the positions where order is violated.
   * Count the minimum number of elements to be sorted to fix the order. Return the lengths needed for each window.
   * 
   * TC: O(n * k) SC: O(n)
   * #array #two-pointers #stack #greedy #sorting #monotonic-stack #medium
   */

  public int[] minSubarraySort(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];
    for (int i = 0; i <= n - k; i++) {
      result[i] = findMinSortLength(nums, i, i + k - 1);
    }
    return result;
  }

  private int findMinSortLength(int[] nums, int start, int end) {
    int left = start, right = end;
    while (left < right && nums[left] <= nums[left + 1]) {
      left++;
    }
    // Implementation to determine the required length to sort
    // Additional code will fill this logic
    return 0; // Placeholder return
  }
}