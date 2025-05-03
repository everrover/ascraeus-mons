package dsa.leetcode.KuiperBelt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-path-to-get-food/
 * The solution employs BFS to find the shortest path from the start location to the nearest food.
 * Following each move, it checks if the next step is a valid grid, not visited, and not an obstacle.
 *
 * TC: O(m*n) SC: O(m*n)
 * #breadth-first-search #matrix #medium
 */
public class ShortestPathToGetFood {

  private final static int[][] moves = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

  private static class T{
    public int x, y, path;
    public T(int x,int y, int path){
      this.x = x;
      this.y = y;
      this.path = path;
    }
  }

  public int getFood(char[][] grid) {
    Queue<T> q = new LinkedList<>();
    int m = grid.length, n = grid[0].length;
    for(int i=0; i<m; i++)
      for(int j=0; j<n; j++)
        if(grid[i][j] == '*') { q.offer(new T(i,j,0)); break; }
    boolean [][]v = new boolean[m][n];
    while(!q.isEmpty()){
      T p = q.poll();
      if(v[p.x][p.y] || grid[p.x][p.y] == 'X') continue;
      if(grid[p.x][p.y] == '#') return p.path;
      v[p.x][p.y] = true;
      for(int []move: moves){
        int i=p.x+move[0], j = p.y+move[1];
        if(i<0 || i>=m || j<0 || j>=n || v[i][j]) continue;
        q.offer(new T(i, j, p.path+1));
      }
    }
    return -1;
  }
}