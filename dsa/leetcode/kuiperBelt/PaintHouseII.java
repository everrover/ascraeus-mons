package dsa.leetcode.KuiperBelt;

class PaintHouseII {
    /**
     * https://leetcode.com/problems/paint-house-ii/
     *
     * To paint all houses such that no two adjacent houses have the same color at minimum cost,
     * we use dynamic programming to keep track of the minimum costs. We iterate over each house
     * and for each color, we find the minimum cost of painting the previous house with any color
     * except the current one.
     *
     * TC: O(nk^2) SC: O(1)
     * #array #dynamic-programming #hard
     */
    public int minCostII(int[][] costs) {
        final int m = costs.length;
        if (m == 0) return 0;
        final int n = costs[0].length;
        if (n == 0) return 0;

        int first;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                first = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j) continue;
                    first = Integer.min(costs[i - 1][k], first);
                }
                costs[i][j] += first;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Integer.min(costs[m - 1][j], ans);
        }

        return ans;
    }
}