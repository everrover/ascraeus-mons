package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/minimize-the-maximum-value-of-gcd-connected-array/
 *
 * Since gcd(x, y) = gcd(x, y - x), two neighbours share a factor exactly when their difference
 * shares a factor with x. So the smallest valid step away from x is spf(x), its smallest prime
 * factor.
 * - value == 1 has no prime factor, so there is no valid array (spf returns 1, giving -1).
 * - Right side: the first step goes to value + p, where p = spf(value). That number is always even:
 * if value is even then p = 2, and if value is odd then p is odd and odd + odd is even. Every later
 * step can then be +2, the smallest possible gap, since even neighbours always share 2. So
 * nums[n-1] = value + p + 2*(n-index-2), or just value when index == n-1.
 * - Left side: by the same argument, the greedy predecessors are value - p followed by -2 steps,
 * all even. The array exists iff nums[0] = value - p - 2*(index-1) > 0. That number is even, so
 * being > 0 means it is >= 2, which still shares 2 with its neighbour. This also forces a prime
 * value to sit at index 0, because value - value = 0.
 * - Finding spf by trial division up to sqrt(value) dominates the cost. The sieve helper is left
 * over from an earlier attempt and is never called.
 *
 * TC: O(sqrt(value)) SC: O(1)
 * #math #number-theory #greedy #medium
 */

class MinimizeTheMaximumValueOfGcdConnectedArray {
  private static List<Long> psn = null;
  private void genprimes(int lt){
    psn = new ArrayList<>();
    if (lt < 2) return;

    BitSet isp = new BitSet(lt + 1);
    isp.set(0);
    isp.set(1);

    for (int p = 2; p * p <= lt; p++)
      if (!isp.get(p)) for (int i = p * p; i <= lt && i>0; i += p) isp.set(i);

    for (int i = 2; i <= lt; i++) if (!isp.get(i)) {long nta = (long)i; psn.add(nta);}
  }
  private long getsmallestprime(long num){
    // genprimes((int)1e9);
    // System.out.println(psn);
    // for(long prime: psn) if(num % prime == 0) return prime;
    long smpr = num;
    for(int i=2; i*i<=num; i++){
      if(num % i == 0) {
        smpr = i; break;
      }
    }
    return smpr;
  }
  public long minimumMaxValue(int n, int index, int value) {
    long smpr = getsmallestprime((long)value);
    if(smpr == 1) return -1;
    long smallest = value - ((index>0&&n>=2)?smpr:0) - ((index>1&&n>=3)?((index-1) * 2):0);
    if(smallest <= 0) return -1;
    long largest = value + ((n>=2&&index<(n-1))?smpr:0) + (n>=3&&index<(n-2)?((n-index-2)*2):0);
    return largest;
  }
}
