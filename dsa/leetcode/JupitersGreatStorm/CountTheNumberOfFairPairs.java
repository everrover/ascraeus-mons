package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountTheNumberOfFairPairs {
  /**
   * https://leetcode.com/problems/count-the-number-of-fair-pairs/description/?envType=daily-question&envId=2025-04-19
   * 
   * For each number in the sorted array, perform binary search to find the smallest and largest numbers
   * that can form a fair pair with the current number, given the constraints.
   * 
   * TC: O(n log n) due to sorting and binary search; SC: O(1)
   * #array #two-pointers #binary-search #sorting #medium
   */

   private int bsearch(int []nums, int till, int lower, int upper){
    int l = 0, r = till-1, m;
    int start = -2, end = -2;
    // find start
    while(l<=r){
      m = (l+r)/2;
      if(nums[m] >= lower){
        start = m;
        r = m-1;
      }else if(nums[m] < lower){
        l = m+1;
      }
    }
    // find lower element count
    l = 0; r = till-1;
    while(l<=r){
      m = (l+r)/2;
      if(nums[m] <= upper){
        end = m;
        l = m+1;
      }else if(nums[m] > upper){
        r = m-1;
      }
    }
    return (start==-2||end==-2)?0:(end-start+1);
  }
  public long countFairPairs(int[] nums, int lower, int upper) {
    long res = 0L;
    Arrays.sort(nums);
    for(int i=0; i<nums.length; i++){
      int b = bsearch(nums, i, lower-nums[i], upper-nums[i]);
      res += b;
    }
    return res;
  }
  // pair i,j
  // upper >= nums[i]+nums[j] >= lower
  // upper-nums[i] >= nums[j] >= lower-nums[i]
}