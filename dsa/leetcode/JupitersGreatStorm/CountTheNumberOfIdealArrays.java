package dsa.leetcode.JupitersGreatStorm;

class CountTheNumberOfIdealArrays {

  /**
   * https://leetcode.com/problems/count-the-number-of-ideal-arrays/description/?envType=daily-question&envId=2025-04-22
   *
   * 
   *
   * TC: O(n * 16) SC: O(n * k) where k is the limit in binomial coefficients.
   */

  static private final int MOD = (int)1e9+7;
  private static long[][] pascal;
  private long nCk(int m, int n) {
    return pascal[m][n];
  }

  private void buildPascals(int n, int k){
    pascal = new long[n+1][k+1];
    for(int i=0; i<=n; i++){
      pascal[i][0] = 1;
      int MIN = Math.min(i, k);
      for(int j=1; j<=MIN; j++){
        pascal[i][j] = (pascal[i-1][j-1]+pascal[i-1][j])%MOD;
      }
    }
  }
  private int N, MAXVAL;
  public int idealArrays(int n, int maxValue) {
    if(pascal == null) buildPascals(10000,15);
    long res=0;
    N = n; MAXVAL = maxValue;
    long [][]dp = new long[maxValue+1][16];
    for(int i=1; i<=maxValue; i++){
      res = (res + dfs(i, 0, dp)) % MOD;
    }
    return (int)res;
  }

  private long dfs(int curr, int n, long [][]dp){
    if(dp[curr][n] != 0) return dp[curr][n];
    long res = 0;
    for(int l=2*curr; l<=MAXVAL; l+=curr){
      res = (res + dfs(l, n+1, dp)) % MOD;
    }
    res = (res + nCk(N-1, n)) % MOD;
    return dp[curr][n] = res;
  }
}