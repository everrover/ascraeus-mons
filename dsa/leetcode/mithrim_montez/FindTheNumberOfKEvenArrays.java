package dsa.leetcode.mithrim_montez;

public class FindTheNumberOfKEvenArrays {

    /**
     * https://leetcode.com/problems/find-the-number-of-k-even-arrays/description/
     *
     * Use dynamic programming to calculate the number of k-even arrays.
     * The function keeps track of whether the last element was even or odd and stores intermediate results.
     * 
     * Earlier thought of tracking the previous element, but that was not necessary since 
     * k-even condition is satisfied if both the previous and current elements are even.
     * 
     * a*b-a-b => a*b is even, -(a+b) is even, even if both a and b are even
     *         => a*b is even, -(a+b) is odd, odd if either a or b is odd
     *         => a*b is odd, -(a+b) is even, odd if both a and b are odd
     *
     * TC: O(n*m*k), SC: O(n*k)
     * #dynamic-programming #medium #combinatorics
     */

    private long[][][] dp;
    private long N = 0, M = 0, K = 0;
    private static final int MOD = (int)1e9+7;

    private long dfs(int idx, int kdx, int prevEven) {
        if (idx >= N) return kdx == K ? 1 : 0;
        if (kdx > K) return 0; // exactly K even numbers are required
        if (dp[prevEven][idx][kdx] != -1) return dp[prevEven][idx][kdx];

        long currEven = (M / 2L * dfs(idx + 1, kdx + prevEven, 1)) % MOD;
        long currOdd = ((M + 1) / 2L * dfs(idx + 1, kdx, 0)) % MOD;

        return dp[prevEven][idx][kdx] = (currEven + currOdd) % MOD;
    }

    public int countOfArrays(int n, int m, int k) {
        this.N = n;
        this.M = m;
        this.K = k;
        this.dp = new long[2][n][k + 1];

        for (long[][] d1 : dp)
            for (long[] d2 : d1)
                java.util.Arrays.fill(d2, -1);

        return (int) dfs(0, 0, 0);
    }
}