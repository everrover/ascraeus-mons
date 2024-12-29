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
   * Earlier thought of storing these fractions in a 2D map linked to 
   * the list of indexes where they occur. And apply binary search to find
   * the count of fractions that match the current fraction.
   * for(int p=0; i<nums.length; i++){
   *  for(int q=i+2; j<nums.length; j++){
   *   int []f = gcdfactors(nums[i], nums[j]);
   *   if(map[f[0]][f[1]] != null){ map[f[0]][f[1]].add(j); }
   *   else{ map[f[0]][f[1]] = new ArrayList<>(); map[f[0]][f[1]].add(j); }
   *  }
   * }
   * // sort all the lists in the map
   * for(int i=nums.length-1; i>=0; i--){
   *  for(int j=i-2; j>=0; j--){
   *   int []f = gcdfactors(nums[i], nums[j]);
   *   if(map[f[0]][f[1]] != null){
   *    int idx = Collections.binarySearch(map[f[0]][f[1]], j-2);
   *    if(idx >= 0){
   *     res += map[f[0]][f[1]].size() - idx;
   *    }
   *   }
   *  }
   * }
   * 
   * But, it's O(n^2 log n) and not efficient as the approach mentioned below.
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
    // why starting from r?
    // because for a given r, we can find all the pairs of p,q that can be formed
    // came to me at the last moment
    for(r=0; r<nums.length; r++){
      for(; q<r-1; q++){ // for a given p,q pair we don't need to recompute the fractions again
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