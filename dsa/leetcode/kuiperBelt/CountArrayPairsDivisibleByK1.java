package dsa.leetcode.kuiperBelt;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-array-pairs-divisible-by-k/
 * The approach involves calculating the greatest common divisor (GCD) of each number in the array with k.
 * It utilizes a map to record the frequency of each GCD value. For each pair of unique GCD values,
 * it checks if their product is divisible by k. If so, it calculates the number of valid pairs
 * that can be formed and adds to the result.
 * 
 * TC: O(n^2) SC: O(n)
 * #math #number-theory #hard
 */

class CountArrayPairsDivisibleByK1 {
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