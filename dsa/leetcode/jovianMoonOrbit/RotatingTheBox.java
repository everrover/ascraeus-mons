package dsa.leetcode.jovianMoonOrbit;

public class RotatingTheBox {
  /**
   * https://leetcode.com/problems/rotating-the-box/
   *
   * Simple brute-force dawgs... Rotate the box 90 degrees clockwise, store into another array
   * and then simulate the gravity by iterating from top to bottom and left to right.
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