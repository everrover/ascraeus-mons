package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/move-zeroes/
 * This solution iterates through the array, shifting non-zero elements to the front.
 * Then, it fills the remaining positions with zeroes, preserving the order of non-zero elements.
 * 
 * TC: O(n) SC: O(1)
 * #array #two-pointers #easy
 */
public class MoveZeroes {

  public void moveZeroes(int[] nums) {
    int idx = 0;
    for(int i=0; i<nums.length; i++){
      if(nums[i] != 0) nums[idx++]=nums[i];
    }
    for(int i=idx; i<nums.length; i++) nums[i]=0;
  }
}