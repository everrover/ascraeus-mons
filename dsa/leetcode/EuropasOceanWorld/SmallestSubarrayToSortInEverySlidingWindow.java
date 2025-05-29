package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class SmallestSubarrayToSortInEverySlidingWindow {

  /**
   * https://leetcode.com/problems/smallest-subarray-to-sort-in-every-sliding-window/description/
   *
   * For each sliding window, determine the smallest subarray that must be sorted
   * to make the window non-decreasing. The approach is to examine each window
   * separately and compute the necessary index bounds.
   *
   * TC: O(n*k) SC: O(1)
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
    if (left == end) return 0;

    while (right > start && nums[right] >= nums[right - 1]) {
      right--;
    }

    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int i = left; i <= right; i++) {
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }

    while (left > start && nums[left - 1] > min) {
      left--;
    }
    while (right < end && nums[right + 1] < max) {
      right++;
    }

    return right - left + 1;
  }
}