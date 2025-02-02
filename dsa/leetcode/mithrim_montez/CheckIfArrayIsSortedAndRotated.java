package dsa.leetcode.mithrim_montez;

public class CheckIfArrayIsSortedAndRotated {

  /**
   * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/?envType=daily-question&envId=2025-02-02
   *
   * Checks whether the array was originally sorted in non-decreasing order and then
   * rotated. It iterates through the array and checks if any element is greater
   * than its successor (accounting for rotation using modulo operation). If this
   * occurs more than once, the array is not properly sorted and rotated.
   *
   * TC: O(n), SC: O(1)
   * #array #rotation #check #easy
   */

  public boolean check(int[] nums) {
    int cnt = 0;
    for (int i = 0; i < nums.length; i++) {
      // Check if current element is greater than the next, accounting for rotation
      if (nums[i] > nums[(i + 1) % nums.length]) {
        cnt++;
      }
      // If the condition found more than once, array is not sorted and rotated correctly
      if (cnt > 1) {
        return false;
      }
    }
    return true;
  }
}