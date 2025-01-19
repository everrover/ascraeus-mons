package dsa.leetcode.mithrim_montez;

import java.util.*;

public class MinimumCostToMakeAtLeastOneValidPathInAGrid {

  /**
   * https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/
   *
   * To solve this problem efficiently, we use a modified BFS approach where we traverse the grid, treating it as a graph.
   * The edges in this graph have weights determined by whether they maintain the current direction or require a change.
   * Start from the top-left cell and attempt to reach the bottom-right cell, pushing edges with zero weight first to
   * ensure minimal cost moves are prioritized.
   *
   * TC: O(m * n) SC: O(m * n)
   * #array #bfs #graph #priority-queue #matrix #hard
   */

  public int minCost(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Deque<T> pq = new ArrayDeque<>();
    int[][] v = new int[m][n];
    for(int[] vv: v) Arrays.fill(vv, Integer.MAX_VALUE);
    pq.offerFirst(new T(0, 0, 0));
    while(!pq.isEmpty()){
      T curr = pq.pollFirst();
      if(v[curr.x][curr.y] <= curr.c) continue;
      v[curr.x][curr.y] = curr.c;
      for(int i = 0; i < dirs.length; i++){
        int[] dir = dirs[i];
        int nx = curr.x + dir[0], ny = curr.y + dir[1];
        if(nx >= m || nx < 0 || ny >= n || ny < 0) continue;
        int newc = (grid[curr.x][curr.y] == (i+1) ? 0 : 1);
        if(v[nx][ny] <= newc) continue;
        T news = new T(nx, ny, newc + curr.c);
        if(newc == 1) pq.offerLast(news);
        else pq.offerFirst(news);
      }
    }
    return v[m-1][n-1];
  }

  static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  static class T {
    int x, y, c;
    T(int x, int y, int c) {
      this.x = x;
      this.y = y;
      this.c = c;
    }

    @Override
    public String toString() {
      return this.x + ":" + this.y + ":" + this.c;
    }
  }

}