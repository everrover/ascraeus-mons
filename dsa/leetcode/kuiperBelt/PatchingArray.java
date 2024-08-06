package dsa.leetcode.kuiperBelt;

// imports here

public class PatchingArray {

  /**
   * https://leetcode.com/problems/patching-array/
   *
   * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.
   * Return the minimum number of patches required.
   *
   * Explanation is in the code comments.
   *
   * TC: O(n) SC: O(1)
   * #array #greedy #hard
   */

  public int minPatches(int[] nums, long n) { // modded input to long to mitigate int overflow
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
  // 1 2 2, 5 => (1o -> 1) (2o -> 3) (2o -> 5)
  // 1 3, 6 => (1o -> 1) (2n -> 3) (3o -> 6)
  // 1 5 10, 20 => (1o -> 1) (2n -> 3) (4n -> 7) (5o -> 12) (10o -> 22)
  // 10, 20 => c1 (1n -> 1) c2 (2n -> 3) c4 (4n -> 7) c8 (8n -> 15)
}