package dsa.leetcode.fermi_s_paradox;

public class MaximumSubarrayWithEqualProducts {

  /**
   * https://leetcode.com/problems/maximum-subarray-with-equal-products/description/
   *
   * For each subarray, calculate the product, gcd, and lcm. If product equals gcd * lcm,
   * update the maximum length found. Simple brute force...
   *
   * TC: O(n^2) SC: O(1)
   * #math #gcd-lcm #subarray #easy
   */

  private static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }

  private static int lcm(int a, int b) {
    return (a * b) / gcd(a, b);
  }

  public int maxLength(int[] nums) {
    int n = nums.length;
    int res = 0;
    for (int i = 0; i < n; i++) {
      long product = 1;
      int currentGCD = nums[i];
      int currentLCM = nums[i];
      for (int j = i; j < n; j++) {
        product *= nums[j];
        currentGCD = gcd(currentGCD, nums[j]);
        currentLCM = lcm(currentLCM, nums[j]);
        // Check if the subarray satisfies the condition
        if (product == (long) currentGCD * currentLCM) {
          res = Math.max(res, j - i + 1);
        }
      }
    }
    return res;
  }
}