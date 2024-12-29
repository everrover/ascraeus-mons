package dsa.leetcode.fermi_s_paradox;

public class MinimumOperationsToMakeColumnsStrictlyIncreasing {
  /**
   * https://leetcode.com/problems/minimum-operations-to-make-columns-strictly-increasing/description/
   * 
   * For each column, iterate through the rows; 
   * if an element in the current row is not greater than the element in the previous row, 
   * increase it to be strictly greater. Count the operations needed.
   * 
   * TC: O(m * n) SC: O(1)
   * #matrix #greedy #easy
   */
  
  public int minimumOperations(int[][] grid) {
    int res = 0;
    for(int i = 0; i < grid[0].length; i++){
      for(int j = 1; j < grid.length; j++){
        if(grid[j][i] <= grid[j-1][i]) {
          // Increment grid[j][i] to be greater than grid[j-1][i]
          res += (grid[j-1][i] - grid[j][i] + 1);
          grid[j][i] = grid[j-1][i] + 1;
        }
      }
    }
    return res;
  }
}