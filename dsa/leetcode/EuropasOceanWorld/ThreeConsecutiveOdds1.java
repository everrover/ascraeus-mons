package dsa.leetcode.EuropasOceanWorld;

class ThreeConsecutiveOdds {

  /**
   * https://leetcode.com/problems/three-consecutive-odds/submissions/1630823285/?envType=daily-question&envId=2025-05-11
   * 
   * Iterate through the array and keep a count of consecutive odd numbers.
   * If three in a row are found, return true. Otherwise, return false.
   * 
   * TC: O(n), SC: O(1)
   * #array #parity-check #easy
   */

  public boolean threeConsecutiveOdds(int[] arr) {
    int cnt = 0;
    if(arr.length < 3) return false;
    for(int i = 0; i < arr.length; i++) {
      if(arr[i] % 2 == 1) {
        cnt++;
        // If three consecutive odds are found
        if(cnt == 3) return true;
      } else {
        cnt = 0; // Reset count if an even number is encountered
      }
    }
    return false;
  }
}