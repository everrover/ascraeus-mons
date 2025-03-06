package dsa.leetcode.VallesMarineris;

public class FindTheNumberOfCopyArrays {
  
  /**
   * https://leetcode.com/problems/find-the-number-of-copy-arrays/description/
   * 
   * The elements in copy are linked by a diff.
   * diff = copy[i] - copy[i - 1] = original[i] - original[i - 1]
   * => copy[i] = copy[i - 1] + original[i] - original[i - 1] = copy[i - 1] + diff
   * 
   * The bounds for each element in copy are given. For first elements in copy, the bounds are the same as original.
   * i.e. af, bf = bounds[0][0], bounds[0][1] ... initally
   * 
   * For each subsequent element, the bounds are based on the diff and the bounds of the previous position.
   * i.e. sb = af + diff, eb = bf + diff
   * But since the actual bounds are given, the bounds for the current position are the intersection of the calculated bounds and the actual bounds.
   * i.e. af = max(ub, sb), bf = min(vb, eb)
   * 
   * Now for each initial element(from within the bound)... we can only have one sequence since it's dependent on 
   * each position's diff. So, if we start from first element, we can have at most bf - af + 1 sequences. This was 
   * visualized on the basis of an example. orig = [1,3,4,8], bounds = [[1,10], [2,9], [3,8], [4,7]]
   * 
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