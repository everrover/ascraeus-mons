package dsa.leetcode.EuropasOceanWorld;

class ZeroArrayTransformationI {

  /**
   * https://leetcode.com/problems/zero-array-transformation-i/description/?envType=daily-question&envId=2025-05-20
   *
   * We use a difference array to efficiently apply decrement operations over multiple queries. 
   * For each range in queries, we increment at the start index and decrement immediately after the end index. 
   * Then apply prefix sums to obtain final decrements required at each index.
   * Return true if all transformations make nums a Zero Array, false otherwise.
   *
   * TC: O(n + m) SC: O(n)
   * #array #prefix-sum #decrement #medium
   */

  public boolean isZeroArray(int[] nums, int[][] queries) {
    int[] pre = new int[nums.length];

    // Apply each query to the prefix sum array
    for (int[] q : queries) {
      pre[q[0]]++;  // Increment at start of range
      if (q[1] < nums.length - 1) pre[q[1] + 1]--;  // Decrement right after the end of range
    }

    int currentDec = 0;
    // Compute prefix sums and check if transformation to Zero Array is possible
    for (int i = 0; i < nums.length; i++) {
      currentDec += pre[i];
      if (currentDec < nums[i]) return false;  // If decrements can't reach current value
    }

    return true;
  }
}