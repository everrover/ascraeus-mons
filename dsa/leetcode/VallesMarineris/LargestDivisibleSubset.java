package dsa.leetcode.VallesMarineris;

import java.util.*;

public class LargestDivisibleSubset {

  /**
   * https://leetcode.com/problems/largest-divisible-subset/?envType=daily-question&envId=2025-04-06
   *
   * Use dynamic programming to track the size of the largest divisible subset that ends with each element.
   * Also track previous element index to reconstruct the subset.
   * 
   * TC: O(n^2) SC: O(n)
   * #array #math #dynamic-programming #sorting #medium
   */

  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int[] dp = new int[n];
    int[] prev = new int[n];
    Arrays.fill(prev, -1);
    Arrays.fill(dp, 1);

    int maxIndex = 0;
    for(int i = 1; i < n; i++) {
      for(int j = 0; j < i; j++) {
        if(nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
      if(dp[i] > dp[maxIndex]) {
        maxIndex = i;
      }
    }

    List<Integer> result = new ArrayList<>();
    for(int i = maxIndex; i >= 0; i = prev[i]) {
      result.add(nums[i]);
      if(prev[i] == -1) break;
    }
    Collections.reverse(result);
    return result;
  }
}