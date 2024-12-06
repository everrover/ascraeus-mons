package dsa.leetcode.RhoCassiopeiae;

public class RemoveOneElementToMakeArrayStrictlyIncreasing {
  
  /**
   * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
   *
   * Iterate through the array to find any violation of the strictly increasing order. Count such occurrences.
   * If there is more than one, it is impossible to fix by removing one element, return false.
   * Else, check if strictly increasing order can be maintained by removing the element at the violation index.
   * 
   * TC: O(n) SC: O(1)
   * #array #greedy #easy
   */
  
  public boolean canBeIncreasing(int[] nums) {
    int mk = 0, idx = -1;
    for(int i = 1; i < nums.length; i++) {
      if(nums[i - 1] >= nums[i]) {
        mk++;
        idx = i - 1;
      }
    }
    if(mk > 1) return false;
    // removing here will make it strictly increasing since nth can be added to the left or right
    if(idx == -1 || idx == 0 || idx == nums.length - 2) return true;
    // check if removing the element at the violation index will make the array strictly increasing
    if(nums[idx + 1] > nums[idx - 1] || nums[idx + 2] > nums[idx]) return true;
    
    return false;
  }
}