package dsa.leetcode.kuiperBelt;

// imports here

public class PatchingArray {

  /**
   * https://leetcode.com/problems/patching-array/
   *
   * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.
   * Return the minimum number of patches required.
   *
   * TC: O(log n) SC: O(1)
   * #array #greedy #hard
   */

  public int minPatches(int[] nums, long n) {
    int res = 0, idx = 0;
    long curr = 1;
    while(n >= curr){
      if(idx < nums.length && nums[idx] <= curr){
        curr += nums[idx++];
      }else{ // greedy patch
        res++;
        curr += curr;
      }
    }
    return res;
  }
}