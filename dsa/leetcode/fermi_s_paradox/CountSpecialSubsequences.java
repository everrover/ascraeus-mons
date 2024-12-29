package dsa.leetcode.fermi_s_paradox;

import java.util.Arrays;

public class CountSpecialSubsequences {
  private static int [][]gcdMemo;

  /**
   * https://leetcode.com/problems/count-special-subsequences/
   *
   * Count pairs where nums[p] / nums[q] equals nums[s] / nums[r],
   * using GCD to handle ratios efficiently.
   *
   * TC: O(n^2) SC: O(n^2)
   * #array #mathematical #medium #math #gcd
   */

  int memoizedGCD(int a, int b) {
    if (gcdMemo[a][b] != -1) return gcdMemo[a][b];
    if (a == 0) return b;
    if (b == 0) return a;
    return gcdMemo[a][b] = memoizedGCD(b, a % b);
  }

  private int[] gcdfactors(int x, int y) {
    int g = memoizedGCD(x, y);
    return new int[]{x / g, y / g};
  }

  public long numberOfSubsequences(int[] nums) {
    if(gcdMemo == null) {
      gcdMemo = new int[1001][1001];
      for(int []g: gcdMemo) Arrays.fill(g,-1);
    }
    long res = 0;
    int [][]map = new int[1001][1001];
    int p=0, q=0, r=0, s=0;
    for(r=0; r<nums.length; r++){
      for(; q<r-1; q++){
        for(p=0; p<q-1; p++){
          int []f = gcdfactors(nums[p], nums[q]) ;
          map[f[0]][f[1]]++;
        }
      }
      for(s = r + 2; s < nums.length; ++s) {
        int []f = gcdfactors(nums[r], nums[s]);
        res += map[f[0]][f[1]];
      }
    }
    return res;
  }
}