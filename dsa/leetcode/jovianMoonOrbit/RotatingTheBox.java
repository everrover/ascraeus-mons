package dsa.leetcode.jovianMoonOrbit;

public class Solution {
  /**
   * https://leetcode.com/problems/rotating-the-box/
   *
   * The solution involves rotating the box 90 degrees clockwise and then letting the stones fall due
to gravity. Start by rotating the matrix, then iterate from the bottom to let the stones ('#') 
fall into place, bypassing obstacles ('*').
   *
   * TC: O(m*n) SC: O(m*n)
   * #array #matrix #twopointers #medium 
   */

  public char[][] rotateTheBox(char[][] box) {
    int rs = box.length, cs = box[0].length;
    char[][] res = new char[cs][rs];
    // Rotate the box 90 degrees clockwise
    for (int i = 0; i < rs; i++) {
      for (int j = 0; j < cs; j++) {
        res[j][rs - i - 1] = box[i][j];
      }
    }
    // Make the stones fall due to gravity
    for (int i = 0; i < rs; i++) {
      int j = 0, s = 0;
      while (j <= cs) {
        if (j == cs || res[j][i] == '*') {
          int st = s;
          while (st-- > 0) {
            res[j - st - 1][i] = '#';
          }
          s = 0;
        } else if (res[j][i] == '#') {
          s++;
          res[j][i] = '.';
        }
        j++;
      }
    }
    return res;
  }
}