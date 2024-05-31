package dsa.leetcode.kuiperBelt;

import java.util.*;

class NumberOfDistinctIslands {
  /**
   * https://leetcode.com/problems/number-of-distinct-islands/
   * 
   * Simply hashed the path of each island and stored it in a set. If the path is already present in the set, we skip it.
   * Could've used a hash function to hash the path instead of using coordinates.
   * TC: O(n*m) SC: O(n*m)
   * #hash-table #depth-first-search #breadth-first-search #union-find #hash-function #medium
   */

  int[][] dirs= new int[][]{{1,0,1},{0,1,2},{-1,0,3},{0,-1,4}};
  public int numDistinctIslands(int[][] grid) {
    Set<String> set= new HashSet<>();
    int res=0;
    
    for(int i=0;i<grid.length;i++){
      for(int j=0;j<grid[0].length;j++){
        if(grid[i][j]==1) {
          StringBuilder sb= new StringBuilder();
          helper(grid,i,j,0,0, sb);
          String s=sb.toString();
          if(!set.contains(s)){
            res++;
            set.add(s);
          }
        }
      }
    }
    return res;
  }
  
  public  void helper(int[][] grid,int i,int j, int xpos, int ypos,StringBuilder sb){
    grid[i][j]=0;
    sb.append(xpos).append(':').append(ypos).append('-');
    for(int[] dir : dirs){
      int x=i+dir[0];
      int y=j+dir[1];
      if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==0) continue;
      // sb.append(dir[2]);
      helper(grid,x,y,xpos+dir[0],ypos+dir[1],sb);
    }
    // sb.append(0); // sentinel is important to mark the end of the path
  }
}