package dsa.leetcode.kuiperBelt;

// imports here

public class FindMinimumInRotatedSortedArrayII {
  /**
   * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
   *
   * Given a sorted rotated array that may contain duplicates, the code finds the minimum element.
   * This is done by iterating through the array and checking for the point where the previous element is larger than the current element.
   *
   * TC: O(n) SC: O(1)
   * #array #binary-search #hard
   */

  public int findMin(int[] nums) {
    // Loop through the array from the end to find the point of rotation
    for(int i = nums.length - 1; i > 0; i--) {
      if(nums[i - 1] > nums[i]) return nums[i];
    }
    return nums[0];
  }
}