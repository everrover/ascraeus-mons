package dsa.leetcode.JupitersGreatStorm;

import java.util.HashMap;

public class TwoSum {
  /**
   * https://leetcode.com/problems/two-sum/
   * 
   * Use a HashMap to store numbers and their indices as we iterate through the array.
   * For each element, check if the complement (target - current number) exists in the map.
   * If it does, return both indices. This provides an O(n) time complexity solution.
   * 
   * TC: O(n) SC: O(n)
   * #array #hash-table #easy
   */
  
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[] { map.get(complement), i };
      }
      map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }
}