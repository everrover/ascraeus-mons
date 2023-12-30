package dsa.leetcode.kuiperBelt;

public class FormArrayByConcatenatingSubarraysOfAnotherArray {
  /**
   * https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array/
   * 
   * Since constraints are small, we can use brute force for sub-array search. 
   * Can use KMP algo for better performance.
   * 
   * TC: O(n*max(group-size)) SC: O(1)
   * 
   * #array #medium
   */
  public boolean canChoose(int[][] groups, int[] nums) {
    int i=0;
    for(int start =0;i < groups.length && groups[i].length+start <= nums.length;start++)
      if(search(groups[i], nums, start)) 
        start += groups[i++].length - 1;
    return i==groups.length;
  }
  private boolean search(int[] group, int[] nums, int start) { // can use KMP algo here
    for(int i=0;i<group.length;i++) 
      if(group[i] != nums[i+start])
        return false;
    return true;
  }
}
