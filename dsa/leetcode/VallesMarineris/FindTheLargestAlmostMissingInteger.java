package dsa.leetcode.VallesMarineris;

public class FindTheLargestAlmostMissingInteger {

  /**
   * https://leetcode.com/problems/find-the-largest-almost-missing-integer/description/
   *
   * Traverse through subarrays of size k, tracking each element’s occurrence count.
   * Elements that appear exactly once in only one subarray are candidate solutions.
   * Compare the candidates to find the largest. Adjust counts as the window slides.
   *
   * TC: O(n * k) SC: O(1)
   * #array #sliding-window #counting #easy
   */

  public int findLargestAlmostMissing(Integer[] nums, int k) {
    int res = -1;
    int[] rhash = new int[51];
    int[] cnt = new int[51];

    // Initial population of counts for the first subarray of size k.
    for (int i = 0; i < nums.length && i < k; i++) {
      cnt[nums[i]]++;
    }

    // Process each element in the subarray and adjust as window slides
    for (int i = k; i < nums.length; i++) {
      cnt[nums[i]]++;
      cnt[nums[i - k]]--;

      // Update khash for the current subarray's elements
      for (int idx = 0; idx < cnt.length; idx++) if (cnt[idx] > 0) rhash[idx]++;

      // Identify the maximum almost missing number
      for (int idx = 0; idx < cnt.length; idx++) if (rhash[idx] == 1) res = Math.max(res, idx);
    }

    return res;
  }
}