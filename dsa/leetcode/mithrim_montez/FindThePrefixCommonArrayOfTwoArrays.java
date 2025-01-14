package dsa.leetcode.mithrim_montez;

public class FindThePrefixCommonArrayOfTwoArrays {
  /**
   * https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/?envType=daily-question&envId=2025-01-14
   *
   * If the number in A at i is there in B[0...i], increment the count. Either we check presence using a hash-table
   * or bit-mask since constraints are small. Similarly for B.
   *
   * TC: O(n) SC: O(1)
   * #array #hash-table #bit-manipulation #medium
   */
  
  public int[] findThePrefixCommonArray(int[] A, int[] B) {
    long h1 = 0, h2 = 0;
    int n = A.length, cnt = 0;
    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
      h1 = h1 | (1L << A[i]);
      h2 = h2 | (1L << B[i]);
      if (A[i] == B[i]) cnt++;
      else {
        if ((h1 & (1L << B[i])) >= 1) cnt++;
        if ((h2 & (1L << A[i])) >= 1) cnt++;
      }
      res[i] = cnt;
    }
    return res;
  }
}