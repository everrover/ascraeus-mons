package dsa.leetcode.VallesMarineris;

import java.util.HashMap;
import java.util.Map;

public class MinimumOperationsToMakeArrayValuesEqualToK {
  /**
   * https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/?envType=daily-question&envId=2025-04-09
   *
   * To solve this problem, we need to make all the elements in the array equal to the target integer k by performing a series of valid integer operations. A valid operation replaces elements greater than a valid integer h with h. The goal is to reduce all elements to k or determine if it is impossible. We achieve this by counting how many distinct integers are greater than k.
   *
   * TC: O(n) SC: O(n)
   * #array #hash-table #greedy #easy
   */
  
  public int minOperations(int[] nums, int k) {
    Map<Integer, Integer> s = new HashMap<>();
    int m = 1000;
    for (int n : nums) {
      if (n != k) 
        s.put(n, s.getOrDefault(n, 0) + 1);
      m = Math.min(m, n);
    }
    return m < k ? -1 : s.size();
  }
}