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
// 14/4 = 3

// P A Y P A L I S H I R I N G = 14
// 1           1           1
//   2       2   2       2   2
//     3   3       3   3
//       4           4
   */
    public String convert(String s, int n) {
      char chs[] = s.toCharArray();
      StringBuilder sb = new StringBuilder("");
      int i, j, k, skip = n==1?1:2*(n-1), len=chs.length;
      i=0;
      while(i<len){
          sb.append(chs[i]);
          i+=skip;
      }
      i=1;
      
      while(i<n){
          j=0; k=skip;
          while(j<len){
              if ((j+i)<len) sb.append(chs[j+i]);
              if ((j+i) != (k-i) && (k-i)<len) sb.append(chs[k-i]);
              j=k;
              k+=skip;
          }
          i++;
      }
      return sb.toString();
  }
}