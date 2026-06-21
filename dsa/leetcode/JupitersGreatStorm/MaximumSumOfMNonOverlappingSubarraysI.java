package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-sum-of-m-non-overlapping-subarrays-i/description/
 *
 * DP with monotonic deque. Define dp[k][i] as the maximum sum achievable using at most k non-overlapping subarrays from index i onward. The recurrence is dp[k][i] = max(dp[k][i+1], max over j in [i+l, i+r] of (pref[j] - pref[i] + dp[k-1][j])). The inner maximization over j reduces to maximizing (pref[j] + dp[k-1][j]) in a sliding window of size [l, r], maintained with a monotonic deque in O(1) amortized per cell. A special case handles the "at least one" constraint when all candidates produce sum 0 — a brute-force scan over single subarrays ensures correctness.
 *
 * TC: O(m * n) SC: O(m * n)
 * #array #dynamic-programming #prefix-sum #monotonic-deque #sliding-window #hard
 */

class MaximumSumOfMNonOverlappingSubarraysI {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        long NEG = Long.MIN_VALUE / 2;

        long[][] dp = new long[m + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, NEG);

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];

        for (int i = 0; i <= n; i++) dp[0][i] = 0;

        long ans = NEG;

        for (int k = 1; k <= m; k++) {
            dp[k][n] = 0;
            Deque<Integer> dq = new ArrayDeque<>();

            for (int i = n - 1; i >= 0; i--) {
                if (i + l <= n) {
                    long curr = pref[i + l] + dp[k - 1][i + l];
                    while (!dq.isEmpty()) {
                        int b = dq.peekLast();
                        long val = pref[b] + dp[k - 1][b];
                        if (val >= curr) break;
                        dq.pollLast();
                    }
                    dq.addLast(i + l);
                }

                while (!dq.isEmpty() && dq.peekFirst() > i + r) dq.pollFirst();

                dp[k][i] = dp[k][i + 1];

                if (!dq.isEmpty()) {
                    int j = dq.peekFirst();
                    dp[k][i] = Math.max(dp[k][i], dp[k - 1][j] + pref[j] - pref[i]);
                }
            }
            ans = Math.max(ans, dp[k][0]);
        }

        if (ans == 0) {
            long b = NEG;
            for (int i = 0; i < n; i++)
                for (int len = l; len <= r && i + len <= n; len++)
                    b = Math.max(b, pref[i + len] - pref[i]);
            return b;
        }

        return ans;
    }
}
