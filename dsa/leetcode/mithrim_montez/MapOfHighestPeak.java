package dsa.leetcode.mithrim_montez;

// Import required for using Queue
import java.util.LinkedList;
import java.util.Queue;

public class MapOfHighestPeak {
  /**
   * https://leetcode.com/problems/map-of-highest-peak/
   *
   * Perform a multi-source BFS starting from all water cells with height 0. Traverse each cell's neighbors to determine their heights by incrementally increasing the distance from water.
   *
   * TC: O(mn) SC: O(mn)
   * #array #bfs #matrix #medium
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
}