package dsa.leetcode.EuropasOceanWorld;

public class RangeSumQuery2DImmutable {

    /**
     * https://leetcode.com/problems/range-sum-query-2d-immutable/description/?envType=problem-list-v2&envId=design
     *
     * Use a prefix sum matrix to calculate sumRegion efficiently. Construct a prefix sum matrix
     * such that pre[i][j] stores sum of elements from (0,0) to (i,j). For sumRegion, use 
     * a-b-c+d formula where a = pre[row2][col2], b = pre[row2][col1-1],
     * c = pre[row1-1][col2], d = pre[row1-1][col1-1].
     *
     * TC: O(m*n) for construction, O(1) for each sumRegion
     * SC: O(m*n)
     * #array #design #matrix #prefix-sum #medium
     */

    private int[][] pre;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        pre = new int[m][n];
        // Fill pre matrix with prefix sums
        pre[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) pre[i][0] = pre[i-1][0] + matrix[i][0];
        for (int j = 1; j < n; j++) pre[0][j] = pre[0][j-1] + matrix[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                pre[i][j] = matrix[i][j] + pre[i-1][j] + pre[i][j-1] - pre[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int a = pre[row2][col2];
        int b = col1 > 0 ? pre[row2][col1-1] : 0;
        int c = row1 > 0 ? pre[row1-1][col2] : 0;
        int d = row1 > 0 && col1 > 0 ? pre[row1-1][col1-1] : 0;
        return a - b - c + d;
    }
}