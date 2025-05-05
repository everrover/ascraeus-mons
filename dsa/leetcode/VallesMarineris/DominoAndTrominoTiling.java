package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class DominoAndTrominoTiling {

  /**
   * https://leetcode.com/problems/domino-and-tromino-tiling/description/?envType=daily-question&envId=2025-05-05
   *
   * IF we use a domino, we can place it in two ways: |(add 1) or =(add 2)
   * IF we use a tromino, we can place it in two ways: L(add 2) or it's diagonal mirror (add 2)
   * 
   * Using the dfs approach below, we place either one domino or one tromino at each step. That's why partial case is like that.
   * Because when it's called, we already placed one tromino and need to place either one domino at `idx-2` or one tromino at `idx-1`.
   *
   * TC: O(n), SC: O(n)
   * #dynamic-programming #tiling #medium
   */

  final int MOD = (int)1e9+7;
  public int numTilings(int n) {
    int res = 0;
    long []dpf = new long[1001];
    long []dpp = new long[1001];
    Arrays.fill(dpf, -1);
    Arrays.fill(dpp, -1);
    return (int)dfs_f(n, dpf, dpp, n);
  }
  private long dfs_f(int idx, long []dpf, long []dpp, final int n){
    if(idx < 0) return 0;
    if(dpf[idx] != -1) return dpf[idx]; 
    long res = 0;
    if(idx == 1) res = 1L;
    else if(idx == 2) res = 2L;
    else res = (
      res + 
      dfs_f(idx-1, dpf, dpp, n) + 
      dfs_f(idx-2, dpf, dpp, n) + 
      2 * dfs_p(idx-1, dpf, dpp, n)
    ) % MOD;
    return dpf[idx] = res;
  }
  private long dfs_p(int idx, final long []dpf, final long []dpp, final int n){
    if(idx < 0) return 0;
    if(dpp[idx] != -1) return dpp[idx]; 
    long res = 0;
    if(idx == 2) res = 1L;
    else res = (res + dfs_p(idx-1, dpf, dpp, n) + dfs_f(idx-2, dpf, dpp, n)) % MOD;
    return dpp[idx] = res;
  } 
}