package dsa.leetcode.fermi_s_paradox;

import java.util.Arrays;

public class RearrangeArrayToMaximizePrefixScore {

  /**
   * https://leetcode.com/problems/rearrange-array-to-maximize-prefix-score/
   * 
   * Sort the array in decreasing order and count the number of positive values in the prefix sum array.
   * The goal is to maximize the number of positive integers in the prefix.
   * 
   * TC: O(n log n) due to sorting, SC: O(1)
   * #array #greedy #sorting #prefix-sum #medium
   */

  public int maxScore(int[] nums) {
    Arrays.sort(nums);
    int res = 0; long psum = 0;
    int l=0;
    for(l=nums.length-1; l>=0; l--){
      if(nums[l] <= 0) break;
      psum += nums[l];
      res++;
    }
    // Consider elements left if their prefix sum remains positive
    while(l>=0){
      psum += nums[l--];
      if(psum <= 0L) break;
      res++;
    }
    return res;
  }
}