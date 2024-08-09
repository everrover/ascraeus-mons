package dsa.leetcode.kuiperBelt;

import java.util.PriorityQueue;

public class TrappingRainWaterII {

  /**
   * https://leetcode.com/problems/trapping-rain-water-ii/
   *
   * Given a 2D elevation map, we use a priority queue to simulate
   * the water flow starting from the boundaries moving inwards. By
   * always exploring the lowest cell, we ensure that water traps
   * correctly.
   * 
   * TC: O(m*n*log(m*n)) SC: O(m*n)
   * #array #bfs #heap #priority-queue #matrix #hard
   */

  private static class T {
    public int r, c, h;
    public T(int r, int c, int h) {
      this.r = r;
      this.c = c;
      this.h = h;
    }
  }

  public int trapRainWater(int[][] hm) {
    PriorityQueue<T> pq = new PriorityQueue<>((a, b) -> a.h - b.h);
    int N = hm.length, M = hm[0].length;
    boolean[][] v = new boolean[N][M];

    for (int col = 0; col < M; col++) {
      pq.add(new T(0, col, hm[0][col]));
      pq.add(new T(N - 1, col, hm[N - 1][col]));
      v[0][col] = true;
      v[N - 1][col] = true;
    }

    for (int row = 0; row < N; row++) {
      pq.add(new T(row, 0, hm[row][0]));
      pq.add(new T(row, M - 1, hm[row][M - 1]));
      v[row][0] = true;
      v[row][M - 1] = true;
    }

    int res = 0, max = -1;
    int[][] ds = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    while (!pq.isEmpty()) {
      T curr = pq.poll();
      max = Math.max(max, curr.h);
      res += max - curr.h;

      for (int[] d : ds) {
        int nr = curr.r + d[0], nc = curr.c + d[1];
        if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc]) {
          v[nr][nc] = true;
          pq.add(new T(nr, nc, hm[nr][nc]));
        }
      }
    }
    return res;
  }
}