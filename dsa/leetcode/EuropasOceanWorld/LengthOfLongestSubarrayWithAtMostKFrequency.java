package dsa.leetcode.EuropasOceanWorld;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubarrayWithAtMostKFrequency {

  /**
   * https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/submissions/1631204315/?envType=company&envId=makemytrip&favoriteSlug=makemytrip-all
   *
   * Use sliding window technique to maintain the longest subarray where no element
   * appears more than k times. Increment window size until condition is violated
   * then adjust the window by moving the left pointer.
   *
   * TC: O(n) SC: O(n)
   * #sliding-window #array #hash-map #medium
   */

  public int lengthOfLongestSubarray(int[] nums, int k) {
    Map<Integer, Integer> m = new HashMap<>();
    int n = nums.length;
    int res = 0;
    int uniq = 0;

    for (int i = 0, j = 0; i < n && j < n; i++) {
      while (j < n && m.size() <= uniq && m.getOrDefault(nums[j], 0) + 1 <= k) {
        // Increase frequency count of current element
        m.put(nums[j], m.getOrDefault(nums[j], 0) + 1);
        j++; // expand window
      }
      // Update result with maximum size found
      res = Math.max(res, j - i);

      // Reduce frequency or remove element from map
      m.put(nums[i], m.get(nums[i]) - 1);
      if (m.get(nums[i]) == 0) m.remove(nums[i]);
    }
    return res;
  }
}