package dsa.leetcode.mithrim_montez;

import java.util.*;

public class DistinctNumbersInEachSubarray {
  /**
   * https://leetcode.com/problems/distinct-numbers-in-each-subarray/?envType=weekly-question&envId=2025-02-01
   *
   * Use a sliding window approach to maintain a frequency map of elements in the current subarray window. As the window slides, update the map by decreasing the frequency of the outgoing element and increasing the frequency of the incoming element. Remove any element from the map whose frequency becomes zero. The size of the map gives the count of distinct numbers in each subarray window.
   *
   * TC: O(n) SC: O(k)
   * #array #hash-table #sliding-window #medium
   */

  public int[] distinctNumbers(int[] nums, final int k) {
    final int N = nums.length;
    int[] res = new int[N - k + 1];
    Map<Integer, Integer> m = new HashMap<>();

    // Initialize the frequency map for the first window
    for (int i = 0; i < k; i++) {
      m.put(nums[i], m.getOrDefault(nums[i], 0) + 1);
    }

    // Slide the window over the array
    for (int i = k; i < nums.length; i++) {
      res[i - k] = m.size();

      // Decrease frequency of the outgoing element
      if (m.get(nums[i - k]) == 1) {
        m.remove(nums[i - k]);
      } else {
        m.put(nums[i - k], m.get(nums[i - k]) - 1);
      }

      // Increase frequency of the incoming element
      m.put(nums[i], m.getOrDefault(nums[i], 0) + 1);
    }

    // Set the last result
    res[N - k] = m.size();

    return res;
  }
}