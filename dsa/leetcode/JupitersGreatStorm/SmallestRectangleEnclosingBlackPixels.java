package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class SmallestRectangleEnclosingBlackPixels {

  /**
   * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/?envType=weekly-question&envId=2025-04-22
   *
   * The basic idea is to use DFS to traverse the connected black pixels ('1') and keep track of the minimum and maximum coordinates.
   * This helps in determining the smallest rectangle that can enclose these black pixels.
   *
   * TC: O(n) SC: O(1) where n is the number of pixels
   * #array #dfs #matrix #hard
   */

  private int left, right, top, bottom;

  public int minArea(char[][] image, int x, int y) {
    left = y;
    right = y;
    top = x;
    bottom = x;
    dfs(image, x, y);
    return (right - left + 1) * (bottom - top + 1);
  }

  private void dfs(char[][] image, int x, int y) {
    if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != '1') return;
    image[x][y] = '0'; // Mark pixel as visited
    left = Math.min(left, y);
    right = Math.max(right, y);
    top = Math.min(top, x);
    bottom = Math.max(bottom, x);

    dfs(image, x + 1, y);
    dfs(image, x - 1, y);
    dfs(image, x, y + 1);
    dfs(image, x, y - 1);
  }
}