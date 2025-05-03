package dsa.leetcode.KuiperBelt;

// imports here
public class LongestIncreasingPathInMatrix {

  /**
   * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
   * 
   * The solution involves Depth-First Search (DFS) with memoization to explore each cell's longest increasing path. Since,
   * a node will be visited only once(1->2->1 isn't allowed so will not backtrack), we can store the result in a 2D array
   * to avoid recomputation.
   *
   * dp(i, j) = max(1 + dp(r,c)), where r,c are neighbors of i,j and matrix[r][c] > matrix[i][j]
   *          = 0, if i<0 or j<0 or i>=m or j>=n or matrix[i][j]<=matrix[i][j](already visited or not increasing)
   *
   * Topological sort is also possible, with indegree and outdegree calculation, but it's not as intuitive as DFS.
   *
   * TC: O(m * n) SC: O(m * n)
   * #array #dynamic-programming #depth-first-search #graph #memoization #hard #topological-sort
   *
   * Follow-up: Another approach? Yes. Using topo-sort. Can we use BFS? Yes.
   * Follow-up: What if strictly inc seq is not required? Yes, using topo-sort. And DFS too, but additional layers of DP
   * will be needed. To track already visited nodes.
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