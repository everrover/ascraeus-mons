package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/
 * Used isolation strategy here. Only way to move forward is with 2^jump moves, and only way to step back is to use -1 moves.
 * If we move forward `jump` times we can move back `jump+1` times(1st step case)
 * That `sums up = 2^jumps - (jump+1)`. Hence max number of drops that can be taken = `jumps+1` but required and thus allowed number of drops is `2^jumps - k`. Hence we need to select the latter from the pool of max jumps.
 * The approach hence involves combinatorics.
 *
 * Logs don't work in java with specific base properly so had to check for `ceil(log2(k))` using a loop and `r>n` checks in nCr.
 * TC: O(32*32)
 * SC: O(1)
 * #math #dynamic-programming #bit-manipulation #memoization #combinatorics #hard #isolative-strategy-to-percieve-the-problem
 */
public class FindNumberOfWaysToReachTheKThStair {

  public int waysToReachStair(int k) {
    if(k <= 5) return new int[]{2,4,4,3,2,4}[k];
    int res = 0;
    for(int jumps=0; jumps<31; jumps++){
      int maxDrops = jumps+1;
      int allowedDrops = (1 << jumps) - k;
      res += nCr(maxDrops, allowedDrops);
    }
    return res;
  }

  private int nCr(int n, int r){
    if(r>n || r<0) return 0;
    long res = 1;
    for(int i=0; i<r; i++)
      res = res * (n-i) / (i+1); // used *= instead and got stuck for a time
    return (int)res;
  }
  
}
