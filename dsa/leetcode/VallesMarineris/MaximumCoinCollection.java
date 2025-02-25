package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class MaximumCoinCollection {

    /**
     * https://leetcode.com/problems/maximum-coin-collection/description/
     *
     * Use dynamic programming with states: dp[i][lane][rem] = the maximum number of coins
     * you can obtain when you are at mile i in the given lane with rem switches remaining.
     *
     * TC: O(n) SC: O(n)
     *
     * #dynamic-programming #medium
     */
    
    private long dfs(int idx, int lane, int rem, int[] lane1, int[] lane2, long[][][] dp) {
        // base case
        if (idx == lane1.length) {
            return 0;
        }
        // use memoized result if available
        if (dp[idx][lane][rem] != Long.MIN_VALUE) {
            return dp[idx][lane][rem];
        }

        long pre = (lane == 1) ? lane1[idx] : lane2[idx];
        long res = pre;

        // no switch possible here
        res = Math.max(res, pre + dfs(idx + 1, lane, rem, lane1, lane2, dp));

        // check if lane switch is allowed
        if (rem > 0) { // can switch lanes
            if (lane == 1) {
                res = Math.max(res, pre + dfs(idx + 1, 2, rem - 1, lane1, lane2, dp));
            } else {
                res = Math.max(res, pre + dfs(idx + 1, 1, rem - 1, lane1, lane2, dp));
            }
        }

        return dp[idx][lane][rem] = res;
    }

    public long maxCoins(int[] lane1, int[] lane2) {
        int n = lane1.length;
        long[][][] dp = new long[n + 1][3][3];
        for (long[][] twoD : dp) {
            for (long[] oneD : twoD) {
                Arrays.fill(oneD, Long.MIN_VALUE);
            }
        }
        return Math.max(dfs(0, 1, 2, lane1, lane2, dp), dfs(0, 2, 1, lane1, lane2, dp));
    }
}