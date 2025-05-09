package dsa.leetcode.EuropasOceanWorld;

class Solution {
  /**
   * https://leetcode.com/problems/maximum-product-subarray/description/
   *
   * Iterate over the array, tracking the local maximum and minimum products.
   * Multiply current element to the current max and min, update global max accordingly.
   * Carefully handle potential negatives by comparing products with and without the current number.
   *
   * TC: O(n) SC: O(1) 
   * #array #dynamic-programming #medium
   */
  
  public int maxProduct(int[] nums) {
    int maxProduct = nums[0], maxValue = nums[0], minValue = nums[0];
    for(int i = 1; i < nums.length; i++) {
      int num = nums[i];
      int tmp = minValue;
      minValue = Math.min(Math.min(minValue * num, maxValue * num), num);
      maxValue = Math.max(Math.max(tmp * num, maxValue * num), num);
      maxProduct = Math.max(maxValue, maxProduct);
    }
    return maxProduct;
  }
}