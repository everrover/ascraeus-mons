package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/prime-subset-selection-ii/
 *
 * Suffix minimum-count 0/1 knapsack over the primes, followed by a greedy lexicographic
 * reconstruction.
 * - Sieve the primes up to min(n, s) in increasing order; larger primes can never fit under s.
 * - dp[i][t] = minimum number of primes from primes[i..] summing to exactly t (1001 means
 *   unreachable). Recurrence: dp[i][t] = min(dp[i+1][t], 1 + dp[i+1][t - p_i]), dp[*][0] = 0.
 *   Evaluating dp[0][t] for every t in [1, s] fills the table top-down.
 * - The answer's sum is the largest t <= s with dp[0][t] reachable; dp[0][t] is then the
 *   minimum count for that sum.
 * - Reconstruction scans primes in increasing order and takes p_i iff
 *   dp[i+1][t - p_i] == dp[i][t] - 1, i.e. an optimal-count completion still exists using p_i.
 *   Taking the smallest feasible prime at every position is exactly what makes the resulting
 *   increasing array lexicographically smallest among all minimum-count, maximum-sum subsets.
 *
 * TC: O(π(min(n,s)) · s) SC: O(π(min(n,s)) · s)
 * #array #math #dynamic-programming #number-theory #hard
 */

class PrimeSubsetSelectionII {
  private int[] genprimes(int n, int s) {
    Boolean []sieve = new Boolean[n+1];
    int sz = 0, limit = Math.min(n,s);
    for(int i=2; i<=limit; i++){
      if(sieve[i] != null) continue;
      sz++;
      int j = i*2;
      sieve[i] = true;
      while(j <= n) {
        sieve[j] = false;
        j += i;
      }
    }
    int []primes = new int[sz];
    for(int i=0, j=0; i<=limit; i++) if(sieve[i]!=null && sieve[i]) primes[j++] = i;
    return primes;
  }
  public List<Integer> maxPrimeSubset(int n, int s) {
    int []primes = genprimes(n, s);
    Integer [][]memo = new Integer[primes.length][s+1];
    for(Integer []m: memo) m[0] = 0;
    int foundmin = 1001;
    for(int sum=1; sum<=s; sum++){
      int m = dfs(0, sum, primes, memo);
      if(m >= 1001) continue;
      if(foundmin>m){
        foundmin = m;
      }
    }
    List<Integer> res = navigate(memo, primes);
    return res;
  }

  private List<Integer> navigate(Integer [][]memo, int []primes){
    List<Integer> res = new ArrayList<>();
    if(primes.length == 0) return res;

    int r = primes.length;
    int c = memo[0].length;
    int j=c-1, i=0;

    while(j>0){
      if(memo[i][j] != 1001){
        break;
      }
      j--;
    }
    if(j == 0) return res;

    while(j>0 && i<r-1){
      if(memo[i+1][j-primes[i]] == memo[i][j]-1){ // pick prime
        res.add(primes[i]);
        j-=primes[i];
      }
      i++;
    }
    if(i == r-1 && primes[i] == j) res.add(primes[i]);
    return res;
  }

  private int dfs(int idx, int sum, final int []primes, final Integer [][]dp){
    if(sum == 0) return 0;
    if(idx >= primes.length || sum < 0) return 1001;
    if(dp[idx][sum] != null) return dp[idx][sum];
    int nsel = dfs(idx+1, sum, primes, dp);
    int sel = dfs(idx+1, sum-primes[idx], primes, dp);
    return dp[idx][sum] = Math.min(nsel, 1+sel);
  }
}
