package dsa.leetcode.VallesMarineris;

public class MaximumProductOfSubsequencesWithAnAlternatingSumEqualToK {
  /**
   * https://leetcode.com/problems/maximum-product-of-subsequences-with-an-alternating-sum-equal-to-k/description/
   * 
   * The problem requires finding a subsequence with an alternating sum equal to k and maximizing its product within a limit.
   * Use dynamic programming to explore all possible products with a particular sum.
   * Ensure to handle cases with a product of 0 and an alternating sum of k.
   * 
   * TC: O(n * max sum range) SC: O(n * max sum range * indicates possible zero)
   * #array #hash-table #dynamic-programming #hard
   */

  private boolean canHaveZero(int isOdd, int idx, int sum, int zeroT, Boolean [][][] flag) {
    if(flag[isOdd][idx][sum+900][zeroT] != null) return flag[isOdd][idx][sum+900][zeroT];
    if(idx >= nums.length) return sum == k && zeroT == 1;

    // Exploring subsequences with the current element
    boolean t = canHaveZero(
      isOdd == 1 ? 0 : 1, 
      idx + 1, 
      sum + (isOdd == 1 ? -1 : 1) * nums[idx], 
      (zeroT == 1 || nums[idx] == 0) ? 1 : 0, 
      flag);

    // Exploring subsequences without the current element
    boolean nt = canHaveZero(isOdd, idx + 1, sum, zeroT, flag);  

    return flag[isOdd][idx][sum + 900][zeroT] = (nt || t);
  }
}