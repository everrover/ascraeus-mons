package dsa.leetcode.VallesMarineris;

public class ApplyOperationsToAnArray {
  /**
   * https://leetcode.com/problems/apply-operations-to-an-array/description/?envType=daily-question&envId=2025-03-01
   * 
   * Iterate over the array and for each element, check if it equals the next element. 
   * If so, double the current element and set the next element to zero. 
   * Finally, shift all zeros to the end of the array.
   * 
   * TC: O(n) SC: O(1)
   * #array #two-pointers #simulation #easy
   */

  public int[] applyOperations(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n - 1; i++) {
      // Apply operations
      if (nums[i] == nums[i + 1]) {
        nums[i] *= 2;
        nums[i + 1] = 0;
      }
    }
    // Shift zeros to the end (in-place)
    int nonZeroIdx = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] != 0) {
        nums[nonZeroIdx++] = nums[i];
      }
    }
    while (nonZeroIdx < n) {
      nums[nonZeroIdx++] = 0;
    }
    return nums;
  }
}