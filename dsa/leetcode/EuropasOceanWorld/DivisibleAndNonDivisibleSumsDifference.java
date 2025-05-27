package dsa.leetcode.EuropasOceanWorld;

class Solution {
  /**
   * https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/description/?envType=daily-question&envId=2025-05-27
   *
   * Calculate two sums: one for numbers not divisible by m and another for those divisible by m within a range.
   * Subtract the sum of divisible numbers from the non-divisible to get the result.
   *
   * TC: O(n) SC: O(1)
   * #math #easy
   */
  public int differenceOfSums(int n, int m) {
    int n1 = 0, n2 = 0;
    for(int i=1; i<=n; i++){
      if(i%m == 0) n2+=i;
      else n1+=i;
    }
    return n1-n2;
  }
}