package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MinimumNumberOfOperationsToMakeElementsInArrayDistinct {
  
  /**
   * https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/description/
   *
   * To make the array distinct, remove 3 elements from the front repeatedly until no duplicates exist. The formula calculates the number of batches required.
   * 
   * TC: O(n) SC: O(n)
   * #array #hash-table #easy
   */
  
  public int minimumOperations(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for(int i = nums.length - 1; i >= 0; i--) {
      if(seen.contains(nums[i])) break; // Stop if a duplicate is found
      else {
        seen.add(nums[i]); // Collect distinct elements from the back
      }
    }
    return (int) Math.ceil((double)(nums.length - seen.size()) / 3.0); // Calculate operations needed
  }
}