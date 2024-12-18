package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class FindMinimumTimeToReachLastRoomIi {
  /**
   * https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/
   *
   * Use shortest path algorithms with a state for the last move being odd or even indexed.
   *
   * TC: O(n * m * log(n * m)) SC: O(n * m)
   * #array #graph #heap #matrix #shortest-path #medium
   */
  
  public int minTimeToReach(int[][] moveTime) {
    final int m = moveTime.length;
    final int n = m == 0 ? 0 : moveTime[0].length;
    if (m == 0 || n == 0) return 0;
    Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
    int[][] dp = new int[m][n];
    for (int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
    pq.offer(new int[]{0, 0, 0, 0});
    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      if (curr[0] == m - 1 && curr[1] == n - 1) return curr[2];
      else if (dp[curr[0]][curr[1]] <= curr[2]) continue;
      dp[curr[0]][curr[1]] = curr[2];
      for (int[] dir : dirs) {
        int r = curr[0] + dir[0], c = curr[1] + dir[1];
        if (r < 0 || r >= m || c < 0 || c >= n) continue;
        int wait = (curr[3] % 2 == 0 ? 1 : 2) + Math.max(moveTime[r][c], curr[2]);
        if (dp[r][c] > wait) {
          pq.offer(new int[]{r, c, wait, curr[3] + 1});
        }
      }
    }
    return -1;
  }

  private final static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}