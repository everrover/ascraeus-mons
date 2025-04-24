package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountCompleteSubarraysInArray {

  /**
   * https://leetcode.com/problems/count-complete-subarrays-in-an-array/description/?envType=daily-question&envId=2025-04-24
   *
   * The problem revolves around identifying subarrays where the number of distinct elements in the subarray
   * matches the number of distinct elements in the entire array. This is solved using a sliding window approach
   * and hashmap to count occurrences of elements.
   *
   * TC: O(n^2) SC: O(n)
   * #array #hash-table #sliding-window #medium
   */

  public int countCompleteSubarrays(int[] nums) {
    int distinct = (int) Arrays.stream(nums).distinct().count(); // Total distinct elements in nums
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      Map<Integer, Integer> h = new HashMap<>();
      for (int j = i; j < nums.length; j++) {
        h.put(nums[j], h.getOrDefault(nums[j], 0) + 1);
        if (h.size() == distinct) {
          res++;
        }
      }
    }
    return res;
  }
}