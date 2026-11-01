package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/count-subarrays-with-loose-bounds/
 *
 * Maximal runs. A subarray is loose-bound iff every element lies in [minK, maxK], so any element
 * outside the range can never belong to one. Such elements split the array into maximal runs of
 * in-range elements, and every valid subarray lies entirely inside exactly one run.
 * - Within a run of length L, every subarray is valid, and there are L * (L + 1) / 2 of them (one
 *   for each pair of start <= end). The answer is the sum of this over all runs.
 * - j marks the start of the current run (-1 when none is open). On an out-of-range element at i the
 *   run [j, i) is closed and counted; a run that reaches the end of the array is counted after the
 *   loop. An out-of-range element with no open run opens and closes an empty run, which adds 0.
 * - L can reach 10^5, so L * (L + 1) / 2 needs 64 bits, hence the long in seq.
 *
 * This is the loose version of "Count Subarrays With Fixed Bounds", which also requires the min and
 * max of the subarray to equal minK and maxK exactly.
 *
 * TC: O(n) SC: O(1)
 * #array #math #two-pointers #medium
 */

class CountSubarraysWithLooseBounds {
  private long seq(int num){
    long tnum = (long)num;
    return tnum*(tnum+1)/2;
  }
  public long countSubarrays(int[] nums, int minK, int maxK) {
    long res = 0;
    int i=0, j=-1; 
    while(i<nums.length){
      if(j == -1) j = i;
      if(nums[i] > maxK || nums[i] < minK){
        res += seq(i-j);
        j = -1;
      }
      i++;
    }
    if(j != -1) return res+=seq(i-j);
    return res;
  }
}
