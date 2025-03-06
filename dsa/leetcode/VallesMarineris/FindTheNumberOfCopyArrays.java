package dsa.leetcode.VallesMarineris;

public class FindTheNumberOfCopyArrays {
  
  /**
   * https://leetcode.com/problems/find-the-number-of-copy-arrays/description/
   * 
   * To solve the problem, we need to find consistent differences in the original array
   * while ensuring the values remain within specified bounds. We calculate valid ranges
   * for possible values of each element in the copy array and return the size of the valid
   * range for the last element.
   * 
   * TC: O(n) SC: O(1)
   * #array #math #medium
   */

  public int countArrays(int[] original, int[][] bounds) {
    int ub, vb, sb, eb, res;
    int af = bounds[0][0], bf = bounds[0][1];
    if(original.length <= 1) return original.length;
    res = bf - af + 1;
    
    for(int i = 1; i < original.length; i++){
      int diff = original[i] - original[i - 1];
      
      ub = bounds[i][0];
      vb = bounds[i][1];
      sb = af + diff;
      eb = bf + diff;
      
      res = Math.min(res, bf - af + 1);
      af = Math.max(ub, sb);
      bf = Math.min(vb, eb);
    }
    return Math.max(res, 0);
  }
}