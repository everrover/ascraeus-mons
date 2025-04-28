package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountCoveredBuildings {

  /**
   * https://leetcode.com/problems/count-covered-buildings/description/
   *
   * Calculate covered buildings by iterating through each building and checking
   * if it has buildings on all four sides. This i did using a hash table to store
   * the max and min values of each row and column. 
   *
   * TC: O(n) SC: O(n)
   * #array #hash-table #sorting #medium
   */

   private static class T {
    public int max = -1, min = Integer.MAX_VALUE;
  }
  final int sz = (int)1e5;
  public int countCoveredBuildings(int n, int[][] buildings) {
    T [] rows = new T[n+1];
    T [] cols = new T[n+1];
    for(int i=0; i<=n; i++) {
      rows[i] = new T();
      cols[i] = new T();
    }
    for(int []b: buildings){
      int r = b[0], c = b[1];
      rows[c].max = Math.max(rows[c].max, r);
      rows[c].min = Math.min(rows[c].min, r);
      cols[r].max = Math.max(cols[r].max, c);
      cols[r].min = Math.min(cols[r].min, c);
    }
    int res = 0;
    for(int []b: buildings){
      int r = b[0], c = b[1];
      T col = cols[r], row = rows[c];
      if(col.max > c && c > col.min && row.max > r && r > row.min) res++;
    }
    return res;
  }
}