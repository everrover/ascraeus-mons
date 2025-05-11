package dsa.leetcode.EuropasOceanWorld;

class MaximumProductSubarray {
  /**
   * https://leetcode.com/problems/maximum-product-subarray/description/
   *
   * Iterate over the array, tracking the local maximum and minimum products.
   * Multiply current element to the current max and min, update global max accordingly.
   * Carefully handle potential negatives by comparing products with and without the current number.
   * 
   * `0` IS THE split index, here . So our max-subarray can;t ever have a `0` if any positive number exists.
   * If range queries were used, it would become cumbersome into play where again negative values will cause trouble.
   * Tracking prev positive and negative numbers is a possible method.
   * > We'll have to track first and last negative numbers indexes. 
   * > If count of negative numbers is even, we can take the whole array.
   * > If count of negative numbers is odd, we can take the whole array except either
   * for the first or last negative number, to have a positive product.
   * > that are positive only.
   * 
   * We can use DP as well, tracking the max and min product till a certain index.
   * 0 = positive, 1 = negative
   * For next set of numbers, the dfs(idx, 0) = dfs(idx-1, 1) * nums[idx], nums[idx] > 0
   *                                          = dfs(idx-1, 0) * nums[idx], nums[idx] < 0
   *                              dfs(idx, 1) = dfs(idx-1, 0) * nums[idx], nums[idx] > 0
   *                                          = dfs(idx-1, 1) * nums[idx], nums[idx] < 0
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