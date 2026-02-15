package dsa.leetcode.VallesMarineris;

public class FindTheLargestAlmostMissingInteger1 {

  /**
   * https://leetcode.com/problems/find-the-largest-almost-missing-integer/description/
   *
   * Consider and count each element's occurrences in 'k' sized subarrays. The answer is the largest element
   * that appears exactly once in any subarray of size 'k'. Check boundaries for special conditions.
   *
   * TC: O(n*k) SC: O(1)
   * #arrays #sliding-window #easy
   */

  public int largestInteger(int[] nums, int k) {
    int[] cnt = new int[51];
    int[] rhash = new int[51];
    int res = -1;
    // Count for the first subarray
    for (int i = 0; i < nums.length && i < k; i++) {
      cnt[nums[i]]++;
    }
    // Update how many times each number appears in the counted subarrays
    for (int idx = 0; idx < cnt.length; idx++) if (cnt[idx] > 0) rhash[idx]++;
    // Slide through the array
    for (int i = k; i < nums.length; i++) {
      cnt[nums[i]]++;
      cnt[nums[i - k]]--;
      for (int idx = 0; idx < cnt.length; idx++) if (cnt[idx] > 0) rhash[idx]++;
    }
    // Determine the result based on recorded counts
    for (int i = 50; i >= 0; i--) {
      if (rhash[i] == 1) {
        res = i;
        break;
      }
    }
    return res;
  }
}