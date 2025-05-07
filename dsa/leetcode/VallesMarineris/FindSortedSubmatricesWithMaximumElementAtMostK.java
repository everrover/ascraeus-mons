package dsa.leetcode.VallesMarineris;

/**
 * https://leetcode.com/problems/find-sorted-submatrices-with-maximum-element-at-most-k/
 *
 * Use a monotonic stack to keep track of the longest increasing subarray
 * of each row that can be extended on a given column.
 * Iterate through each row checking conditions for forming submatrices.
 *
 * TC: O(m * n) SC: O(m * n)
 * #matrix #monotonic-stack #array #hard
 */

public class FindSortedSubmatricesWithMaximumElementAtMostK {
  
  public long countSubmatrices(int[][] grid, int k) {
    final int m = grid.length, n = grid[0].length;
    long [][]inc = new long[n][m];
    for(int i=0; i<m; i++) inc[n-1][i] = grid[i][n-1]<=k? 1:-1;
    long res = 0;
    for(int i=0; i<m; i++){
      for(int j=n-2; j>=0; j--){
        if(grid[i][j] > k) {
          inc[j][i] = -1;
        } else if(grid[i][j+1] <= k && grid[i][j] >= grid[i][j+1]) {
          inc[j][i] = inc[j+1][i]+1;
        } else {
          inc[j][i] = 1;
        }
        for (int l = 0; l < m; l++) {
          if (inc[j][l] != -1) res += inc[j][l];
        }
      }
    }
    return res;
  }
}