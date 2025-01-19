package dsa.leetcode.mithrim_montez;

import java.util.Arrays;

public class PaintHouseIV {

    /**
     * https://leetcode.com/problems/paint-house-iv/description/
     *
     * Each house can either be painted with color 0, 1, or 2. And we do the reqd iterations only fo the first half of the
     * houses. For any house i, we can paint it with color j only if the previous house i-1 was painted with color k(k!=j) and
     * for n-i-1 house we haven't painted it in color k. Built the rescursive impl around it and put memoization on top.
     * 
     * // ac = color of the previous house, bc = color of the previous house of the other half, idx = current house
     * dfs(idx, ac, bc) = min(cost[idx][i] + cost[n-idx-1][j] + dfs(idx+1, i, j)) for i,j in [colors] and i!=j and i!=ac and j!=bc
     *           a       = 0 if idx >= N
     *
     * TC: O(n) SC: O(n)
     * #dp #two-pointers #medium
     */
    
    private int N;

    public long minCost(int n, int[][] cost) {
        N = n / 2;
        long [][][] dp = new long[N][3][3];
        for (long[][] dd : dp) for (long[] d : dd) Arrays.fill(d, -1);
        long res = dfs(0, -1, -1, cost, dp);
        return res;
    }

    private long dfs(int idx, int ac, int bc, int[][] cost, long[][][] dp) {
        if (idx >= N) return 0;
        if (ac != -1 && bc != -1 && dp[idx][ac][bc] != -1) return dp[idx][ac][bc];
        long res = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j || i == ac || j == bc) continue;
                res = Math.min(res, cost[idx][i] + cost[cost.length - idx - 1][j] + dfs(idx + 1, i, j, cost, dp));
            }
        }
        if (ac != -1 && bc != -1) dp[idx][ac][bc] = res;
        return res;
    }
}