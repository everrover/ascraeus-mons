package dsa.leetcode.mithrim_montez;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/?envType=daily-question&envId=2025-01-28
 *
 * In this problem, we utilize Depth First Search (DFS) to explore all possible paths starting from each water cell. 
 * The goal is to accumulate the maximum number of fish by moving across connected water cells until no more adjacent
 * water cells are left. We iterate over each cell, initiating a DFS if it's a water cell and has not been visited yet.
 *
 * Disjoint sets could also be used to solve this problem. We can iterate over each cell, and if it's a water cell,
 * we can union it with its adjacent water cells. The maximum number of fish can be calculated by counting the number
 * of water cells in each disjoint set.
 * 
 * TC: O(m * n) SC: O(m * n)
 * #array #depth-first-search #matrix #medium #breadth-first-search #disjoint-set
 */

public class MaximumNumberOfFishInAGrid {

  public int maxFish(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    boolean[][] v = new boolean[m][n];
    int maxFish = 0;
    int[] dx = {1, -1, 0, 0}; // Directions for moving in 4 connected neighbours
    int[] dy = {0, 0, 1, -1};

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!v[i][j] && grid[i][j] > 0) {
          maxFish = Math.max(maxFish, dfs(grid, v, i, j, dx, dy));
        }
      }
    }
    return maxFish;
  }

  private int dfs(int[][] grid, boolean[][] v, int x, int y, int[] dx, int[] dy) {
    int m = grid.length;
    int n = grid[0].length;
    int currres = grid[x][y];
    v[x][y] = true;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && nx < m && ny >= 0 && ny < n && !v[nx][ny] && grid[nx][ny] > 0) {
        currres += dfs(grid, v, nx, ny, dx, dy);
      }
    }

    return currres;
  }

  // final int [][]dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

  // public int findMaxFish(int[][] grid) {
  //   final int m = grid.length, n = grid[0].length;
  //   int res = 0, currres = 0;
  //   boolean [][]v = new boolean[m][n];
  //   Queue<int[]> q = new LinkedList<>();
  //   for(int i=0; i<m; i++){
  //     for(int j=0; j<n; j++){
  //       if(v[i][j] || grid[i][j] == 0) continue;
  //       currres = 0;
  //       q.offer(new int[]{i,j});
  //       while(!q.isEmpty()){
  //         int []curr = q.poll();
  //         if(v[curr[0]][curr[1]]) continue;
  //         v[curr[0]][curr[1]] = true;
  //         currres += grid[curr[0]][curr[1]];
  //         for(int []dir: dirs){
  //           int x = curr[0]+dir[0],
  //               y = curr[1]+dir[1];
  //           if(x<0 || y<0 || x>=m || y>=n || v[x][y]) continue;
  //           if(grid[x][y]==0) continue;
  //           q.offer(new int[]{x,y});
  //         }
  //       }
  //       res = Math.max(res, currres);
  //     }
  //   }
  //   return res;
  // }
}