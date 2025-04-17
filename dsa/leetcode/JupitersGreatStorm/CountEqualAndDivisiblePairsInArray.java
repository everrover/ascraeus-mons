package dsa.leetcode.JupitersGreatStorm;

public class CountEqualAndDivisiblePairsInArray {
  /**
   * https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/description/?envType=daily-question&envId=2025-04-17
   * 
   * For every pair (i, j) with i < j, check if nums[i] == nums[j] and (i * j) is divisible by k.
   * The solution iterates through each possible pair to count valid ones.
   * 
   * TC: O(n^2) SC: O(1)
   * #array #brute-force #easy
   */

  public int countPairs(int[] nums, int k) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        // Check if the pair (i, j) satisfies both conditions
        if (nums[i] == nums[j] && (i * j) % k == 0) {
          count++;
        }
      }
    }
    return count;
  }
}