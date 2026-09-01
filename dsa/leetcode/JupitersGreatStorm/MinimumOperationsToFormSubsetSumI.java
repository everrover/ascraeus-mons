package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-operations-to-form-subset-sum-i/
 *
 * Each element's cost is decoupled from the others once we fix which target value it contributes (or whether it's excluded), so this is a knapsack-style "take/skip" DP over (remaining sum, index). Because every multiplication must precede every division for a given element, the only values ever worth reaching from x are the repeated-halving chain x, x/2, x/4, ... (cost = number of halvings) and the repeated-doubling chain x, 2x, 4x, ... capped below sum (cost = number of doublings) — any other order collapses to one of these via cancellation, so trying both chains for every element covers every useful contribution.
 *
 * dfs(nums, sum, idx) returns the minimum operations to hit exactly `sum` using a subset of nums[idx:], memoized on (sum, idx) since sum ranges only over [0, 5000]. At each index it branches over: skip nums[idx]; take nums[idx] unmodified; take it after 1..k halvings; take it after 1..k doublings (while the doubled value stays below sum, since overshooting can't help). The outer loop tries every index as the DP's starting point because the subset doesn't need to include earlier elements at all. Integer.MAX_VALUE denotes "unreachable" and is filtered out at the end to report -1.
 *
 * TC: O(n*sum) SC: O(n*sum)
 * #array #dynamic-programming #bit-manipulation #medium
 */

class MinimumOperationsToFormSubsetSumI {
    private int [][]dp = null;
    public int minOperations(int[] nums, int sum) {
        int res = Integer.MAX_VALUE;
        dp = new int[5001][nums.length+1];
        for(int []d: dp) Arrays.fill(d, -1);
        for(int i=0; i<nums.length; i++){
            res = Math.min(res, dfs(nums, sum, i));
        }
        return res>=(Integer.MAX_VALUE-101)?-1:res;
    }

    private int dfs(int []nums, int sum, int idx){
        if(sum == 0) return 0;
        if(idx == nums.length || sum < 0) return Integer.MAX_VALUE;
        if(dp[sum][idx] != -1) return dp[sum][idx];
        int res = Integer.MAX_VALUE;
        res = Math.min(res, dfs(nums, sum, idx+1));
        int tr = dfs(nums, sum-nums[idx], idx+1);
        if(tr != Integer.MAX_VALUE) res = Math.min(tr, res);

        int num = nums[idx];
        int div = 0, mul = 0;
        while(num>=1){
            div++; num/=2;
            tr = dfs(nums, sum-num, idx+1);
            if(tr != Integer.MAX_VALUE) res = Math.min(tr+div, res);
        }

        num = nums[idx];
        while(num < sum){
            mul++; num*=2;
            tr = dfs(nums, sum-num, idx+1);
            if(tr != Integer.MAX_VALUE) res = Math.min(tr+mul, res);
        }
        return dp[sum][idx] = res;
    }
}
