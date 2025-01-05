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
   * Basically, `visited` array is implemented as a set of minimum distances to each room.
   * If we see a room with a distance less than the current distance, we skip it. Because a better
   * path has already been found.
   *
   * TC: O(m * n) SC: O(m * n)
   * #array #bfs #matrix #medium
   */

  private static class T {
    int x, y, dist;
    T(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }

  final int [][]dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};

  public void wallsAndGates(int[][] rooms) {
    
    final int m = rooms.length, n = rooms[0].length;
    Queue<T> q = new LinkedList<>(); // thought of using a priority queue, but it increased the complexity to O(m*n*log(m*n))
    // and hence rcv a TLE
    int [][]v = new int[m][n];
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(rooms[i][j] == -1) v[i][j] = -1;
        else v[i][j] = Integer.MAX_VALUE;
        if(rooms[i][j] == 0) q.offer(new T(i, j, 0));
      }
    }
    while(!q.isEmpty()){
      T curr = q.poll();
      if(v[curr.x][curr.y] <= curr.dist) continue;
      v[curr.x][curr.y] = curr.dist;
      for(int []dir: dirs){
        int nx = curr.x+dir[0], ny = curr.y+dir[1];
        if(nx<0 || nx >= m || ny < 0 || ny >=n || v[nx][ny] == -1 || v[nx][ny] <= curr.dist+1) continue;
        q.offer(new T(nx, ny, curr.dist+1));
      }
    }
    
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        rooms[i][j] = v[i][j];
      }
    }
  }
}