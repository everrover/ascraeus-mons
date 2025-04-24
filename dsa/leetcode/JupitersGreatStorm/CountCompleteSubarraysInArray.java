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
    Map<Integer, Integer> h = new HashMap<>();
    for (int i = 0, j = 0; i < nums.length; i++) {
      while(j<nums.length && h.size() < distinct){
        h.put(nums[j], h.getOrDefault(nums[j], 0)+1);j++;
      }
      if(h.size() == distinct){
        res += (nums.length-j+1);
      }
      if(h.get(nums[i]) == 1) {
        h.remove(nums[i]);
      }else h.put(nums[i], h.get(nums[i])-1);
    }
    return res;
  }
}