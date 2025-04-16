package dsa.leetcode.JupitersGreatStorm;

import java.util.HashMap;

public class CountTheNumberOfGoodSubarrays {

  /**
   * https://leetcode.com/problems/count-the-number-of-good-subarrays/description/?envType=daily-question&envId=2025-04-16
   *
   * Maintain two pointers, left and right, to dynamically explore subarrays while keeping track of
   * the number of pairs using a HashMap. Increment right to include new elements; if adding new
   * elements forms at least k pairs, record the positive result and increment left to reduce pairs.
   *
   * TC: O(n) SC: O(n)
   * #array #hash-table #sliding-window #medium
   */

  public long countGoodSubarrays(int[] nums, int k) {
    int n = nums.length;
    long res = 0;
    HashMap<Integer, Integer> cnt = new HashMap<>();
    int same = 0, right = -1;

    for (int left = 0; left < n; ++left) {
      while (same < k && right + 1 < n) {
        ++right;
        cnt.put(nums[right], cnt.getOrDefault(nums[right], 0) + 1);
        same += cnt.get(nums[right]) - 1;
      }
      if (same >= k) {
        res += n - right;
      }
      cnt.put(nums[left], cnt.get(nums[left]) - 1);
      same -= cnt.get(nums[left]);
    }

    return res;
  }
}