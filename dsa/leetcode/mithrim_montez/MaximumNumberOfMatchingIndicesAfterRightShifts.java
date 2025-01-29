package dsa.leetcode.mithrim_montez;

class Solution {
  /**
   * https://leetcode.com/problems/maximum-number-of-matching-indices-after-right-shifts/description/
   *
   * The function simulates right shifts on `nums1` and counts matching indices with `nums2`.
   * For each shift, calculate the number of indices `i` where `nums1[i]` equals `nums2[i]`.
   * This implementation iterates over all shifts and returns the maximum count of matching indices.
   *
   * TC: O(n^2)  where n is the length of the arrays.
   * SC: O(1)  since no extra space is used.
   * #array #two-pointers #simulation #medium
   */
  public int maximumMatchingIndices(int[] nums1, int[] nums2) {
    final int n = nums1.length;
    int res = 0, currres = 0;
    for(int i = 0; i < n; i++){
      currres = 0;
      for(int j = 0; j < n; j++){
        if(nums1[j] == nums2[(j + i) % n]) currres++;
      }
      // Update the maximum number of matching indices found
      res = Math.max(res, currres);
    }
    return res;
  }
}