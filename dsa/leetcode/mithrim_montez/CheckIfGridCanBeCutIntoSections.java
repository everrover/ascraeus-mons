package dsa.leetcode.mithrim_montez;

import java.util.Arrays;

class Solution {
  /**
   * https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/description/
   *
   * To determine if a grid of size n x n can be split into sections with two cuts, consider sorting
   * the rectangles by x and y coordinates separately. Attempt to find positions where vertical and
   * horizontal cuts can leave at least one rectangle in each of the three sections formed by those cuts.
   *
   * TC: O(m log m) SC: O(m)
   * #array #sorting #geometry #medium
   */
  public boolean checkValidCuts(int n, int[][] rectangles) {
    int [][]x = new int[rectangles.length * 2][3];
    int [][]y = new int[rectangles.length * 2][3];
    
    for(int i = 0; i < rectangles.length; i++) {
      x[i * 2][0] = rectangles[i][0];
      x[i * 2 + 1][0] = rectangles[i][2];
      y[i * 2][0] = rectangles[i][1];
      y[i * 2 + 1][0] = rectangles[i][3];
      
      y[i * 2][1] = y[i * 2 + 1][1] = x[i * 2][1] = x[i * 2 + 1][1] = i;
      y[i * 2][2] = x[i * 2][2] = 1;
      y[i * 2 + 1][2] = x[i * 2 + 1][2] = 0;
    }
    
    Arrays.sort(x, (a, b) -> (a[0] == b[0]) ? (a[2] - b[2]) : (a[0] - b[0]));
    Arrays.sort(y, (a, b) -> (a[0] == b[0]) ? (a[2] - b[2]) : (a[0] - b[0]));
    
    // Implementation to check valid cuts goes here...

    return false; // placeholder for actual logic
  }
}