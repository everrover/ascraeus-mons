package dsa.leetcode.kuiperBelt;

// imports here
public class LongestIncreasingPathInMatrix {

  /**
   * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
   * 
   * The solution involves Depth-First Search (DFS) with memoization to explore each cell's longest increasing path. Memoization helps avoid recomputation by storing results of subproblems, ensuring efficient execution.
   * 
   * TC: O(m * n) SC: O(m * n)
   * #array #dynamic-programming #depth-first-search #graph #memoization #hard
   */

  private static int [][]moves = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
  private int m, n;
  
  public int longestIncreasingPath(int[][] matrix) {
    m = matrix.length; 
    n = matrix[0].length; 
    int [][]dp = new int[m][n]; 
    int res = 0;
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(dp[i][j]==0) dfs(i,j,matrix,dp);
        res = Math.max(res, dp[i][j]);
      }
    }
    return res;
  }

  private int dfs(int i, int j, int [][]matrix, int [][]dp){
    if(dp[i][j] != 0) return dp[i][j];
    int res = 1;
    for(int []move: moves){
      int r = i+move[0], c = j+move[1];
      if(r<0 || c<0 || r>=m || c>=n || matrix[r][c]<=matrix[i][j]) continue;
      res = Math.max(res, 1 + dfs(r, c, matrix, dp));
    }
    return dp[i][j] = res;
  }
}