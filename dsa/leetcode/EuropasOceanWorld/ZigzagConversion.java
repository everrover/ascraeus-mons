package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class ZigzagConversion {

  /** 
   * https://leetcode.com/problems/zigzag-conversion/
   *
   * Converts a given string into a zigzag pattern with a specified number of rows.
   * Utilizes traversal and appending of characters to form the pattern across rows.
   *
   * TC: O(n) SC: O(n)
   * #string #pattern #medium
   */

  public String convert(String s, int numRows) {
    if (numRows == 1) return s;
    StringBuilder sb = new StringBuilder();
    int len = s.length();
    int skip = 2 * (numRows - 1);

    for (int i = 0; i < numRows; i++) {
      int j = 0;
      while (j < len) {
        if ((j + i) < len) sb.append(s.charAt(j + i));
        if (i != 0 && i != numRows - 1 && (j + skip - i) < len) sb.append(s.charAt(j + skip - i));
        j += skip;
      }
    }
    return sb.toString();
  }
}