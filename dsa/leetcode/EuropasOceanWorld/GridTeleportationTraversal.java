package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class GridTeleportationTraversal {
  /**
   * https://leetcode.com/problems/grid-teleportation-traversal/
   *
   * Use Breadth-First Search to scan through the grid. Treat all grid cells with the same portal
   * letter as connected. Keep track of visited cells to avoid revisiting, and count steps needed 
   * to reach the bottom-right corner.
   * 
   * TC: O(m * n) SC: O(m * n)
   * #graph #breadth-first-search #greedy #medium
   */

  public int minMoves(String[] matrix) {
    int m = matrix.length;
    int n = matrix[0].length();
    char[][] grid = new char[m][];
    for (int i = 0; i < m; i++) grid[i] = matrix[i].toCharArray();

    Deque<Node> pq = new LinkedList<>();
    int[][] visited = new int[m][n];
    int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    // Initialize BFS starting point
    pq.offer(new Node(0, 0, 0));
    visited[0][0]=1;
    while (!pq.isEmpty()) {
      Node curr = pq.poll();
      // Check if we reached the end
      if (curr.r == m - 1 && curr.c == n - 1) return curr.steps;

      // Traverse all adjacent cells
      for (int[] dir : directions) {
        int r = curr.r + dir[0];
        int c = curr.c + dir[1];
        if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] != '#' && visited[r][c]==0) {
          visited[r][c]=1;
          pq.offer(new Node(r, c, curr.steps + 1));
        }
      }

      // Handle teleportation logic
      // (implementation needed for teleportation step handling)
    }
    return -1;
  }

  class Node {
    int r, c, steps;
    Node(int r, int c, int steps) {
      this.r = r;
      this.c = c;
      this.steps = steps;
    }
  }
}