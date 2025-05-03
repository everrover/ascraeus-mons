package dsa.leetcode.JovianMoonOrbit;

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
   * I saw people using binary search over solution space and apply BFS/Dijkstra's algorithm to check if it's possible to reach the end cell within the given time.
   * 
   * How is this problem even hard?
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
      if (t.y > 0 && !mark[t.x][t.y - 1]) pq.offer(new T(t.x, t.y - 1, grid[t.x][t.y - 1]));
      if (t.y < m - 1 && !mark[t.x][t.y + 1]) pq.offer(new T(t.x, t.y + 1, grid[t.x][t.y + 1]));
      if (t.x > 0 && !mark[t.x - 1][t.y]) pq.offer(new T(t.x - 1, t.y, grid[t.x - 1][t.y]));
      if (t.x < n - 1 && !mark[t.x + 1][t.y]) pq.offer(new T(t.x + 1, t.y, grid[t.x + 1][t.y]));
      if (t.x == m - 1 && t.y == n - 1) break;
    }
    return res;
  }

  /**
   * // Binary search solution - CPP
   * class Solution {
    public:
        int swimInWater(vector<vector<int>>& grid) {
            int n = grid.size();
            int low = grid[0][0], hi = n*n-1;
            while (low < hi) {
                int mid = low + (hi-low)/2;
                if (valid(grid, mid)) 
                  hi = mid;
                else
                  low = mid+1;
            }
            return low;
        }
    private:
        bool valid(vector<vector<int>>& grid, int waterHeight) {
            int n = grid.size();
            vector<vector<int>> visited(n, vector<int>(n, 0));
            vector<int> dir({-1, 0, 1, 0, -1});
            return dfs(grid, visited, dir, waterHeight, 0, 0, n);
        }
        bool dfs(vector<vector<int>>& grid, vector<vector<int>>& visited, vector<int>& dir, int waterHeight, int row, int col, int n) {
            visited[row][col] = 1;
            for (int i = 0; i < 4; ++i) {
                int r = row + dir[i], c = col + dir[i+1];
                if (r >= 0 && r < n && c >= 0 && c < n && visited[r][c] == 0 && grid[r][c] <= waterHeight) {
                    if (r == n-1 && c == n-1) return true;
                    if (dfs(grid, visited, dir, waterHeight, r, c, n)) return true;
                }
            }
            return false;
        }
    };
   */
}