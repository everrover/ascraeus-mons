package dsa.leetcode.mithrim_montez;

import java.util.*;

public class WallsAndGates {

  /**
   * https://leetcode.com/problems/walls-and-gates/description/
   *
   * Iterate through the grid and perform a Breadth-First Search (BFS) starting from each gate (0).
   * Update the distance in each room according to the BFS layer.
   * If there's no reachable gate, the room remains Infinity.
   *
   * TC: O(m * n) SC: O(m * n)
   * #array #bfs #matrix #medium
   */

  final int [][]dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};

  public void wallsAndGates(int[][] rooms) {
    final int m = rooms.length, n = rooms[0].length;
    Queue<T> q = new LinkedList<>();
    int [][]v = new int[m][n];

    // Initialize rooms distances and queue with gates
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        if(rooms[i][j] == -1) v[i][j] = -1;
        else v[i][j] = Integer.MAX_VALUE;
        if(rooms[i][j] == 0) q.offer(new T(i, j, 0));
      }
    }

    // Process BFS from all gates
    while(!q.isEmpty()){
      T curr = q.poll();
      if(v[curr.x][curr.y] <= curr.dist) continue;
      v[curr.x][curr.y] = curr.dist;
      for(int[] dir : dirs){
        int x = curr.x + dir[0];
        int y = curr.y + dir[1];
        if(x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] != -1){
          q.offer(new T(x, y, curr.dist + 1));
        }
      }
    }
  }

  class T {
    int x, y, dist;
    T(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }
}