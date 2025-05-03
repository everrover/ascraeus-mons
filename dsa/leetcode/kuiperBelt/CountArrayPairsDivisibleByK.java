package dsa.leetcode.KuiperBelt;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-array-pairs-divisible-by-k/
 * To solve this problem, the key insight is to find pairs whose product is divisible by k.
 * We utilize the greatest common divisor (gcd) to simplify the divisibility check.
 * By storing counts of numbers after applying gcd with k, we efficiently compute the result.
 * 
 * Brute force might have worked as well.
 *
 * TC: O(n*log2(100000) + k * k) SC: O(k)
 * #math #number-theory #hard #brute-force
 */

class CountArrayPairsDivisibleByK {
  private long gcd(long a, long b) {
    if(b>a) return gcd(b,a);
    if (b==0) return a;
    return gcd(b,a%b);
  }

  public long countPairs(int[] nums, int k) {
    Map<Long, Long> counts = new HashMap<>();
    long res = 0;
    for(int num: nums){
      counts.merge(gcd(num,k), 1L, Long::sum);
      // gcd = BigInteger.valueOf(num).gcd(BigInteger.valueOf(k)).longValue()
    }
    for(long v1: counts.keySet()){ 
      for(long v2: counts.keySet()){ 
        if(v1>=v2 && (v1*v2 % k) == 0L){
          res += (v1>v2?(counts.get(v1)*counts.get(v2)):(counts.get(v2)*(counts.get(v2)-1L)/2L));
        }
      } 
    }
    return res;
  }
}
