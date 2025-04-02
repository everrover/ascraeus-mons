package dsa.leetcode.VallesMarineris;

public class MaximumValueOfAnOrderedTriplet {
  /**
   * https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/description/?envType=daily-question&envId=2025-04-02
   *
   * Use three nested loops to find all the triplets of indices (i, j, k) such that i < j < k. 
   * Calculate the value (nums[i] - nums[j]) * nums[k] and track the maximum value.
   * Return the maximum value found or 0 if all such triplets have a negative value.
   *
   * TC: O(n^3) SC: O(1)
   * #array #brute-force #easy
   */
  
  public long maximumTripletValue(int[] nums) {
    long res = 0L;
    for(int i=0; i<nums.length; i++){
      for(int j=i+1; j<nums.length; j++){ 
        for(int k=j+1; k<nums.length; k++){ 
          res = Math.max(
            res, 
            (0L+nums[i]-nums[j])*nums[k] 
          ); 
        } 
      } 
    }
    return res;
  }
}