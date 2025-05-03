package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/
 * This problem uses a dynamic programming approach to solve for the maximum sum subsequence with 
 * non-adjacent elements. Like house robber, we can't select adjacent elements in the array.
 * The key insight is to use a dp array to cache intermediate results and use recursion to explore options.
 *
 * Got TLE with this...
 * 
 * TC: O(n*m) SC: O(n)
 * 
 * https://leetcode.com/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/
 * 
 * The problem is solved by building a segment tree where each node maintains four states representing the maximum 
 * sum with either including or excluding the leftmost/rightmost element of range
 * . 
 * The states are represented as follows: 00 - exclude left and right, 01 - include left and exclude right,
 * 10 - exclude left and include right, 11 - include left and right
 * 
 * We then iterate through the queries, updating the value in the array at the specified position and recalculating 
 * the segment tree nodes affected by this change. O(log(n)) nodes are affected by each update.
 * 
 * Finally, for each query, we find the maximum sum of subsequences with non-adjacent elements by considering the 
 * range [0,n-1] in segment tree and taking the maximum of the four states of the root node.
 *
 * TC: O(queries.length * log(nums.length)) SC: O(nums.length) due to the segment tree.
 * #segment-tree #dynamic-programming #array #hard #recursion #modular-arithmetic
 */

public class MaximumSumOfSubsequenceWithNonAdjacentElements {

  private static class N{
    public long _00,_01,_10,_11;
    // a=00 b=01 c=10 d=11
    // 10 = 1010 1100 1000
    public N(int num){
      _00 = 0;
      _01 = _10 = (long)(-1e10); // Long.MIN_VALUE was used that got me stuck for a while
      _11 = num;
    }
  }
  private void combine(int idx, int left, int right){
      N ln = ans[left]; N rn = ans[right];
      ans[idx]._00 = Math.max(ln._00+rn._00, Math.max(ln._00+rn._10, ln._01+rn._00));
      ans[idx]._01 = Math.max(ln._00+rn._01, Math.max(ln._00+rn._11, ln._01+rn._01));
      ans[idx]._10 = Math.max(ln._10+rn._00, Math.max(ln._10+rn._10, ln._11+rn._00));
      ans[idx]._11 = Math.max(ln._10+rn._01, Math.max(ln._10+rn._11, ln._11+rn._01));
  }
  N []ans;
  int arr[];
  private void build(int l, int r, int idx){
    if(l>r) return;
    if(l == r){
      ans[idx] = new N(arr[l]);
      return;
    }else{
      int mid = l+(r-l)/2;
      int left = 2*idx+1;
      int right = 2*idx+2;
      build(l, mid, left);
      build(mid+1, r, right);
      ans[idx] = new N(0);
      combine(idx, left, right);
    }
  }
  private void update(int l, int r, int idx, int pos, int val){
    if(l>r) return;
    if(l == r){
      ans[idx] = new N(val);
      return;
    }else{
      int mid = l+(r-l)/2;
      int left = 2*idx+1;
      int right = 2*idx+2;
      if(mid>=pos) update(l, mid, left, pos, val);
      else update(mid+1, r, right, pos, val);
      combine(idx, left, right);
    }
  }
  private final static long MOD = (long)1e9+7;
  public int maximumSumSubsequence(int[] nums, int[][] queries) {
    ans = new N[4*nums.length];
    arr = nums;
    build(0, nums.length-1, 0);
    long res = 0;
    for(int []q: queries){
      update(0, nums.length-1, 0, q[0], q[1]);
      res = (res+Math.max(ans[0]._11, Math.max(ans[0]._00, Math.max(ans[0]._01, ans[0]._10))))%MOD;
    }
    return (int)res;
  }
}


// public class MaximumSumOfSubsequenceWithNonAdjacentElements {

//   private final static long MOD = (long)1e9+7;

//   public int maximumSumSubsequence(int[] nums, int[][] queries) {
//     long []dp = new long[nums.length];
//     Arrays.fill(dp, -1);
//     rec(0, dp, nums);
//     long res = 0;
//     for(int []q: queries){
//       nums[q[0]] = q[1];
//       Arrays.fill(dp, -1);
//       res = (res+rec(0, dp, nums))%MOD;
//     }
//     return (int)res;
//   }

//   private long rec(int idx, long []dp, int []nums){
//     if(idx>=nums.length) return 0L;
//     if(dp[idx] != -1) return dp[idx];
//     return dp[idx] = Math.max(rec(idx+1, dp, nums), Math.max(0, nums[idx])+rec(idx+2, dp, nums))%MOD;
//   }
// }