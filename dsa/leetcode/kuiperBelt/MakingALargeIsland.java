package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/making-a-large-island/
 * This solution marks each cell with the size of the island it belongs to and 
 * then checks every 0 cell to calculate the potential size of the island if it were to be changed to 1.
 * 
 * Could have made it more optimal with just one DFS and a union-find.
 * 
 * TC: O(n^2) SC: O(n^2)
 * #array #depth-first-search #breadth-first-search #union-find #matrix #hard #inverted-though-process
 */
class Solution {
  static int m, n;
  public int largestIsland(int[][] grid) {
    m = grid.length; n = grid[0].length;
    int res = -1;
    int [][]mark = new int[m][n];
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(mark[i][j] != 0 || grid[i][j] == 0) {
          int size = findSizes(mark, grid, i, j);
          colorTheGrid(mark, grid, i, j, size);
          res = Math.max(res, size);
        }
      }
    }
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
          res = Math.max(res, checkTheGrid(grid, i, j));
      }
    }
    return res;
  }
  
  private int findSizes(int [][]mark, int [][]grid, int i, int j){
    if(i<0 || i>=m || j<0 || j>=n || grid[i][j] == 0 || mark[i][j] == 1) return 0;
    int sz = 1;
    mark[i][j] = 1;
    sz += findSizes(mark, grid, i+1, j);
    sz += findSizes(mark, grid, i-1, j);
    sz += findSizes(mark, grid, i, j+1);
    sz += findSizes(mark, grid, i, j-1);
    return sz;
  }
  private void colorTheGrid(int [][]mark, int [][]grid, int i, int j, int sz){
    if(i<0 || i>=m || j<0 || j>=n || grid[i][j] == 0 || mark[i][j] != 1) return;
    mark[i][j] = 2;
    grid[i][j] = sz;
    colorTheGrid(mark, grid, i+1, j, sz);
    colorTheGrid(mark, grid, i-1, j, sz);
    colorTheGrid(mark, grid, i, j+1, sz);
    colorTheGrid(mark, grid, i, j-1, sz);
    return;
  }
  private int checkTheGrid(int [][]grid, int i, int j){
    if(grid[i][j] != 0) return 0;
    var res = 1;
    if(i<m-1) res += grid[i+1][j];
    if(i>0) res += grid[i-1][j];
    if(j<n-1) res += grid[i][j+1];
    if(j>0) res += grid[i][j-1];
    return res;
  }
}