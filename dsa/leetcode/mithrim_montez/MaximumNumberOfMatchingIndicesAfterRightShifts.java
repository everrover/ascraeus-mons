package dsa.leetcode.mithrim_montez;

class MaximumNumberOfMatchingIndicesAfterRightShifts {
  /**
   * https://leetcode.com/problems/maximum-number-of-matching-indices-after-right-shifts/description/
   *
   * Simple simulation of the right shift operation. B.F.
   *
   * TC: O(n^2) SC: O(1)
   * #array #two-pointers #simulation #medium #brute-force
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