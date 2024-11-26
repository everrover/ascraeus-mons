package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class MinNumberOfOperationsToMakeArrayContinuous {
  /**
   * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/submissions/
   *
   * To make the array continuous, for every unique element, use binary search and sliding window.
   * Find the maximum number of unique elements that could create a sequence of nums.length.
   *
   * TC: O(n log n) SC: O(1)
   * #array #binary-search #sliding-window #hard
   */

  public int minOperations(int[] nums) {
    Arrays.sort(nums);
    final int N = nums.length;
    int mk = 1;
    for(int i=1; i<nums.length; i++){
      if(nums[i] != nums[i-1]) nums[mk++]=nums[i];
    }
    int reslow = N;
    for(int i=0, j=0; i<mk; i++){
      while(j<mk && nums[j]-nums[i] <= N-1) j++;
      reslow = Math.min(reslow, N - (j - i)); // Minimize total moves
    }
    return reslow;
  }
}