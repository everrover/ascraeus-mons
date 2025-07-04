package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class CountPrimeGapBalancedSubarrays {
  /**
   * https://leetcode.com/problems/count-prime-gap-balanced-subarrays/description/
   *
   * The algorithm utilizes the Sieve of Eratosthenes to compute prime numbers up to a limit.
   * It uses a sliding window approach to maintain subarrays with valid prime-gap.
   *
   * TreeMap helps in maintaining current prime status for range queries to check max-min primes.
   * And queue is used to track indices of prime numbers in the current window, and also to enable easy fetch of 2nd prime val's
   * index
   *
   * TC: O(n log p) SC: O(p)
   * #sliding-window #trees #prime-sieve #medium
   */

  private static boolean[] primes = null;
  private static int LT = 5 * (int) 1e4;

  private void computePrimes() {
    int UB = (int) Math.sqrt(LT);
    primes = new boolean[LT + 1];
    Arrays.fill(primes, true);
    primes[1] = false;
    for (int m = 2; m <= UB; m++) {
      if (primes[m]) {
        for (int k = m * m; k <= LT; k += m) primes[k] = false;
      }
    }
    // for(int p=1; p<prime.length; p++) if(prime[p]) primes.add(p);
  }

  public int primeSubarray(int[] nums, int k) {
    int i = 0, j = 0, res = 0, ps = 0;
    computePrimes();
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    List<Integer> q = new LinkedList<>();
    // System.out.println(primes);
    while (i < nums.length) {
      while (j < nums.length) {
        if (primes[nums[j]]) {
          Integer maxkey = tm.lowerKey(LT);
          Integer minkey = tm.higherKey(0);
          if (maxkey != null && (Math.abs(nums[j] - maxkey) > k || Math.abs(nums[j] - minkey) > k)) break;
          tm.put(nums[j], tm.getOrDefault(nums[j], 0) + 1);
          q.add(j);
        }
        j++;
      }
      if (q.size() >= 2) {
        res += (j - q.get(1));
      }
      if (primes[nums[i]]) {
        tm.put(nums[i], tm.getOrDefault(nums[i], 0) - 1);
        q.remove(0);
      }
      i++;
    }
    return res;
  }
}