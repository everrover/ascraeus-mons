package dsa.leetcode.EuropasOceanWorld;

public class FindTheSmallestDivisorGivenAThreshold {

  /**
   * https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/?envType=company&envId=agoda&favoriteSlug=agoda-all
   * 
   * Given an array, find a positive integer divisor so that the sum of all array elements, divided by the divisor and rounded up, is less than or equal to a threshold. Utilize binary search to efficiently pinpoint the smallest divisor.
   * 
   * TC: O(n log(max(nums))) SC: O(1)
   * #binary-search #array #medium
   */

  public int smallestDivisor(int[] nums, int threshold) {
    int l = 1, r = 0, res = 0;
    for(int n: nums) r = Math.max(n, r);
    while(l <= r) {
      int m = (l + r) / 2;
      if(compute(nums, m, threshold)) {
        res = m;
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return res;
  }

  private boolean compute(int[] nums, int m, int threshold) {
    int res = 0;
    for(int n: nums) {
      res += (n / m) + (n % m > 0 ? 1 : 0);
    }
    return res <= threshold;
  }
}