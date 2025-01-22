package dsa.leetcode.mithrim_montez;

// Import required for using Queue
import java.util.LinkedList;
import java.util.Queue;

public class MapOfHighestPeak {
  /**
   * https://leetcode.com/problems/map-of-highest-peak/
   *
   * Perform a multi-source BFS starting from all water cells with height 0. 
   * Traverse each cell's neighbors to determine their heights by incrementally increasing the distance from water 
   * or the height of the land cell.
   * 
   * Each cell's height is determined by the adjacent cell with the minimum height. LR and TB followed by RL and BT iterations work as well.
   * Determined using walking through a few examples.
   *
   * TC: O(mn) SC: O(mn)
   * #array #bfs #matrix #medium #greedy
   */

  private final int M = 2 * (int) 1e8;

  public int[][] highestPeak(int[][] isWater) {
    final int m = isWater.length, n = isWater[0].length;
    int [][] res = new int[m][n];
    Queue<int[]> queue = new LinkedList<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (isWater[i][j] == 1) {
          res[i][j] = 0;
          queue.offer(new int[]{i, j});
        } else {
          res[i][j] = M;
        }
      }
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int x = cell[0], y = cell[1];

      for (int[] dir : directions) {
        int nx = x + dir[0], ny = y + dir[1];

        if (nx >= 0 && ny >= 0 && nx < m && ny < n && res[nx][ny] == M) {
          res[nx][ny] = res[x][y] + 1;
          queue.offer(new int[]{nx, ny});
        }
      }
    }

    return res;
  }

  // BFS version 2
  public int[][] highestPeakV2(int[][] isWater) {
    int m = isWater.length, n = isWater[0].length;
    Queue<int[]> q = new LinkedList<>();
    boolean [][]v = new boolean[m][n];
    boolean isWaterPresent = true;
    for(int i = 0; i < m; i++) for(int j = 0; j < n; j++) {
      if(isWater[i][j] == 1) {
        isWaterPresent = true;
        isWater[i][j] = 0;
        q.offer(new int[]{i, j});
      }else{
        isWater[i][j] = Integer.MAX_VALUE;
      }
    }
    // if no water source present, then start from the center, to get max height sum
    // condition is to have an absolute diff of 1 bw. adjacent cells but it works as well
    // since any one of the valid solution is acceptable
    if(!isWaterPresent) q.offer(new int[]{m/2,n/2});
    final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      if(v[curr[0]][curr[1]]) continue;
      v[curr[0]][curr[1]] = true;
      int x = curr[0], y = curr[1];
      for(int[] dir : dirs) {
        int nx = x + dir[0], ny = y + dir[1];
        if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
        if(!v[nx][ny]){
          // to maintain a max absolute diff of 1 bw. adjacent cells
          isWater[nx][ny] = Math.min(isWater[x][y] + 1, isWater[nx][ny]);
          q.offer(new int[]{nx, ny});
        }
      }
    }
    return isWater;
  }
}