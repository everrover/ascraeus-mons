package dsa.leetcode.VallesMarineris;

import java.util.HashMap;
import java.util.Map;

public class MaximumProductOfSubsequencesWithAnAlternatingSumEqualToK {
  /**
   * https://leetcode.com/problems/maximum-product-of-subsequences-with-an-alternating-sum-equal-to-k/description/
   * 
   * Simple DP problem, like knspsack, but with a ton of optimizations.
   * 
   * First, our sum can't be beyond or less than 900, for a maximum of 1800, and minimum of -1800.
   * Needed math here. Maxima and minima finding ... the contraints given in the problem attempted to confuse...
   * 
   * We can use a map to store the dp states, and use a long to store the key. Not a direct `memo` matrix.
   * 
   * Third, we did two passes, one to find the maximum product without zero, and the second to find 
   * if we can have a zero in the array. If we've got a zero, we can have a product of 0 as maximum.
   * 
   * TC: O(n * max sum range * (limit+1) * 2 * 2)
   * SC: O(n * max sum range * (limit+1) * 2 * 2)
   * 
   * #array #hash-table #dynamic-programming #hard
   */

  private Map<Long, Integer> dp; private int []nums; private int limit, k;
  public int maxProduct(int[] nums, int k, int limit) {
    this.nums = nums;
    this.k = k; this.limit = limit;
    this.dp = new HashMap<>();
    int withoutZero = k<=-900 || k>=900? -1 : dfs(0, 0, 0, 0, 1);
    boolean canHaveZeroVal = canHaveZero(0, 0, 0, 0, new Boolean[2][nums.length][1801][2]);
    return withoutZero == -1 ? (canHaveZeroVal ? 0: withoutZero) : withoutZero;
  }

  private boolean canHaveZero(int isOdd, int idx, int sum, int zeroT, Boolean [][][][]flag){
    if(idx>=nums.length) return sum==k && zeroT == 1;
    if(flag[isOdd][idx][sum+900][zeroT] != null) return flag[isOdd][idx][sum+900][zeroT];
    boolean t = canHaveZero(
      isOdd==1?0:1,
      idx+1,
      sum+(isOdd==1?-1:1)*nums[idx],
      (zeroT == 1 || nums[idx] == 0) ? 1 : 0,
      flag
    );
    boolean nt = canHaveZero(isOdd,idx+1,sum,zeroT,flag);
    return flag[isOdd][idx][sum+900][zeroT] = (nt || t);
  }

  private long k(int oneatleast, int isOdd, int idx, int sum, int prod){
    return (long)(1e10*oneatleast+1e9*isOdd+1e6*idx+1e3*sum+prod);
  }

  private int dfs(int oneatleast, int isOdd, int idx, int sum, int prod){
    if(idx>=nums.length) return oneatleast == 1 && sum == k?prod:-1;
    long ke = k(oneatleast, isOdd, idx, sum, prod);
    if(dp.containsKey(ke)) return dp.get(ke);
    int p = -1;
    if(nums[idx] != 0 && prod*nums[idx] <= limit) {
      p = dfs(
        1, isOdd==1?0:1,
        idx+1,
        sum+(isOdd==1?-1:1)*nums[idx],
        prod*nums[idx]
      );
    }

    int np = dfs(oneatleast, isOdd, idx+1, sum, prod);
    dp.put(ke, Math.max(p, np));
    return dp.get(ke);
  }
}