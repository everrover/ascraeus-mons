package dsa.leetcode.Ipestus;

import java.util.*;

public class FindXSumOfAllKLongSubarrays {

  /**
   * https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/description/
   *
   * For each k-long subarray, count occurrences of elements, retain counts of top x most frequent.
   * If counts are equal, retain elements with higher value.
   * Calculate sum of retained elements for the result.
   *
   * TC: O(n * log(n)) SC: O(n)
   * #array #hash-table #sliding-window #heap #easy
   */

  public int[] findXSum(int[] nums, int k, int x) {
    int[] res = new int[nums.length - k + 1];
    Map<Integer, Integer> ts = new HashMap<>();
    for(int i = 0; i < nums.length - k + 1; i++) {
      for(int j = i; j < i + k; j++) {
        ts.put(nums[j], ts.getOrDefault(nums[j], 0) + 1);
      }
      int[][] farr = new int[ts.size()][2];
      int z = 0, currres = 0;
      for(Map.Entry<Integer, Integer> me : ts.entrySet()) {
        farr[z++] = new int[]{me.getKey(), me.getValue()};
      }
      // Additional logic possibly omitted, add here if needed.
    }
    return res;
  }
}