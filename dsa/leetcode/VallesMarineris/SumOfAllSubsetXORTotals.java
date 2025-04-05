package dsa.leetcode.VallesMarineris;

public class SumOfAllSubsetXORTotals {

  /**
   * https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/?envType=daily-question&envId=2025-04-05
   * 
   * To find the sum of all XOR totals for every subset, use depth-first search (DFS) to iterate over each element. At each element, choose to include or exclude it in the subset, calculating the XOR recursively with or without the element.
   * 
   * TC: O(2^n) SC: O(n)
   * #backtracking #bit-manipulation #easy
   */

  public int subsetXORSum(int[] nums) {
    return dfs(0, 0, nums);
  }

  private int dfs(int idx, int xor, int[] nums) {
    if(idx >= nums.length) return xor;
    int w = dfs(idx + 1, xor ^ nums[idx], nums); // Include nums[idx]
    int wo = dfs(idx + 1, xor, nums);           // Exclude nums[idx]
    return w + wo;
  }
}