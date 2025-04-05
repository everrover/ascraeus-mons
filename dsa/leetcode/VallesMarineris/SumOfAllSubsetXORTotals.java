package dsa.leetcode.VallesMarineris;

public class SumOfAllSubsetXORTotals {

  /**
   * https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/?envType=daily-question&envId=2025-04-05
   * 
   * To find the sum of all XOR totals for every subset, use depth-first search (DFS) to iterate over each element. At each element, choose to include or exclude it in the subset, calculating the XOR recursively with or without the element.
   * 
   * Cheeky way:
   * If we add x with x, in bits it moves left by 1 shift.
   * If we xor n elements, and if all its subsets are added to xor, then the xor of all subsets will shift left by the count of numbers we have.
   * I tried it with 4, 7, 11, 11. Worked.
   * 
   * TC: O(2^n) SC: O(n)
   * #backtracking #bit-manipulation #easy
   */

  public int subsetXORSum(int[] nums) {
    // return dfs(0, 0, nums);
    int n = nums.length;
    int xor = 0;
    for (int i = 0; i < n; i++) {
      xor |= nums[i];
    }
    return xor * (1 << (n - 1)); // xor << (n-1)
  }

  private int dfs(int idx, int xor, int[] nums) {
    if(idx >= nums.length) return xor;
    int w = dfs(idx + 1, xor ^ nums[idx], nums); // Include nums[idx]
    int wo = dfs(idx + 1, xor, nums);           // Exclude nums[idx]
    return w + wo;
  }
}