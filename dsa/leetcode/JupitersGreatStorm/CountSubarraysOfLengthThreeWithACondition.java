package dsa.leetcode.JupitersGreatStorm;

public class Solution {

  /**
   * https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/description/?envType=daily-question&envId=2025-04-27
   *
   * Iterate over the array and check for each triplet if the sum of the first and third elements equals half the middle element.
   * If yes, increment the result counter.
   *
   * TC: O(n) SC: O(1)
   * #array #sliding-window #easy
   */

  public int countSubarrays(int[] nums) {
    int res = 0;
    for(int i = 1; i < nums.length - 1; i++) {
      // Check if sum of first and third equals half of second
      if ((nums[i - 1] + nums[i + 1]) == nums[i] / 2) {
        res++;
      }
    }
    return res;
  }

}