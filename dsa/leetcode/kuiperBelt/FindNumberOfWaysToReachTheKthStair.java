package dsa.leetcode.kuiperBelt;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/
 * The solution uses dynamic programming with memoization to reduce
 * the complexity of recursion.
 * A key is generated for every state to ensure uniqueness and prevent
 * recalculating the same state.
 * 
 * TC: O(max-jumps*2*ln(n) = max-jumps*ln(n)), as it depends on the number of recursion calls
 * SC: O(max-jumps*2*ln(n) = max-jumps*ln(n)), where n is the depth of recursion tree and size of the dp map
 * #math #dynamic-programming #bit-manipulation #memoization #combinatorics #hard
 */

class Solution {
  private Map<Long, Long> dp;
  private static long []exp = null;
  
  public int waysToReachStair(int k) {
    if(k <= 5) return new int[]{2,4,4,3,2,4}[k];
    dp = new HashMap<>();
    if(exp == null){
      int i = 0;
      exp = new long[40]; exp[0] = 1;
      while(++i<exp.length) exp[i] = exp[i-1]*2;/*(long)Math.pow(2, jump)*/
    }
    int res = (int)sol(1, 0, 1, k);
    // System.out.println(dp);
    return res;
  }
  
  public long sol(int i, int jump, int x, int k){
    if(i > k+1) return 0;
    long nodeval = genKey(i, jump, x);
    if(dp.containsKey(nodeval)){ return dp.get(nodeval); }
    
    long res = 0;
    if(i == k) res++;
    
    if(i>0 && x == 1) res += sol(i-1, jump, 0, k);
    res += sol((int)((long)i+exp[jump]), jump+1, 1, k);
    dp.put(nodeval, res);
    return dp.get(nodeval);
  }
  
  private long genKey(int i, int jump, int x){
    long key = 0, tmp = (long)x; 
    key = key | ((tmp = x) << 61); // 13
    key = key | ((tmp = jump) << 40);
    key = key | (tmp=i);
    return key;
  }

}
