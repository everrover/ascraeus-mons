package dsa.leetcode.EuropasOceanWorld;

class Solution {
  /**
   * https://leetcode.com/problems/three-consecutive-odds/submissions/1630823285/?envType=daily-question&envId=2025-05-11
   *
   * Iterate through the array, count consecutive odd numbers.
   * If the count reaches three, return true.
   *
   * TC: O(n) SC: O(1)
   * #array #simple #easy
   */
  public boolean threeConsecutiveOdds(int[] arr) {
    if(arr.length < 3) return false;
    int cnt = 0;
    for(int i = 0; i < arr.length; i++) {
      // Check if the current number is odd
      if(arr[i] % 2 == 1) {
        cnt++;
        // If three consecutive odds are found
        if(cnt == 3) return true;
      } else {
        cnt = 0;
      }
    }
    return false;
  }
}