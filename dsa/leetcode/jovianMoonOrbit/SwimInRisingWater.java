package dsa.leetcode.jovianMoonOrbit;

// imports here
import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {

  /**
   * https://leetcode.com/problems/swim-in-rising-water/
   *
   * Keep a min-heap to always expand the smallest possible current time.
   * Pop the smallest element in the heap, and push all its unvisited neighbors into the heap.
   * Repeat until we reach the bottom-right cell.
   *
   * TC: O(n^2 log n) SC: O(n^2)
   * #array #binary-search #dfs #bfs #union-find #heap #matrix #hard
   */

  private static class T {
    public int x, y, ht;
    public T(int x, int y, int ht) {
      this.x = x;
      this.y = y;
      this.ht = ht;
    }
  }

  public int swimInWater(int[][] grid) {
    int m = grid.length, n = grid[0].length, res = Integer.MIN_VALUE;
    boolean[][] mark = new boolean[m][n];
    Queue<T> pq = new PriorityQueue<>((a, b) -> a.ht - b.ht);
    pq.offer(new T(0, 0, grid[0][0]));
    while (!pq.isEmpty()) {
      T t = pq.poll();
      res = Math.max(res, t.ht);
      mark[t.x][t.y] = true;
      // check and push left neighbor
      if (t.y > 0 && !mark[t.x][t.y - 1]) pq.offer(new T(t.x, t.y - 1, grid[t.x][t.y - 1]));
      // check and push right neighbor
      if (t.y < m - 1 && !mark[t.x][t.y + 1]) pq.offer(new T(t.x, t.y + 1, grid[t.x][t.y + 1]));
      // check and push upper neighbor
      if (t.x > 0 && !mark[t.x - 1][t.y]) pq.offer(new T(t.x - 1, t.y, grid[t.x - 1][t.y]));
      // check and push bottom neighbor
      if (t.x < n - 1 && !mark[t.x + 1][t.y]) pq.offer(new T(t.x + 1, t.y, grid[t.x + 1][t.y]));
      if (t.x == m - 1 && t.y == n - 1) break;
    }
    return res;
  }
}