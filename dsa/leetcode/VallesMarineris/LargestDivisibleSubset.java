package dsa.leetcode.VallesMarineris;

import java.util.*;

public class LargestDivisibleSubset {

  /**
   * https://leetcode.com/problems/largest-divisible-subset
   *
   * dfs(idx) = max(dfs(idx+i), (dfs(idx+i) + 1) if nums[idx] % nums[i] == 0 else 0) for i in range(idx+1, n)
   * 
   * on same premise, i've used bottom up dp to solve this problem.
   * `prev` is used to store the previous index of the element in the subset.
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