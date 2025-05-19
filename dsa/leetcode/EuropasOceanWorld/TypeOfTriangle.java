package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class Solution {

  /**
   * https://leetcode.com/problems/type-of-triangle/description/?envType=daily-question&envId=2025-05-19
   *
   * The function first sorts the sides of the triangle. It then checks if the sum of any two sides
   * is greater than the third side to validate if it can form a triangle. Based on the uniqueness
   * of the side lengths, it returns 'equilateral', 'isosceles', or 'scalene'.
   *
   * TC: O(1) SC: O(1)
   * #array #math #easy
   */
  
  public String triangleType(int[] nums) {
    Arrays.sort(nums);
    int a = nums[0], b = nums[1], c = nums[2];
    if (a + b <= c || a + c <= b || b + c <= a) return "none";
    if (a == b && b == c) return "equilateral";
    if (a == b || b == c || a == c) return "isosceles";
    return "scalene";
  }
}